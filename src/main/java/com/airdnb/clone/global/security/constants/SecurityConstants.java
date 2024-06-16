package com.airdnb.clone.global.security.constants;

public interface SecurityConstants {

    String JWT_HEADER = "Authorization";
    String JWT_ISSUER = "airdnb";
    String JWT_SUBJECT = "team04";
    int JWT_EXPIRATION_TIME = 1000 * 60 * 60 * 24;
}
