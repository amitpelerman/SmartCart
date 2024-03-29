package smartspace.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import smartspace.aop.PlayerOrManagerGetRole;
import smartspace.dao.EnhancedElementDao;
import smartspace.data.ElementEntity;
import smartspace.data.ElementKey;
import smartspace.data.UserRole;

@Service
// @LoggerService
public class GetSpecificElementServiceImpl implements GetSpecificElementService {

	private EnhancedElementDao<ElementKey> elementDao;

	@Autowired
	public GetSpecificElementServiceImpl(EnhancedElementDao<ElementKey> elementDao) {
		this.elementDao = elementDao;
	}

	@Override
	@PlayerOrManagerGetRole
	public ElementEntity getElement(UserRole role, String userSmartspace, String userEmail, String elementSmartspace, String elementId) {
		
		ElementEntity element = elementDao.readById(new ElementKey(elementSmartspace, Long.parseLong(elementId))).orElseThrow(
				()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Element not in DB"));
		
		if (role == UserRole.MANAGER || (role == UserRole.PLAYER && !element.getExpired())) {
			return element;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Element not found");
		}

	}

}