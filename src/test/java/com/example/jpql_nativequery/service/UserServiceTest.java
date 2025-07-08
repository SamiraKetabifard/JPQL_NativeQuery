package com.example.jpql_nativequery.service;
import com.example.jpql_nativequery.dto.UserDto;
import com.example.jpql_nativequery.entity.User;
import com.example.jpql_nativequery.mapper.UserMapper;
import com.example.jpql_nativequery.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void getActiveUsersJPA_ShouldReturnActiveUsers() {
        //arrange
        User activeUser1 = new User("samira", true, 45000, null);
        User activeUser2 = new User("reza", true, 35000, null);
        List<User> mockActiveUsers = Arrays.asList(activeUser1, activeUser2);

        when(userRepository.findByActiveTrue()).thenReturn(mockActiveUsers);
        when(userMapper.toDtoList(mockActiveUsers)).thenReturn(
                Arrays.asList(new UserDto(), new UserDto())
        );
        //act
        List<UserDto> result = userService.getActiveUsersJPA();
        //assert
        assertEquals(2, result.size());
        verify(userRepository).findByActiveTrue();
    }
    @Test
    void getUsernameAndActiveTrueJPA_ShouldReturnSpecificUser() {
        User specificUser = new User("samira", true, 45000, null);
        when(userRepository.findByUsernameAndActiveTrue("samira"))
                .thenReturn(Arrays.asList(specificUser));
        when(userMapper.toDtoList(anyList())).thenReturn(
                Arrays.asList(new UserDto()));

        List<UserDto> result = userService.getUsernameAndActiveTrueJPA("samira");

        assertEquals(1, result.size());
        verify(userRepository).findByUsernameAndActiveTrue("samira");
    }
    @Test
    void countActiveUsers_ShouldReturnCorrectCount() {
        when(userRepository.countByActiveTrue()).thenReturn(4L);
        long count = userService.getCountActiveUsers();
        assertEquals(4, count);
    }
}