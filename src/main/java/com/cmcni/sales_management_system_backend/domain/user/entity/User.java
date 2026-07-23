package com.cmcni.sales_management_system_backend.domain.user.entity;

import com.cmcni.sales_management_system_backend.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_role_type_id", comment = "권한")
    private UserRoleType userRoleType;

    @Column(comment = "이메일 주소")
    private String emailAddress;

    @Column(comment = "비밀번호")
    private String password;

    @Column(comment = "성명")
    private String name;

    @Column(comment = "휴대폰 번호")
    private String phoneNumber;

    private String accessToken;
    private String refreshToken;

    public User() {}

    public User(UserRoleType userRoleType, String emailAddress, String password, String name, String phoneNumber) {
        this.userRoleType = userRoleType;
        this.emailAddress = emailAddress;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void setUserRoleType(UserRoleType userRoleType) {
        this.userRoleType = userRoleType;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
