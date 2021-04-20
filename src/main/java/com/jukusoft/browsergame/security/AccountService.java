package com.jukusoft.browsergame.security;

import com.jukusoft.authentification.jwt.account.AccountDTO;
import com.jukusoft.authentification.jwt.account.IAccountService;
import com.jukusoft.browsergame.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("iAccountService")
public class AccountService implements IAccountService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<AccountDTO> loginUser(String username, String password) {
        return userDAO.findOneByUsername(username)
                .filter(account -> passwordEncoder.matches(password, account.getPassword()))
                .map(user -> new AccountDTO(user.getId(), user.getUsername()));
    }

}
