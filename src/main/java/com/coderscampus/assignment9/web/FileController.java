package com.coderscampus.assignment9.web;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.assignment9.domain.Recipes;
import com.coderscampus.assignment9.service.FileService;

@RestController
public class FileController {
	
	@Autowired
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    
	@GetMapping("/gluten-free") //The “gluten-free” endpoint will only return a subset of the full Collection where glutenFree is true
	public List<Recipes> readGluten () throws IOException {
		List<Recipes> allRecipes = fileService.csvParser();
		return allRecipes.stream()
				.filter(Recipes::getGlutenFree)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/vegan") //The “vegan” endpoint will only return a subset of the full Collection where vegan is true
	public List<Recipes> readVegan () throws IOException {
		List<Recipes> allRecipes = fileService.csvParser();
		return allRecipes.stream()
				.filter(Recipes::getVegan)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/vegan-and-gluten-free") //The “vegan-and-gluten-free” endpoint will only return a subset of the full Collection where glutenFree is true and vegan is true
	public List<Recipes> readVeganGluten () throws IOException {
		List<Recipes> allRecipes = fileService.csvParser();
		return allRecipes.stream()
				.filter(Recipes::getVegan)
				.filter(Recipes::getGlutenFree)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/vegetarian") //The “vegetarian” endpoint will only return a subset of the full Collection where vegetarian is true
	public List<Recipes> readVegetarian () throws IOException {
		List<Recipes> allRecipes = fileService.csvParser();
		return allRecipes.stream()
				.filter(Recipes::getVegetarian)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/all-recipes") //The “all-recipes” endpoint will not filter the data at all and should return the full Collection.
	public List<Recipes> readAllRecipes () throws IOException {
		return fileService.csvParser();
	}
	
}
