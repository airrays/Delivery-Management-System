package com.amenity_reservation_system.service;

import com.amenity_reservation_system.exception.CapacityFullException;
import com.amenity_reservation_system.model.Capacity;
import com.amenity_reservation_system.model.Reservation;
import com.amenity_reservation_system.repos.CapacityRepository;
import com.amenity_reservation_system.repos.ReservationRepository;
import com.amenity_reservation_system.repos.UserRepository;
import com.amenity_reservation_system.util.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final CapacityRepository capacityRepository;

    public ReservationService(final ReservationRepository reservationRepository,
            final UserRepository userRepository, final CapacityRepository capacityRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.capacityRepository=capacityRepository;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll(Sort.by("id"));
//        final List<Reservation> reservations = reservationRepository.findAll(Sort.by("id"));
//        return reservations.stream()
//                .map((reservation) -> mapToDTO(reservation, new Reservation()))
//                .collect(Collectors.toList());
    }

    public Reservation get(final Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final Reservation reservation) {
//        final Reservation reservation = new Reservation();
//        mapToEntity(Reservation, reservation);
        int currentCapacity=capacityRepository.findByAmenityType(reservation.getAmenityType()).getCapacity();
        //List<Reservation> findReservationsByReservationDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween
        //            (LocalDate reservationDate, LocalTime startTime, LocalTime endTime, LocalTime betweenStart, LocalTime betweenEnd);
        int overLappingReservations=reservationRepository.findReservationsByReservationDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween(
                reservation.getReservationDate(), reservation.getStartTime(), reservation.getEndTime(), reservation.getStartTime(), reservation.getEndTime()).size();
        if(overLappingReservations>=currentCapacity){
            //Throw a custom exception
            throw new CapacityFullException("This amenity's capacity is full at desired time");
        }
        return reservationRepository.save(reservation).getId();
    }

//    private List<Reservation>findReservationsByReservationDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween(LocalDate reservationDate, LocalTime startTime, LocalTime endTime, LocalTime betweenStart, LocalTime betweenEnd){
//        return re
//    }
    public void update(final Long id, final Reservation reservation) {
        final Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        //mapToEntity(Reservation, reservation);
        reservationRepository.save(reservation);
    }

    public void delete(final Long id) {
        reservationRepository.deleteById(id);
    }
    public List<Reservation> findByName(String name){
        return reservationRepository.findByName(name);
    }
//    private Reservation mapToDTO(final Reservation reservation,
//            final Reservation Reservation) {
//        Reservation.setId(reservation.getId());
//        Reservation.setReservationDate(reservation.getReservationDate());
//        Reservation.setStartTime(reservation.getStartTime());
//        Reservation.setEndTime(reservation.getEndTime());
//        Reservation.setUser(reservation.getUser() == null ? null : reservation.getUser().getId());
//        return Reservation;
//    }

//    private Reservation mapToEntity(final Reservation reservation,
//            final Reservation reservation) {
//        reservation.setReservationDate(Reservation.getReservationDate());
//        reservation.setStartTime(Reservation.getStartTime());
//        reservation.setEndTime(Reservation.getEndTime());
//        final User user = Reservation.getUser() == null ? null : userRepository.findById(Reservation.getUser())
//                .orElseThrow(() -> new NotFoundException("user not found"));
//        reservation.setUser(user);
//        return reservation;
//    }

}
