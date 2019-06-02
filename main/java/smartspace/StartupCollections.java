package smartspace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("test") // only runs in tests startup
public class StartupCollections implements CommandLineRunner {
	private MongoTemplate mongoTemplate;

	public StartupCollections() {
	}

	@Autowired
	public StartupCollections(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		String[] collections = { "USERS", "ELEMENTS", "ACTIONS", "database_sequences" };

		// IMPORTANT! MongoDB transactions can't create collections!!! couldn't find any other way at the moment.
		// the problem is when a transaction tried to create something into a collection that doesn't exist
		// transactions can't create non existing collections so it fails.
		for (String collectionName : collections) {
			if (!mongoTemplate.getCollectionNames().contains(collectionName)) {
				mongoTemplate.createCollection(collectionName);
			}
		}
	}
}