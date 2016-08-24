package org.braidner.runner.api;

import org.braidner.runner.annotation.CurrentUser;
import org.braidner.runner.service.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Created by Braidner
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    private final TokenValidator tokenValidator;

    @Autowired
    public AuthController(@Qualifier("runnerTokenValidator") TokenValidator tokenValidator) {
        this.tokenValidator = tokenValidator;
    }

    @RequestMapping("*")
    private String generateAccessToken(@CurrentUser UserDetails userDetails) {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = Arrays.toString(bytes);
        tokenValidator.addToken(token, userDetails);
        return token;
    }

}
