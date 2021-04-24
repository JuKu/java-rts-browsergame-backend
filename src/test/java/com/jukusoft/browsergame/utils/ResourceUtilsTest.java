package com.jukusoft.browsergame.utils;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ResourceUtilsTest {

    @Test(expected = IOException.class)
    public void testResourceNotExists() throws IOException {
        ResourceUtils.readResourceFile("not_existent_file.txt");
    }

    @Test
    public void testReadResource() throws IOException {
        String content = ResourceUtils.readResourceFile("permissions/global_roles.json");
        assertNotNull(content);

        assertFalse(content.isEmpty());
        assertTrue(content.startsWith("["));
        assertTrue(content.endsWith("]"));
    }

}
