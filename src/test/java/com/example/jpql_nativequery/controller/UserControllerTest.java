package com.example.jpql_nativequery.controller;

import com.example.jpql_nativequery.dto.UserDto;
import com.example.jpql_nativequery.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDto testUser;
    private List<UserDto> testUsers;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        testUser = new UserDto();
        testUser.setUsername("samira");
        testUsers = Collections.singletonList(testUser);
    }
    // Happy Path Tests
    @Test
    void getActiveUsersJPA_ShouldReturn200AndUsers() throws Exception {
        when(userService.getActiveUsersJPA()).thenReturn(testUsers);

        mockMvc.perform(get("/users/jpa/active")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("samira"));

        verify(userService).getActiveUsersJPA();
    }
    @Test
    void getActiveUsersJPQL_ShouldReturn200() throws Exception {
        when(userService.getActiveUsersJPQL()).thenReturn(testUsers);

        mockMvc.perform(get("/users/jpql/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(userService).getActiveUsersJPQL();
    }
    @Test
    void getActiveUsersNative_ShouldReturn200() throws Exception {
        when(userService.getActiveUsersNative()).thenReturn(testUsers);

        mockMvc.perform(get("/users/native/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("samira"));

        verify(userService).getActiveUsersNative();
    }
    @Test
    void findByUsernameAndActiveTrue_ShouldReturn200() throws Exception {
        when(userService.getUsernameAndActiveTrueJPA("samira")).thenReturn(testUsers);

        mockMvc.perform(get("/users/jpa/findByUsernameAndActiveTrue")
                        .param("username", "samira"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("samira"));

        verify(userService).getUsernameAndActiveTrueJPA("samira");
    }
    @Test
    void findByUsernameAndActiveTrueJPQL_ShouldReturn200() throws Exception {
        when(userService.getUsernameAndActiveTrueJPQL("samira")).thenReturn(testUsers);

        mockMvc.perform(get("/users/jpql/findByUsernameAndActiveTrue")
                        .param("username", "samira"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("samira"));

        verify(userService).getUsernameAndActiveTrueJPQL("samira");
    }
    @Test
    void findByUsernameAndActiveTrueNative_ShouldReturn200() throws Exception {
        when(userService.getUsernameAndActiveTrueNative("samira")).thenReturn(testUsers);

        mockMvc.perform(get("/users/native/findByUsernameAndActiveTrue")
                        .param("username", "samira"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("samira"));

        verify(userService).getUsernameAndActiveTrueNative("samira");
    }
    @Test
    void countActiveUsersJPA_ShouldReturn200() throws Exception {
        when(userService.getCountActiveUsers()).thenReturn(5L);

        mockMvc.perform(get("/users/jpa/countActiveUsers"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));

        verify(userService).getCountActiveUsers();
    }
    @Test
    void countActiveUsersJPQL_ShouldReturn200() throws Exception {
        when(userService.getCountActiveUsersJPQL()).thenReturn(5L);

        mockMvc.perform(get("/users/jpql/countActiveUsers"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));

        verify(userService).getCountActiveUsersJPQL();
    }
    @Test
    void countActiveUsersNative_ShouldReturn200() throws Exception {
        when(userService.getCountActiveUsersNative()).thenReturn(5L);

        mockMvc.perform(get("/users/native/countActiveUsers"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));

        verify(userService).getCountActiveUsersNative();
    }
    // Unhappy Path Tests
    @Test
    void findByUsernameAndActiveTrue_ShouldReturn400_WhenMissingUsername() throws Exception {
        mockMvc.perform(get("/users/jpa/findByUsernameAndActiveTrue"))
                .andExpect(status().isBadRequest());

        verify(userService, never()).getUsernameAndActiveTrueJPA(any());
    }
    @Test
    void findByUsernameAndActiveTrueJPQL_ShouldReturn400_WhenMissingUsername() throws Exception {
        mockMvc.perform(get("/users/jpql/findByUsernameAndActiveTrue"))
                .andExpect(status().isBadRequest());

        verify(userService, never()).getUsernameAndActiveTrueJPQL(any());
    }
    @Test
    void findByUsernameAndActiveTrueNative_ShouldReturn400_WhenMissingUsername() throws Exception {
        mockMvc.perform(get("/users/native/findByUsernameAndActiveTrue"))
                .andExpect(status().isBadRequest());

        verify(userService, never()).getUsernameAndActiveTrueNative(any());
    }
}