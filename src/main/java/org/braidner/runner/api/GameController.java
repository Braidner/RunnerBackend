package org.braidner.runner.api;

import org.braidner.runner.annotation.CurrentUser;
import org.braidner.runner.domain.User;
import org.braidner.runner.domain.RunInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by KuznetsovNE on 05.08.2016.
 */
@RestController
@RequestMapping("api/game")
public class GameController {

    @PostMapping("start")
    public void start(@CurrentUser User currentUser) {
        if (currentUser.isRunning()) throw new RuntimeException("Hacked");

        currentUser.setRunning(true);
    }

    @PostMapping("finish")
    public void finish(RunInfo runInfo, Principal principal) {

    }

}
