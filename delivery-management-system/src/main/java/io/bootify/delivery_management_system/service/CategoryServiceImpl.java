package io.bootify.delivery_management_system.service;

import exception.CustomException;
import io.bootify.delivery_management_system.repos.CategoryRepository;
import io.bootify.delivery_management_system.repos.DishRepository;
import io.bootify.delivery_management_system.repos.SetmealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private SetmealRepository setmealRepository;
    @Autowired
    private DishServiceImpl dishServiceImpl;
    @Autowired
    private CategoryRepository categoryRepository;

    public void remove(Long id){
        int count=dishServiceImpl.dishCatCtn(id);
        if(count>0){
            throw new CustomException("Already Category-Dish Linked, Cannot be deleted");
        }
        else if (setmealRepository.setMealCatCtn(id)>0){
            throw new CustomException("Already Category-SetMeal Linked, Cannot be deleted");
        }else {
            categoryRepository.deleteById(id);
        }
    }
}
