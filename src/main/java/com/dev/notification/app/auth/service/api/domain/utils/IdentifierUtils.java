package com.dev.notification.app.auth.service.api.domain.utils;

import java.util.UUID;

public class IdentifierUtils {

    private IdentifierUtils() {}

    public static String unique(){
        return UUID.randomUUID().toString();
    }
}
