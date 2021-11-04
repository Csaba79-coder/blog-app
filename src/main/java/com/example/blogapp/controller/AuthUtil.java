package com.example.blogapp.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class AuthUtil {

    public static MultiValueMap<String, String> getBasicAuthHeaders() {
        String credentials = new String(Base64.encodeBase64("admin:password".getBytes()));
        MultiValueMap<String, String> headerValues = new LinkedMultiValueMap<>();
        headerValues.add("Authorization", "Basic " + credentials);
        return headerValues;
    }
}
