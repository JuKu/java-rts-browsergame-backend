package com.jukusoft.browsergame.service.importer;

import com.jukusoft.browsergame.dao.ResourceTypeDAO;
import com.jukusoft.browsergame.entity.island.ResourceTypeEntity;
import com.jukusoft.browsergame.entity.user.PermissionEntity;
import com.jukusoft.browsergame.entity.user.RoleEntity;
import com.jukusoft.browsergame.utils.ImportUtils;
import com.jukusoft.browsergame.utils.ResourceUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
//@DependsOn({"permission_import", "user_import"})
public class ResourceImporter implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ResourceImporter.class);

    @Autowired
    private ResourceTypeDAO resourceTypeDAO;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!ImportUtils.isInitialImportEnabled()) {
            return;
        }

        logger.info("create or update resource types");

        JSONArray jsonArray = new JSONArray(ResourceUtils.readResourceFile("init/general/resources"));

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);

            String key = json.getString("key");//unique name
            String title = json.getString("title");
            String alt = json.getString("alt");
            String icon = json.getString("icon");

            if (resourceTypeDAO.existsByKey(key)) {
                logger.debug("resource type '{}' already exists", key);

                ResourceTypeEntity res = resourceTypeDAO.findByKey(key).orElseThrow();
                res.setTitle(title);
                res.setAlt(alt);
                res.setIcon(icon);
                resourceTypeDAO.save(res);
            } else {
                logger.info("create resource type '{}'", key);

                //create new resource type
                ResourceTypeEntity res = new ResourceTypeEntity(key, title, alt, icon);
                resourceTypeDAO.save(res);
            }
        }
    }

}
