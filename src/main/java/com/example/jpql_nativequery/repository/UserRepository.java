package com.example.jpql_nativequery.repository;

import com.example.jpql_nativequery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //JPA
    List<User> findByActiveTrue();

    List<User> findByUsernameAndActiveTrue(String username);

    long countByActiveTrue();

    //JPQL
    @Query("SELECT u FROM User u WHERE u.active = true")
    List<User> findByActiveTrueJPQL();

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.active = true")
    List<User> findByUsernameAndActiveTrueJPQL(@Param("username") String username);

    @Query("SELECT COUNT(u) FROM User u WHERE u.active = true")
    long countByActiveTrueJPQL();

    //Native-query
    @Query(value = "SELECT * FROM user WHERE active = true", nativeQuery = true)
    List<User> findByActiveTrueNative();

    @Query(value = "SELECT * FROM user WHERE username = :username AND active =true", nativeQuery = true)
    List<User> findByUsernameAndActiveTrueNative(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM user WHERE active = true", nativeQuery = true)
    long countByActiveTrueNative();
}