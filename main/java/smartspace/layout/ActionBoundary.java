package smartspace.layout;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import smartspace.data.ActionEntity;
import smartspace.data.ActionKey;

public class ActionBoundary {
	private Map<String, String> actionKey;
	private String type;
	private Date created;
	private Map<String, String> element;
	private Map<String, String> player;
	private Map<String, Object> properties;

	public ActionBoundary() {
	}

	// convert ActionEntity to ActionBoundary
	public ActionBoundary(ActionEntity entity) {
		this.actionKey = new TreeMap<String, String>();
		this.actionKey.put("id", entity.getActionId());
		this.actionKey.put("smartspace", entity.getActionSmartspace());

		this.type = entity.getActionType();

		this.created = entity.getCreationTimestamp();

		this.element = new TreeMap<String, String>();
		this.element.put("id", entity.getElementId());
		this.element.put("smartspace", entity.getElementSmartspace());

		this.player = new TreeMap<String, String>();
		this.player.put("smartspace", entity.getPlayerSmartspace());
		this.player.put("email", entity.getPlayerEmail());

		this.properties = entity.getMoreAttributes();
	}

	public Map<String, String> getActionKey() {
		return actionKey;
	}

	public void setActionKey(Map<String, String> actionKey) {
		this.actionKey = actionKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Map<String, String> getElement() {
		return element;
	}

	public void setElement(Map<String, String> element) {
		this.element = element;
	}

	public Map<String, String> getPlayer() {
		return player;
	}

	public void setPlayer(Map<String, String> player) {
		this.player = player;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public ActionEntity convertToEntity() {
		ActionEntity entity = new ActionEntity();

		// check that TreeMap is not null and contains the required keys
		if (this.actionKey != null && this.actionKey.get("smartspace") != null && this.actionKey.get("id") != null) {
			entity.setKey(new ActionKey(this.actionKey.get("smartspace"), Long.parseLong(this.actionKey.get("id"))));
		}

		entity.setActionType(this.type);
		entity.setCreationTimestamp(this.created);

		if (this.element != null && this.element.get("smartspace") != null && this.element.get("id") != null) {
			entity.setElementSmartspace(this.element.get("smartspace"));
			entity.setElementId(this.element.get("id"));
		}

		if (this.player != null && this.player.get("smartspace") != null && this.player.get("email") != null) {
			entity.setPlayerSmartspace(this.player.get("smartspace"));
			entity.setPlayerEmail(this.player.get("email"));
		}

		// default value
		entity.setMoreAttributes(new TreeMap<String, Object>());
		if (this.properties != null) {
			entity.setMoreAttributes(this.properties);
		}
		
		return entity;
	}

	@Override
	public String toString() {
		return "ActionBoundary [actionKey=" + actionKey + ", type=" + type + ", created=" + created + ", element="
				+ element + ", player=" + player + ", properties=" + properties + "]";
	}
}
