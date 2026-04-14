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
    @PostMapping("/create")
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

    @GetMapping("/all")
    public ResponseEntity<List<FoodItem>> listAllItems() {
        return ResponseEntity.ok(service.listAll());
    }

    //UPDATE

    @PutMapping("/update/{id}")
    public ResponseEntity<FoodItem> updateItem(@PathVariable Long id, @RequestBody FoodItem foodItem) {
        FoodItem updatedItem = service.updateItem(id, foodItem);

        if (updatedItem != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(updatedItem);
        }

        return ResponseEntity.notFound()
                .build();
    }

    //DELETE

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        FoodItem foodItem = service.findById(id);

        if (foodItem != null){
            service.deleteItem(id);
            return ResponseEntity.ok("Item successfully deleted");
        }

        return ResponseEntity.notFound()
                .build();
    }
}
