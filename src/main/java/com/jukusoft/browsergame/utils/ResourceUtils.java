package com.jukusoft.browsergame.utils;

import com.google.common.io.Resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * utility class responsible to read contents from resource files
 */
public class ResourceUtils {

    private ResourceUtils() {
        //
    }

    /**
     * read string content from resource file
     *
     * @param path path to resource file, e.q. "permissions/global_roles.json"
     * @return content
     * @throws IOException if resource file cannot found or resource read is not possible
     */
    public static String readResourceFile(String path) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(path);

        if (url == null) {
            throw new FileNotFoundException("resource doesn't exists: " + path);
        }

        return Resources.toString(url, StandardCharsets.UTF_8);
    }

}
