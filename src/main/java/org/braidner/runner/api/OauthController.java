package org.braidner.runner.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Braidner
 */
@RestController
public class OauthController {

    @RequestMapping
    public void index() {
    }

    @RequestMapping("oauth2callback")
    public void callback() {

    }
}
