package com.coderscampus.assignment9.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment9.domain.Recipes;

@Service
public class FileService {
    private String fileName;

    public FileService() {
    }

    public FileService(String fileName) {
        this.fileName = fileName;
    }

    public List<Recipes> csvParser() throws IOException {
        List<Recipes> recipes = new ArrayList<>();
        Reader in = new FileReader("recipes.txt");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreSurroundingSpaces()
                .withEscape('\\')
                .parse(in);

            for (CSVRecord record : records) {
                Recipes recipe = new Recipes(
                    Integer.parseInt(record.get("Cooking Minutes")),
                    Boolean.parseBoolean(record.get("Dairy Free")),
                    Boolean.parseBoolean(record.get("Gluten Free")),
                    record.get("Instructions"),
                    Double.parseDouble(record.get("Preparation Minutes")),
                    Double.parseDouble(record.get("Price Per Serving")),
                    Integer.parseInt(record.get("Ready In Minutes")),
                    Integer.parseInt(record.get("Servings")),
                    Double.parseDouble(record.get("Spoonacular Score")),
                    record.get("Title"),
                    Boolean.parseBoolean(record.get("Vegan")),
                    Boolean.parseBoolean(record.get("Vegetarian"))
                );
                recipes.add(recipe);
            }
            return recipes;
        }

    public List<String> readFile(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }
}