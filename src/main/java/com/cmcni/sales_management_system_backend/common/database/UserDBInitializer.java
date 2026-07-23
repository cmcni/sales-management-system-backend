package com.cmcni.sales_management_system_backend.common.database;

import com.cmcni.sales_management_system_backend.domain.user.entity.RoleType;

import com.cmcni.sales_management_system_backend.domain.user.entity.UserRoleType;
import com.cmcni.sales_management_system_backend.domain.user.repository.UserRoleTypeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDBInitializer {

    final private UserRoleTypeRepository userRoleTypeRepository;

    @PostConstruct
    private void init () {
        log.debug("UserDBInitializer 시작!");

        initUserRoleType();

        log.debug("UserDBInitializer 종료!");
    }

    private void initUserRoleType() {
        try {
            final Set<RoleType> roleTypes =
                    userRoleTypeRepository.findAll().stream()
                            .map(UserRoleType::getRoleType)
                            .collect(Collectors.toSet());

            for (RoleType type : RoleType.values()) {
                if (!roleTypes.contains(type)) {
                    final UserRoleType userRoleType = new UserRoleType(type);
                    userRoleTypeRepository.save(userRoleType);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
