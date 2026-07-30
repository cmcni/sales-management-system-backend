package com.cmcni.sales_management_system_backend.domain.user.repository;

import com.cmcni.sales_management_system_backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.userRoleType where u.emailAddress = :emailAddress")
    Optional<User> findByEmailAddress(String emailAddress);
}
