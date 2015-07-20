package com.springapp.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Implementation of {@Link com.springapp.controller.home.HomeResource}
 */
@Controller
@RequestMapping(value = "/")
public class HomeResourceBean implements HomeResource {


    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }
}
