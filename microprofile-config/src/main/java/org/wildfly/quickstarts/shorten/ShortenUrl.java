package org.wildfly.quickstarts.shorten;

import java.util.Base64;

public class ShortenUrl {
    public static final String shortenUrl(String url) {
        if (url.length() < 8) {
            return url;
        }
        String shortUrl = Base64.getUrlEncoder().encodeToString(url.getBytes()).substring(0, 8);
        return shortUrl;
    }
}
