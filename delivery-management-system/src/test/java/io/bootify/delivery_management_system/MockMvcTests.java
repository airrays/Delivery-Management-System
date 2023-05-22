package io.bootify.delivery_management_system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc  // depend on junit5
public class MockMvcTests {
    @Autowired
    MockMvc mockMvc;

    //not rely on internet, web service
//    @Test
//    void testMockMvcGet() throws Exception {
//        mockMvc.perform(
//                        MockMvcRequestBuilders.get("/employee/page")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .param("page","1")
//                                .param("pageSize","1")
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                //.andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("admin"))
//                .andDo(MockMvcResultHandlers.print());
//    }
}