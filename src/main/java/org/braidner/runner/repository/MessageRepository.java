package org.braidner.runner.repository;

import org.braidner.runner.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Braidner
 * Date: 17/08/2016
 * Time: 14:12
 */
public interface MessageRepository extends MongoRepository<Message, String> {
}
