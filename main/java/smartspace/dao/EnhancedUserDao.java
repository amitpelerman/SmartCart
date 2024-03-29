/**
 * @author liadkh	16-04-2019
 */
package smartspace.dao;

import java.util.List;

import smartspace.data.UserEntity;
import smartspace.data.UserKey;

/**
 * The Interface EnhancedUserDao.
 *
 * @author liadkh
 * @param <UserKey> the generic type
 */
public interface EnhancedUserDao<UserKey> extends UserDao<UserKey> {

	/**
	 * Read all with paging.
	 *
	 * @param size the size
	 * @param page the page
	 * @return the list
	 */
	public List<UserEntity> readAllWithPaging(int size, int page);
	
	public List<UserEntity> readAllWithPaging(String sortBy,int size, int page);
	

	/**
	 * Import user.
	 *
	 * @param user the user
	 * @return the user entity
	 */
	public UserEntity importUser(UserEntity user);

	public void addPoints(UserEntity update);
}
