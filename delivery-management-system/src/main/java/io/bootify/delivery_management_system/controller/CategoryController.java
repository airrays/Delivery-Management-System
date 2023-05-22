package io.bootify.delivery_management_system.controller;

import io.bootify.delivery_management_system.common.R;
import io.bootify.delivery_management_system.domain.Category;
import io.bootify.delivery_management_system.repos.CategoryRepository;
import io.bootify.delivery_management_system.service.CategoryServiceImpl;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Dish,Meal Category Handle
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    /***
     * New Category
     */
    @PostMapping
    public ResponseEntity<R> save(@RequestBody Category category){
        log.info("Saving category:{}",category);
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.OK).body(R.success("New Category Saved"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findAll());
    }
    /***
     * /category/page?page=1&pageSize=10
     * getContent() to retrieve the List of items in the page.
     * getNumber() for current Page.
     * getTotalElements() for total items stored in database.
     * getTotalPages() for number of total pages.
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<Map<String,Object>> page(@RequestParam(defaultValue="0")int page,@RequestParam(defaultValue="10") int pageSize,@RequestParam(defaultValue="sort,desc")String[] sort){
        try{
            List<Sort.Order>orders=new ArrayList<>();
            List<Category> categories=new ArrayList<Category>();

            if(sort[0].contains(",")) {
                //will sort more than 2 fields
                //sortOrder="field,direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    Sort.Direction direction = _sort[1].contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
                    orders.add(new Sort.Order(direction, _sort[0]));
                }
            }
            else
            {
                //sort=[field,direction]
                Sort.Direction direction=sort[1].contains("desc")? Sort.Direction.DESC : Sort.Direction.ASC;
                orders.add(new Sort.Order(direction,sort[0]));
            }

            //This is how we create Pageable objects using PageRequest class which implements Pageable interface
            Pageable pageable= PageRequest.of(page,pageSize, Sort.by(orders));
            Page<Category> pageCats;
            pageCats = categoryRepository.findAll(pageable);
            categories=pageCats.getContent();
            if(categories.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<String,Object>response=new HashMap<>();
            response.put("categories",categories);
            response.put("currentPage",pageCats.getNumber());
            response.put("totoalItems",pageCats.getTotalElements());
            response.put("totalPages",pageCats.getTotalPages());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /***
     * /category?ids=13978888
     * if the category is relating dish or meal, it cannot be deleted
     * @param ids
     * @return
     */
    @GetMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<R> delete(@PathVariable("id") Long ids){
        log.info("Delete Category, id: {}", ids);
        //categoryRepository.deleteById(ids);
        categoryServiceImpl.remove(ids);

        //return ResponseEntity.status(HttpStatus.OK).body(R.success("Deleted Category "+ids));\
        return new ResponseEntity<>(R.success("Deleted Category "+ids),HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<R> update(@RequestBody Category category){
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
