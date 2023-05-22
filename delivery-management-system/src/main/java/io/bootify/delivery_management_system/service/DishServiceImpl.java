package io.bootify.delivery_management_system.service;

import io.bootify.delivery_management_system.domain.DishFlavor;
import io.bootify.delivery_management_system.dto.DishDto;
import io.bootify.delivery_management_system.repos.DishFlavorRepository;
import io.bootify.delivery_management_system.repos.DishRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl implements DishService{

    @Autowired
    DishRepository dishRepository;
    @Autowired
    DishFlavorRepository dishFlavorRepository;
    public int dishCatCtn(Long id) {
        int ctn=dishRepository.dishCatCtn(id);
        log.info(Integer.toString(ctn));
        return ctn;
    }

    //TODO
//    @Override
//    @Transactional
//    public void saveWithFlavor(DishDto dishDto){
//        dishRepository.save(dishDto);
//        Long dishid=dishDto.getId();
//        List<DishFlavor> flavors=dishDto.getFlavors();
//        flavors=flavors.stream().map((item)->{
//            item.setDishId(dishid);
//            return item;
//        }).collect(Collectors.toList());
//        dishFlavorRepository.saveAll(flavors);
//    }
}
