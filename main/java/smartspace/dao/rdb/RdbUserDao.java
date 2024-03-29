package smartspace.dao.rdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import smartspace.dao.EnhancedUserDao;
import smartspace.data.UserEntity;
import smartspace.data.UserKey;

@Repository
public class RdbUserDao implements EnhancedUserDao<UserKey> {
	private UserCrud userCrud;

	private String appSmartspace;

	@Autowired
	public RdbUserDao(UserCrud userCrud) {
		super();
		this.userCrud = userCrud;
	}

    @Value("${smartspace.name}") 
	public void setAppSmartspace(String appSmartspace) {
		this.appSmartspace = appSmartspace;
	}

	@Override
	@Transactional
	public UserEntity create(UserEntity userEntity) {
		userEntity.setUserSmartspace(appSmartspace);
		userEntity.setKey(new UserKey(userEntity.getUserSmartspace(), userEntity.getUserEmail()));

		if (!this.userCrud.existsById(userEntity.getKey())) {
			UserEntity rv = this.userCrud.save(userEntity);
			return rv;
		} else {
			throw new RuntimeException("user already exists with key: " + userEntity.getKey());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserEntity> readAll() {
		List<UserEntity> rv = new ArrayList<>();

		this.userCrud.findAll().forEach(rv::add);
		return rv;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserEntity> readById(UserKey userKey) {
		return this.userCrud.findById(userKey);
	}

	@Override
	@Transactional
	public void update(UserEntity update) {

		UserEntity existing = this.readById(update.getKey())
				.orElseThrow(() -> new RuntimeException("no user to update"));

		if (update.getAvatar() != null) {
			existing.setAvatar(update.getAvatar());
		}
//		if (update.getUserEmail() != null) {
//			existing.setUserEmail(update.getUserEmail());
//		}
		if (update.getUsername() != null) {
			existing.setUsername(update.getUsername());
		}
//		if (update.getUserSmartspace() != null) {
//			existing.setUserSmartspace(update.getUserSmartspace());
//		}
		if (update.getRole() != null) {
			existing.setRole(update.getRole());
		}
		//existing.setPoints(update.getPoints()); the only way to get points is to do actions

		this.userCrud.save(existing);
	}

	@Override
	@Transactional
	public void addPoints(UserEntity update) {
		UserEntity existing = this.readById(update.getKey())
				.orElseThrow(() -> new RuntimeException("no user to update"));
		
		existing.setPoints(update.getPoints());
		this.userCrud.save(existing);
	}

	@Override
	@Transactional
	public void deleteAll() {
		this.userCrud.deleteAll();
	}

	/**
	 * Read all with paging.
	 *
	 * @param size the size
	 * @param page the page
	 * @return the list
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserEntity> readAllWithPaging(int size, int page) {
		return this.userCrud.findAll(PageRequest.of(page, size, Direction.ASC, "key")).getContent();
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserEntity> readAllWithPaging(String sortBy, int size, int page) {
		return this.userCrud.findAll(PageRequest.of(page, size, Direction.ASC, sortBy)).getContent();

	}

	/**
	 * Import user.
	 *
	 * @param user the user
	 * @return the user entity
	 */
	@Override
	@Transactional
	public UserEntity importUser(UserEntity user) {
		if (user.getKey() != null) {
			return this.userCrud.save(user);
		}
		return null;
	}

}
