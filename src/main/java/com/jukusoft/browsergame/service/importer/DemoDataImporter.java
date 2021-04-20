package com.jukusoft.browsergame.service.importer;

import com.jukusoft.browsergame.dao.UserDAO;
import com.jukusoft.browsergame.entity.user.UserEntity;
import com.jukusoft.browsergame.service.PasswordService;
import com.jukusoft.browsergame.utils.ImportUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class DemoDataImporter implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(DemoDataImporter.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordService passwordService;

    @Bean(name = "user_import")
    @Override
    public void afterPropertiesSet() throws Exception {
        if (!ImportUtils.isInitialImportEnabled()) {
            return;
        }

        //check, if min. one user exists, else create admin user
        if (userDAO.count() == 0) {
            logger.info("create demo user 'admin'");

            //create demo user and add it to first customer
            UserEntity user = new UserEntity("admin", "Admin", "Admin");
            user.setPassword(passwordService.getPasswordEncoder().encode("admin"));
            user = userDAO.save(user);

            if (user.getId() == 0) {
                throw new IllegalStateException("user id is was not automatically inserted by Spring JPA");
            }
        }
    }

}
