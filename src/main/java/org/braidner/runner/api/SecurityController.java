package org.braidner.runner.api;

import org.braidner.runner.domain.Message;
import org.braidner.runner.domain.User;
import org.braidner.runner.dto.RegistrationInfo;
import org.braidner.runner.repository.MessageRepository;
import org.braidner.runner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * Created with IntelliJ IDEA.
 * User: Braidner
 * Date: 04/08/2016
 * Time: 13:59
 */
@RestController
@RequestMapping("api/security")
public class SecurityController {

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public String test() {
        return "";
    }

    private UserRepository userRepository;

    private MessageRepository messageRepository;

    @Autowired
    public SecurityController(
            UserRepository userRepository,
            MessageRepository messageRepository
    ) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @PostMapping("signin")
    public String signIn(
            @Valid @RequestBody RegistrationInfo info,
            HttpServletRequest request, HttpServletResponse response
    ) {

        info.getAuthToken(); // TODO add token validation
        User user = new User();
        user.setUsername(info.getUsername());
        user.setDeviceId(info.getDeviceId());
        user.setDeviceType(info.getDeviceType());
        User save = userRepository.save(user);
        return save.getGuid();
    }

    @GetMapping("debug")
    public String debugLog(@RequestParam String message) {
        Message debugMessage = new Message();
        debugMessage.setBody(message);
        Message save = messageRepository.save(debugMessage);
        return save.getId();
    }

    @PostMapping("login")
    public String logIn(String googleToken) {
        return "login";
    }

    @PostMapping("signout")
    public String signOut() {
        return "";
    }

}