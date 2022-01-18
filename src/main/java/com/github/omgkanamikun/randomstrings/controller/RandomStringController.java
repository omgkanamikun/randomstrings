package com.github.omgkanamikun.randomstrings.controller;

import com.github.omgkanamikun.randomstrings.service.RandomStringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RandomStringController {

    final Logger logger = LoggerFactory.getLogger(RandomStringController.class);

    private final RandomStringService service;

    public RandomStringController(RandomStringService service) {
        this.service = service;
    }

    @GetMapping({"", "/app"})
    public String mainPage() {
        logger.info("somebody requested main page");

        return "main";
    }

    @GetMapping(value = "/generate")
    public ModelAndView randomStringPage() {
        logger.info("somebody requested main page");

        final String randomString = service.generateString();
        logger.info("pseudo random string generated: {}", randomString);

        ModelAndView model = new ModelAndView();
        model.addObject("generatedString", randomString);
        model.setViewName("random-strings");

        return model;
    }

}
