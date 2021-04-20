package com.jukusoft.browsergame.utils;

import com.jukusoft.authentification.jwt.account.UserAccount;
import com.jukusoft.browsergame.dao.UserDAO;
import com.jukusoft.browsergame.entity.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserHelperService {

    @Autowired
    private UserDAO userDAO;

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Objects.requireNonNull(authentication);

        UserAccount userAccount = (UserAccount) authentication;

        //find current userID
        long userID = userAccount.getUserID();

        return userDAO.findById(userID).orElseThrow();
    }

}
