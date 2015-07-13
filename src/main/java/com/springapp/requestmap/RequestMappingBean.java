package com.springapp.requestmap;

import com.springapp.controller.ExerciseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This class contains all request mapping managed by the entire application.
 */
@Controller
@RequestMapping(value = "/")
public class RequestMappingBean {

    private ExerciseResource exerciseResource;

    @Autowired
    public RequestMappingBean(final ExerciseResource exerciseResource){
        this.exerciseResource = exerciseResource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(final ModelMap model) {
        model.addAttribute("message", "");
        return exerciseResource.printWelcome(model);
    }
}