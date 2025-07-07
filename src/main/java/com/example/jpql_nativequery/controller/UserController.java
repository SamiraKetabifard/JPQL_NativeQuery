package com.example.jpql_nativequery.controller;

import com.example.jpql_nativequery.dto.UserDto;
import com.example.jpql_nativequery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/jpa/active")
    public List<UserDto> getActiveUsersJPA() {
        return userService.getActiveUsersJPA();
    }

    @GetMapping("/jpql/active")
    public List<UserDto> getActiveUsersJPQL() {
        return userService.getActiveUsersJPQL();
    }

    @GetMapping("/native/active")
    public List<UserDto> getActiveUsersNative() {
        return userService.getActiveUsersNative();
    }

    @GetMapping("/jpa/findByUsernameAndActiveTrue")
    public List<UserDto> findByUsernameAndActiveTrue(@RequestParam String username) {
        return userService.getUsernameAndActiveTrueJPA(username);
    }

    @GetMapping("/jpql/findByUsernameAndActiveTrue")
    public List<UserDto> findByUsernameAndActiveTrueJPQL(@RequestParam String username) {
        return userService.getUsernameAndActiveTrueJPQL(username);
    }

    @GetMapping("/native/findByUsernameAndActiveTrue")
    public List<UserDto> findByUsernameAndActiveTrueNative(@RequestParam String username) {
        return userService.getUsernameAndActiveTrueNative(username);
    }

    @GetMapping("/jpa/countActiveUsers")
    public long countActiveUsersJPA() {
        return userService.getCountActiveUsers();
    }

    @GetMapping("/jpql/countActiveUsers")
    public long countActiveUsersJPQL() {
        return userService.getCountActiveUsersJPQL();
    }

    @GetMapping("/native/countActiveUsers")
    public long countActiveUsersNative() {
        return userService.getCountActiveUsersNative();
    }
}