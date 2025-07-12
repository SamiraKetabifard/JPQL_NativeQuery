package com.example.jpql_nativequery.service;

import com.example.jpql_nativequery.dto.UserDto;
import com.example.jpql_nativequery.entity.User;
import com.example.jpql_nativequery.mapper.UserMapper;
import com.example.jpql_nativequery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getActiveUsersJPA() {
        List<User> activeUsers = userRepository.findByActiveTrue();
        return userMapper.toDtoList(activeUsers);
    }
    public List<UserDto> getActiveUsersJPQL() {
        List<User> activeUsers = userRepository.findByActiveTrueJPQL();
        return userMapper.toDtoList(activeUsers);
    }
    public List<UserDto> getActiveUsersNative() {
        List<User> activeUsers = userRepository.findByActiveTrueNative();
        return userMapper.toDtoList(activeUsers);
    }
    public List<UserDto> getUsernameAndActiveTrueJPA(String username) {
        List<User> users = userRepository.findByUsernameAndActiveTrue(username);
        return userMapper.toDtoList(users);
    }
    public List<UserDto> getUsernameAndActiveTrueJPQL(String username) {
        List<User> users = userRepository.findByUsernameAndActiveTrueJPQL(username);
        return userMapper.toDtoList(users);
    }
    public List<UserDto> getUsernameAndActiveTrueNative(String username) {
        List<User> users = userRepository.findByUsernameAndActiveTrueNative(username);
        return userMapper.toDtoList(users);
    }
    public long getCountActiveUsers() {
        return userRepository.countByActiveTrue();
    }
    public long getCountActiveUsersJPQL() {
        return userRepository.countByActiveTrueJPQL();
    }
    public long getCountActiveUsersNative() {
        return userRepository.countByActiveTrueNative();
    }
}
