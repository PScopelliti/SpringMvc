package com.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

/**
 * Created by giuseppe on 13/07/15.
 */

@Controller
public class ExerciseResourceBean implements ExerciseResource {


    public String printWelcome(final ModelMap model) {
        model.addAttribute("message", "");
        return "exercise";
    }
}