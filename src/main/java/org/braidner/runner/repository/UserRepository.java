package org.braidner.runner.repository;

import org.braidner.runner.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Braidner
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByUserId(String username);
}
