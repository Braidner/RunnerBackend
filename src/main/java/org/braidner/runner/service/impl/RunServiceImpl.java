package org.braidner.runner.service.impl;

import org.braidner.runner.domain.Run;
import org.braidner.runner.domain.User;
import org.braidner.runner.repository.RunRepository;
import org.braidner.runner.repository.UserRepository;
import org.braidner.runner.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Braidner
 */
@Service
public class RunServiceImpl implements RunService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RunRepository runRepository;

    @Override
    public String startRun(User user) {
        if (user.isRunning()) throw new RuntimeException("Hack");
        user.setRunning(true);
        userRepository.save(user);
        Run run = runRepository.save(new Run(user.getUsername()));
        return run.getGuid();
    }

    @Override
    public Run findUnfinishedRun(String username) {
        return runRepository.findByUsernameAndEndRun(username, null);
    }
}
