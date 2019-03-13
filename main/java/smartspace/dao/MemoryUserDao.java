package smartspace.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import smartspace.data.UserEntity;

//Amit 13/03
@Repository
public class MemoryUserDao implements UserDao<String> {
	private List<UserEntity> users;

	// To Do
	@Override
	public UserEntity create(UserEntity userEntity) {
		return null;
	}

	@Override
	public List<UserEntity> readAll() {
		return this.users;
	}

	@Override
	public Optional<UserEntity> readById(String userKey) {
		UserEntity target = null;
		for (UserEntity current : this.users) {
			if (current.getKey().equals(userKey)) {
				target = current;
			}
		}
		if (target != null) {
			return Optional.of(target);
		} else {
			return Optional.empty();
		}
	}

//Amit - Have to decide about line 66
	@Override
	public void update(UserEntity update) {
		synchronized (this.users) {
			UserEntity existing = this.readById(update.getKey())
					.orElseThrow(() -> new RuntimeException("not message to update"));
			if (update.getAvatar() != null) {
				existing.setAvatar(update.getAvatar());
			}
			if (update.getUserEmail() != null) {
				existing.setUserEmail(update.getUserEmail());
			}
			if (update.getUsername() != null) {
				existing.setUsername(update.getUsername());
			}
			if (update.getUserSmartspace() != null) {
				existing.setUserSmartspace(update.getUserSmartspace());
			}
			if (update.getRole() != null) {
				existing.setRole(update.getRole());
			}
			// ????????
//			if (update.getPoints() >= 0) {
//				existing.setPoints(update.getPoints());
//			}

		}
	}

	@Override
	public void deleteAll() {
		this.users.clear();
	}

}
//