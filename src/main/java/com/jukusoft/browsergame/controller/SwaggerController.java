package com.jukusoft.browsergame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for swagger documentation.
 *
 * @author Justin Kuenzel
 */
@Controller
public class SwaggerController {

    /**
     * Redirect all requests for /swagger to the html page.
     *
     * @return Redirect String
     */
    @GetMapping("/swagger")
    public String redirectToSwaggerUI() {
        return "redirect:/swagger-ui.html";
    }

}
