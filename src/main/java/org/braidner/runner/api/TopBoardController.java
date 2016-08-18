package org.braidner.runner.api;

import org.braidner.runner.domain.Score;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KuznetsovNE on 05.08.2016.
 */
@RestController
@RequestMapping("api/top")
public class TopBoardController {

    @GetMapping("score")
    public List<Score> score() {
        return new ArrayList<>();
    }

    @GetMapping("distance")
    public List<Score> distance() {
        return new ArrayList<>();
    }

}