package org.braidner.runner.repository;

import org.braidner.runner.domain.Run;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

/**
 * Created by Braidner
 */
public interface RunRepository extends MongoRepository<Run, String> {
    Run findByUsernameAndEndRun(String username, Date date);
}
