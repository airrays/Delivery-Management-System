package io.bootify.delivery_management_system.controller;

import exception.ResourceNotFoundException;
import io.bootify.delivery_management_system.domain.Category;
import io.bootify.delivery_management_system.domain.Dish;
import io.bootify.delivery_management_system.domain.DishFlavor;
import io.bootify.delivery_management_system.dto.DishDto;
import io.bootify.delivery_management_system.repos.CategoryRepository;
import io.bootify.delivery_management_system.repos.DishFlavorRepository;
import io.bootify.delivery_management_system.repos.DishRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    private CategoryRepository categoryRepository;
    private DishRepository dishRepository;
    private final DishFlavorRepository dishFlavorRepository;

    public DishController(DishRepository dishRepository, CategoryRepository categoryRepository,
                          DishFlavorRepository dishFlavorRepository){
        this.dishRepository=dishRepository;
        this.categoryRepository=categoryRepository;
        this.dishFlavorRepository = dishFlavorRepository;
    }
    /***
     * Add new Dish:
     * backend/page/food/add.html sends ajax request, request dish cat data and display in drop-down list from server-side
     * page send request for img upload, request server-side save img to server-side
     * page request img download, display upload img
     * click save button, save ajax request, send json dish data to server-side
     */

    @GetMapping("/list")
    public ResponseEntity<?> list(Category category){
        List<Dish>list=dishRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody DishDto dishDto){
        dishRepository.save(dishDto);
        return ResponseEntity.status(HttpStatus.OK).body(dishDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Optional<?> dish=dishRepository.getByIdWithFlavor(id);
        if(dish==null){
            log.error("Dish is NULL RESOURCE NOT FOUND");
        }else {
            log.debug("NOT NULL RESOURCE"+ResponseEntity.status(HttpStatus.OK).body(dish).toString());
        }
        return ResponseEntity.status(HttpStatus.OK).body(dish);
    }

    @GetMapping("/dishFlavor/{id}")
    public ResponseEntity<?> getDishFlavor(@PathVariable Long id){
        List<DishFlavor> dishFlavor=dishFlavorRepository.findByDish_Id(id);
        return new ResponseEntity<>(dishFlavor,HttpStatus.OK);
    }
}
