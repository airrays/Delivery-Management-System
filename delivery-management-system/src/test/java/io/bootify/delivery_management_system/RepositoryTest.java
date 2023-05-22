package io.bootify.delivery_management_system;

import io.bootify.delivery_management_system.domain.Dish;
import io.bootify.delivery_management_system.domain.Employee;
import io.bootify.delivery_management_system.repos.DishRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@DataJpaTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {
    @Autowired
    DishRepository dishRepository;
    private Dish dish;
//    @Autowired
//    private TestEntityManager entityManager;
//    @Test
//    public void contextLoads() {
//        Assertions.assertNotNull(entityManager);
//    }
//    @BeforeEach
//    public void setUp() {
//        assertNotNull(dishRepository);
//        Dish dish=new Dish();
//        dish.setId(99999L);
//        dish.setName("Test1Name");
//        dishRepository.save(dish);
//    }
//    @Test
//    public void dish_dishFlavor_queryTest() throws Exception {
//        List<Dish> dishOptional=dishRepository.findAll();
//        dishOptional.forEach(System.out::println);
//    }
}
