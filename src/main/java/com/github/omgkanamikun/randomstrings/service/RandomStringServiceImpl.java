package com.github.omgkanamikun.randomstrings.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RandomStringServiceImpl implements RandomStringService {

    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    @Override
    public String generateString() {
        return generate();
    }

    private String generate() {

        int leftLimit = 48;
        int rightLimit = 122;

        int targetStringLength = 29;

        return IntStream
                .range(0, targetStringLength)
                .map(i -> leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1)))
                .mapToObj(randomLimitedInt -> String.valueOf((char) randomLimitedInt))
                .collect(Collectors.joining());
    }

}
