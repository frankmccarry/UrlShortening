package org.wildfly.quickstarts.service;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.wildfly.quickstarts.model.NipURL;
import org.wildfly.quickstarts.model.endpoint.UrlRepository;
import org.wildfly.quickstarts.model.entity.Url;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@Path("/{shorturl}")
public class RedirectService {
    @Inject
    UrlRepository repository;

    private final LoadingCache<String, NipURL> URL_CACHE = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build(shortUrl -> getNipUrl(repository.findUrlByShortUrl(shortUrl)));

    private NipURL getNipUrl(Url url) {
        if (url == null) {
            return null;
        } else {
            return new NipURL(url.getShortUrl(), String.valueOf(url.getRetention()), String.valueOf(url.getVisited()), url.getLongUrl());
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRedirectResponse(@PathParam("shorturl") String shortUrl) {

        NipURL nipUrl = URL_CACHE.get(shortUrl);
        if (nipUrl == null) {
            throw new NotFoundException("Short URL not found");
        } else {
            URI uri;
            try {
                uri = new URI(nipUrl.getLongUrl());
            } catch (URISyntaxException e) {
                throw new BadRequestException("Invalid Long URL");
            }
            return Response.status(Response.Status.MOVED_PERMANENTLY).location(uri).build();
        }
    }
}
