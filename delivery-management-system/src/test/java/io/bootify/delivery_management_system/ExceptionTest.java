package io.bootify.delivery_management_system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureMockMvc
//@SpringBootTest
//public class ExceptionTest {
//    @Autowired
//    MockMvc mockMvc;
//    @Test
//    void should_return_400_if_param_not_valid() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/illegalArgumentException"))
//                .andExpect(status().is(400))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Illegal arguments found!"));
//    }
//
//    @Test
//    void should_return_404_if_resource_not_found() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/resourceNotFoundException"))
//                .andExpect(status().is(404))
//                .andExpect(jsonPath("$.message").value("Sorry, the resourse not Found "));
//    }
//}
