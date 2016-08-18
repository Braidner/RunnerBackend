package org.braidner.runner.service.impl;

import org.braidner.runner.domain.Run;
import org.braidner.runner.domain.User;
import org.braidner.runner.service.GameService;
import org.braidner.runner.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Braidner
 * Date: 11/08/2016
 * Time: 14:18
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private RunService runService;

    @Override
    public void initGame(User user) {
        if (user.isRunning()) {
            Run unfinishedRun = runService.findUnfinishedRun(user.getUsername());
            if (unfinishedRun != null) {
                unfinishedRun.setEndRun(new Date());
            }
            user.setRunning(false);
        }

    }
}
