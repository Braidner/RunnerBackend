package org.braidner.runner.service;

import org.braidner.runner.domain.Run;
import org.braidner.runner.domain.User;

/**
 * Created by Braidner
 */
public interface RunService {

    String startRun(User user);

    Run findUnfinishedRun(String username);
}
