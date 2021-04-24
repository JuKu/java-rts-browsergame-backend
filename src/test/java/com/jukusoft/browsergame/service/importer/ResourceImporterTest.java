package com.jukusoft.browsergame.service.importer;

import com.jukusoft.browsergame.config.TestH2Config;
import com.jukusoft.browsergame.dao.ResourceTypeDAO;
import com.jukusoft.browsergame.entity.general.AbstractEntity;
import com.jukusoft.browsergame.entity.island.ResourceTypeEntity;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@SpringBootTest
//@Import({ResourceTypeEntity.class/*, TestH2Config.class*/, ResourceImporter.class})
@ActiveProfiles({"test"})
//@ExtendWith({MockitoExtension.class})
//@ComponentScan({"com.jukusoft.browsergame.entity", "com.jukusoft.browsergame.dao"})
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
//@ContextConfiguration(classes = {ResourceTypeDAO.class})
public class ResourceImporterTest {

    protected static final AtomicInteger createdResourceTypes = new AtomicInteger(0);
    protected static final List<ResourceTypeEntity> createdEntities = new ArrayList<>();

    @TestConfiguration
    static class TestConfig {

        @Bean
        @Primary
        public ResourceTypeDAO createResourceTypeDAO() {
            ResourceTypeDAO dao = Mockito.mock(ResourceTypeDAO.class);
            when(dao.save(any(ResourceTypeEntity.class))).then(new Answer<ResourceTypeEntity>() {
                @Override
                public ResourceTypeEntity answer(InvocationOnMock invocationOnMock) throws Throwable {
                    createdResourceTypes.incrementAndGet();
                    createdEntities.add(invocationOnMock.getArgument(0, ResourceTypeEntity.class));

                    return null;
                }
            });

            return dao;
        }

    }

    @Autowired
    private ResourceTypeDAO resourceTypeDAO;

    @DisplayName("Test if resource types can be imported")
    @Test
    public void testImportResourceTypes() throws Exception {
        assertNotNull(resourceTypeDAO);

        ResourceImporter importer = new ResourceImporter(resourceTypeDAO);
        importer.afterPropertiesSet();

        //check, that 4 resource types was created or updated
        assertEquals(4, createdResourceTypes.get());
        assertTrue(createdEntities.stream().anyMatch(entity -> entity.getKey().equals("wood")));
        assertTrue(createdEntities.stream().anyMatch(entity -> entity.getKey().equals("clay")));
        assertTrue(createdEntities.stream().anyMatch(entity -> entity.getKey().equals("stone")));
        assertTrue(createdEntities.stream().anyMatch(entity -> entity.getKey().equals("iron")));
    }

}
