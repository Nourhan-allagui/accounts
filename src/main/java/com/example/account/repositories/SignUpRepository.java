package com.example.account.repositories;

import com.example.account.entities.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignUpRepository extends JpaRepository<UserInformation, Long> {
    Optional<UserInformation> findByUsername(String username);
    Optional<UserInformation> findByEmail(String email);
    @Query("SELECT u FROM UserInformation u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<UserInformation> findOneWithRolesByLoginIgnoreCase(String email);
}
