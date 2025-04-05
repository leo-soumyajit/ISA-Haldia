package com.soumyajit.ISA.HIT.HALDIA.Repository;

import com.soumyajit.ISA.HIT.HALDIA.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.posts WHERE u.id = :id")
    Optional<User> findByIdWithPosts(Long id);

    List<User> findByNameContainingIgnoreCase(String userName);
}
