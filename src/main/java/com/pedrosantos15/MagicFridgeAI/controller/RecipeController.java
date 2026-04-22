package com.pedrosantos15.MagicFridgeAI.controller;

import com.pedrosantos15.MagicFridgeAI.model.FoodItem;
import com.pedrosantos15.MagicFridgeAI.service.FoodItemService;
import com.pedrosantos15.MagicFridgeAI.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RecipeController {

    private GeminiService geminiService;
    private FoodItemService service;

    public RecipeController(GeminiService geminiService, FoodItemService service) {
        this.geminiService = geminiService;
        this.service = service;
    }


    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe(){

        List<FoodItem> foodItems = service.listAll();

        return geminiService.generateRecipe(foodItems)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());

    }

}
