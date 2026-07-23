package com.cmcni.sales_management_system_backend.common.security.jwt;


import com.cmcni.sales_management_system_backend.common.security.CustomUserDetails;
import com.cmcni.sales_management_system_backend.domain.user.entity.RoleType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtData {
    private String userId;
    private String tokenId;
    private String tokenType;
    private String email;
    private RoleType roleType;

    public static JwtData create(String userId, String tokenType, String tokenId, String email, RoleType roleType) {
        return JwtData.builder()
            .userId(userId)
            .tokenType(tokenType)
            .tokenId(tokenId)
            .email(email)
            .roleType(roleType)
            .build();
    }

    public boolean isAccess() {
        return this.tokenType.equals(JwtConst.TOKEN_TYPE_ACCESS);
    }
    public boolean isNotAccess() { return !this.isAccess(); }
    public boolean isRefresh() {
        return this.tokenType.equals(JwtConst.TOKEN_TYPE_REFRESH);
    }
    public boolean isNotRefresh() { return !this.isRefresh(); }
    public boolean isTemporal() {
        return this.tokenType.equals(JwtConst.TOKEN_TYPE_TEMPORAL);
    }
    public boolean isNotTemporal() { return !this.isTemporal(); }


    public void verifyAccess() throws RuntimeException {
        if (this.isNotAccess()) {
            throw new RuntimeException("not " + JwtConst.TOKEN_TYPE_ACCESS + " token");
        }
    }

    public void verifyRefresh() throws RuntimeException  {
        if (this.isNotRefresh()) {
            throw new RuntimeException("not " + JwtConst.TOKEN_TYPE_REFRESH + " token");
        }
    }

    public void verifyTemporal() throws RuntimeException  {
        if (!this.isNotTemporal()) {
            throw new RuntimeException("not " + JwtConst.TOKEN_TYPE_TEMPORAL + " token");
        }
    }

    public void verifyTokenId(CustomUserDetails userDetails) throws RuntimeException {

        // 토큰의 jti(tokenId)가 DB에 저장된 rememberToken과 같은지 검증한다.
        if(
            userDetails.getRememberToken() == null ||
            !userDetails.getRememberToken().equals(tokenId)
        ) {
            throw new RuntimeException("token not supported");
        }
    }
}
