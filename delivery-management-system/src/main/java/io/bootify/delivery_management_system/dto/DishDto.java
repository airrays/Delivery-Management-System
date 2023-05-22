package io.bootify.delivery_management_system.dto;

import io.bootify.delivery_management_system.domain.Dish;
import io.bootify.delivery_management_system.domain.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {
    private List<DishFlavor>flavors=new ArrayList<>();
    private String CategoryName;
    private Integer copies;
}
