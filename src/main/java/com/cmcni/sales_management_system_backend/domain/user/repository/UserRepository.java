package com.cmcni.sales_management_system_backend.domain.user.repository;

import com.cmcni.sales_management_system_backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmailAddress(String emailAddress);
}
