package com.pedrosantos15.MagicFridgeAI.service;

import com.pedrosantos15.MagicFridgeAI.model.FoodItem;
import com.pedrosantos15.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public FoodItem save(FoodItem foodItem){
        return repository.save(foodItem);
    }

    public List<FoodItem> listAll(){
        return repository.findAll();
    }

    public FoodItem findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public FoodItem updateItem(Long id, FoodItem foodItem){

        if (repository.existsById(id)){
            foodItem.setId(id);
            return repository.save(foodItem);
        }

        return null;
    }

    public void deleteItem(Long id){
        repository.deleteById(id);
    }
}
