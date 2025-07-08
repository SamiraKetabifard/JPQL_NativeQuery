package com.example.jpql_nativequery.repository;

import com.example.jpql_nativequery.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User activeUser;
    private User inactiveUser;

    @BeforeEach
    public void setUp() {
        // ایجاد نمونه‌های تست
        activeUser = new User();
        activeUser.setUsername("activeUser");
        activeUser.setActive(true);

        inactiveUser = new User();
        inactiveUser.setUsername("inactiveUser");
        inactiveUser.setActive(false);

        // ذخیره‌سازی داده‌ها در دیتابیس
        userRepository.save(activeUser);
        userRepository.save(inactiveUser);
    }

    @Test
    public void testFindByActiveTrue() {
        List<User> users = userRepository.findByActiveTrue();

        assertEquals(1, users.size());
        assertTrue(users.contains(activeUser));
        assertFalse(users.contains(inactiveUser));
    }

    @Test
    public void testFindByUsernameAndActiveTrue() {
        List<User> users = userRepository.findByUsernameAndActiveTrue("activeUser");

        assertEquals(1, users.size());
        assertTrue(users.contains(activeUser));
        assertFalse(users.contains(inactiveUser));
    }

    @Test
    public void testCountByActiveTrue() {
        long count = userRepository.countByActiveTrue();

        assertEquals(1, count); // فقط activeUser باید شمرده شود
    }

    @Test
    public void testFindByActiveTrueJPQL() {
        List<User> users = userRepository.findByActiveTrueJPQL();

        assertEquals(1, users.size());
        assertTrue(users.contains(activeUser));
        assertFalse(users.contains(inactiveUser));
    }

    @Test
    public void testFindByUsernameAndActiveTrueJPQL() {
        List<User> users = userRepository.findByUsernameAndActiveTrueJPQL("activeUser");

        assertEquals(1, users.size());
        assertTrue(users.contains(activeUser));
        assertFalse(users.contains(inactiveUser));
    }

    @Test
    public void testCountByActiveTrueJPQL() {
        long count = userRepository.countByActiveTrueJPQL();

        assertEquals(1, count); // فقط activeUser باید شمرده شود
    }

    @Test
    public void testFindByActiveTrueNative() {
        List<User> users = userRepository.findByActiveTrueNative();

        assertEquals(1, users.size());
        assertTrue(users.contains(activeUser));
        assertFalse(users.contains(inactiveUser));
    }

    @Test
    public void testFindByUsernameAndActiveTrueNative() {
        List<User> users = userRepository.findByUsernameAndActiveTrueNative("activeUser");

        assertEquals(1, users.size());
        assertTrue(users.contains(activeUser));
        assertFalse(users.contains(inactiveUser));
    }

    @Test
    public void testCountByActiveTrueNative() {
        long count = userRepository.countByActiveTrueNative();

        assertEquals(1, count); // فقط activeUser باید شمرده شود
    }
}
