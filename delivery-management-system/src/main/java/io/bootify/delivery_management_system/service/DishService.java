package io.bootify.delivery_management_system.service;

import io.bootify.delivery_management_system.dto.DishDto;
import jakarta.transaction.Transactional;

public interface DishService {
    int dishCatCtn(Long id);

//    @Transactional
//    void saveWithFlavor(DishDto dishDto);
}
