package com.cmcni.sales_management_system_backend.domain.user.repository;

import com.cmcni.sales_management_system_backend.domain.user.entity.UserRoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleTypeRepository extends JpaRepository<UserRoleType, Long> {
}
