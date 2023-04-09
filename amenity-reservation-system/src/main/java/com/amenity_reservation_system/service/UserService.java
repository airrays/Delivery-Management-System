package com.amenity_reservation_system.service;

import com.amenity_reservation_system.model.User;
import com.amenity_reservation_system.repos.UserRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll(Sort.by("id"));
//        final List<User> users = userRepository.findAll(Sort.by("id"));
//        return users.stream()
//                .map((user) -> mapToDTO(user, new User()))
//                .collect(Collectors.toList());
    }

    public User get(final Long id) {
        return userRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
//        return userRepository.findById(id)
//                .map(user -> mapToDTO(user, new User()))
//                .orElseThrow(NotFoundException::new);
    }

    public Long create(final User user) {
        return userRepository.save(user).getId();
    }

    public void update(final Long id, final User user) {
        final User existingUser=userRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRepository.save(user);
//        final User user = userRepository.findById(id)
//                .orElseThrow(NotFoundException::new);
//        mapToEntity(User, user);
//        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private User mapToDTO(final User user, final User User) {
        User.setId(user.getId());
        return User;
    }

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
    public User getUserByFullName(String fullname){
        return userRepository.findUserByFullName(fullname);
    }
//
//    private User mapToEntity(final User User, final User user) {
//        return user;
//    }

}
