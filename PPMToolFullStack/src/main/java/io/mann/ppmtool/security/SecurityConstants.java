package io.mann.ppmtool.security;

/**
 * @Author: Mann
 * @Date: 2019-06-03 23:01
 */
public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/api/uers/**";

    public static final String H2_URS = "h2-console/**";

    public static final String SECRET = "SecretKeyToGenJWTs";

    public static final String TOKEN_PREFIX = "Mann";

    public static final String HEADER_STRING = "Authorization";

    public static final long EXPIRATION_TIME = 30_000; // 30 seconds
}