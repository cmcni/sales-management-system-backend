package com.cmcni.sales_management_system_backend.common.security;

import org.springframework.http.HttpMethod;

import java.util.Arrays;

public class SecurityConfigPermitAllRepository {
    private SecurityConfigPermitAllRepository() {}
    private static final String ROOT_PATH = "/";
    private static final PermitAllPath[] permitAllPaths = {
            new PermitAllPath(HttpMethod.POST, ROOT_PATH + "authentication/login"),
            new PermitAllPath(HttpMethod.POST, ROOT_PATH + "user/sign-up"),
            new PermitAllPath(HttpMethod.GET, ROOT_PATH + "user/sign-up/role-type"),
    };

    public static String[] getPaths(HttpMethod httpMethod) {
        return Arrays.stream(permitAllPaths)
                .filter(permitAllPath -> permitAllPath.httpMethod().equals(httpMethod))
                .map(PermitAllPath::path)
                .toArray(String[]::new);
    }

    private record PermitAllPath(HttpMethod httpMethod, String path) {}
}

