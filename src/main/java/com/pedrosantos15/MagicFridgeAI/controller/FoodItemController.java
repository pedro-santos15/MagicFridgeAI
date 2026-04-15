package com.pedrosantos15.MagicFridgeAI.controller;

import com.pedrosantos15.MagicFridgeAI.model.FoodItem;
import com.pedrosantos15.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }

    //POST (Create)
    @PostMapping
    public ResponseEntity<FoodItem> create(@RequestBody FoodItem foodItem) {
        FoodItem savedItem = service.save(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedItem);
    }

    //GET (Read)
    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getById(@PathVariable Long id) {
        FoodItem foodItem = service.findById(id);

        if (foodItem != null) {
            return ResponseEntity.ok(foodItem);
        } else {
            return ResponseEntity.notFound()
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FoodItem>> listAllItems() {
        return ResponseEntity.ok(service.listAll());
    }

    //UPDATE

    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> updateItem(@PathVariable Long id, @RequestBody FoodItem foodItem) {
        FoodItem updatedItem = service.updateItem(id, foodItem);

        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        }

        return ResponseEntity.notFound()
                .build();
    }

    //DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        FoodItem foodItem = service.findById(id);

        if (foodItem != null){
            service.deleteItem(id);
            return ResponseEntity.noContent()
                    .build();
        }

        return ResponseEntity.notFound()
                .build();
    }
}
