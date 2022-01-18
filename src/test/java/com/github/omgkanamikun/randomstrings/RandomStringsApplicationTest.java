package com.github.omgkanamikun.randomstrings;

import com.github.omgkanamikun.randomstrings.controller.RandomStringController;
import com.github.omgkanamikun.randomstrings.service.RandomStringService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RandomStringsApplicationTest {

    final Logger logger = LoggerFactory.getLogger(RandomStringsApplicationTest.class);

    @Autowired
    private Environment environment;
    @Autowired
    private RandomStringService randomStringService;
    @Autowired
    private RandomStringController controller;

    @Test
    void contextTest() {
        assertNotNull(environment);
        assertNotNull(controller);
        assertNotNull(randomStringService);
    }

    @Test
    void shouldGenerateRandomStrings() {

        final Set<String> uniqueStrings = new HashSet<>();

        Stream.iterate(0, i -> i + 1)
                .limit(1000)
                .forEach(integer -> {

                    final String string = randomStringService.generateString();

                    boolean added = uniqueStrings.add(string);

                    if (!added) {
                        logger.info("failed when set had {} items, non unique string: {}", uniqueStrings.size(), string);
                    }

                    assertTrue(added, "service should generate always random strings!");
                });

    }

    @Test
    void shouldReceivePages() {
        assertFalse(controller.mainPage().isEmpty());
        assertTrue(controller.randomStringPage().hasView());
        assertFalse(controller.randomStringPage().getModel().values().isEmpty());
    }
}
