package com.amenity_reservation_system.controller;

import com.amenity_reservation_system.model.Reservation;
import com.amenity_reservation_system.model.User;
import com.amenity_reservation_system.service.ReservationService;
import com.amenity_reservation_system.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Set;

//annotate with @Controller instead of @RestController which implies that the controller will return REST response whereas
// a @Controller can return pre-rendered(SSR) views/HTML
@Controller
public class HomeController {

    final UserService userService;
    final ReservationService reservationService;

    public HomeController(UserService userService, ReservationService reservationService) {
        this.userService=userService;
        this.reservationService=reservationService;
    }

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/reservations")
    public String reservations(Model model, HttpSession session) { //( , Principal principal)
        //User user = userService.get(10000L);
        //System.out.println("----- "+user.getFullName());
        UserDetails principal=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name=principal.getUsername();
        User user=userService.getUserByUsername(name);
//        User user=userService.get();
//        session.setAttribute("user",user);
//        Reservation reservation=new Reservation();
//        model.addAttribute("reservation", reservation);
        if(user != null){
            session.setAttribute("user",user);
            Reservation reservation=new Reservation();
            model.addAttribute("reservation",reservation);
            return "reservations";
        }
        return "index";

        /***
         * List<Reservation>reservations=reservationService.listReservations(principal.getName());
         * model.addAttribute("reservations",reservations);
         * return "reservations/list";
         */
    }

    @PostMapping("/reservations-submit")
    public String reservationSubmit(@ModelAttribute Reservation reservation, @SessionAttribute("user") User user){
        //Save to DB after updating
        assert user!=null;
        reservation.setUser(user);
        reservationService.create(reservation);
        Set<Reservation> userReservations=user.getReservations();
        userReservations.add(reservation);
        user.setReservations(userReservations);
        userService.update(user.getId(),user);
        return "redirect:/reservations";
    }
}
