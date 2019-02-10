package com.paralyzedfetch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robertoduessmann on 2/5/19
 */
@Slf4j
@Service
public class CityReader {

    private static final String FILE_NAME = "cities.txt";

    public List<String> read() {
        List<String> cities = new ArrayList<>();
        try {
            Files.lines(Paths.get(ClassLoader.getSystemResource(FILE_NAME).toURI()))
                    .forEach(cities::add);
        } catch (URISyntaxException | IOException e) {
            log.error("Error to read cities from ", FILE_NAME, e);
        }
        return cities;
    }
}
