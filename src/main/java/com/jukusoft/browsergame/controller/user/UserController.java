package com.jukusoft.browsergame.controller.user;

import com.jukusoft.browsergame.dao.UserDAO;
import com.jukusoft.browsergame.entity.user.UserEntity;
import com.jukusoft.browsergame.service.PasswordService;
import com.jukusoft.browsergame.service.setting.GlobalSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class UserController {

    @Autowired
    private GlobalSettingsService globalSettingsService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        if (!globalSettingsService.getSetting("registration.password").isPresent() && !globalSettingsService.getSetting("registration.password").get().getValue().equals("none")) {
            //first, check password
            if (!globalSettingsService.getSetting("registration.password").get().getValue().equals(request.getPassword())) {
                //wrong registration password
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("registration password is wrong!");
            }
        }

        String username = HtmlUtils.htmlEscape(request.getUsername(), "UTF-8");

        //check, if username already exists
        if (userDAO.findOneByUsername(username).isPresent()) {
            //username already exists
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username already exists");
        }

        UserEntity user = new UserEntity(username, HtmlUtils.htmlEscape(request.getPrename(), "UTF-8"), HtmlUtils.htmlEscape(request.getLastname(), "UTF-8"));
        user.setPassword(passwordService.getPasswordEncoder().encode(request.getPassword()));
        user = userDAO.save(user);

        return ResponseEntity.ok("registration completed");
    }

}
