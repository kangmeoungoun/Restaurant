package com.ga.jpatest.lunch.parser;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVParser {

    private final String path;

    public CSVParser(String path) {
        this.path = path;
    }

    public List<String> parse() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(path);
        Path path = Paths.get(classPathResource.getURI());
        return Files.readAllLines(path);
    }

}
