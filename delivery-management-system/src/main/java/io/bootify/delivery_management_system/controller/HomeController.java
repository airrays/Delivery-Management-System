package io.bootify.delivery_management_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    @RequestMapping("/ini")
    @ResponseBody
    public String sayHi(){
        return "Heell";
    }

//    @GetMapping("/api/illegalArgumentException")
//    public void throwException() {
//        throw new IllegalArgumentException();
//    }

//    @GetMapping("/api/resourceNotFoundException")
//    public void throwException2() {
//        throw new ResourceNotFoundException();
//    }

//    @GetMapping("/resourceNotFound")
//    public void throwException() {
//        Person p=new Person(1L,"SnailClimb");
//        throw new ResourceNotFoundException(ImmutableMap.of("person id:", p.getId()));
//    }
//
//    @GetMapping("/api/resourceNotFoundException2")
//    public void throwException3() {
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, the resourse not found!", new ResourceNotFoundException());
//    }

//    @Deprecated
//    @RequestMapping(value = { "/admin/oldLogin" })
//    public String oldLogin(Model model) {
//
//        // Code here never run.
//        return "oldLogin";
//    }
}
