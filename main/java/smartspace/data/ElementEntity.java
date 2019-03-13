package smartspace.data;

//aviel

import java.util.Date;
import java.util.Map;

public class ElementEntity implements SmartspaceEntity<String> {

	private String elementSmartSpace;
	private String elementId;
	private Location location;
	private String name;
	private String type;
	private Date creationTimestamp;
	private boolean expierd;
	private String creatorSmartspace;
	private String creatorEmail;
	private Map<String, Object> moreAttributes;

	// default constructor
	public ElementEntity() {
	}

	public String getElementSmartSpace() {
		return elementSmartSpace;
	}

	public void setElementSmartSpace(String elementSmartSpace) {
		this.elementSmartSpace = elementSmartSpace;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isExpierd() {
		return expierd;
	}

	public void setExpierd(boolean expierd) {
		this.expierd = expierd;
	}

	public String getCreatorSmartspace() {
		return creatorSmartspace;
	}

	public void setCreatorSmartspace(String creatorSmartspace) {
		this.creatorSmartspace = creatorSmartspace;
	}

	public String getCreatorEmail() {
		return creatorEmail;
	}

	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}

	public Map<String, Object> getMoreAttributes() {
		return moreAttributes;
	}

	public void setMoreAttributes(Map<String, Object> moreAttributes) {
		this.moreAttributes = moreAttributes;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}


	@Override
	public String getKey() {
		return this.elementId;
	}

	@Override
	public void setKey(String k) {
		this.elementId = k;
	}

	@Override
	public String toString() {
		return "ElementEntity [elementSmartSpace=" + elementSmartSpace + ", elementId=" + elementId + ", location="
				+ location + ", name=" + name + ", type=" + type + ", creationTimestamp=" + creationTimestamp
				+ ", expierd=" + expierd + ", creatorSmartspace=" + creatorSmartspace + ", creatorEmail=" + creatorEmail
				+ ", moreAttributes=" + moreAttributes + "]";
	}

}