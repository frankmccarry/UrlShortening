package org.wildfly.quickstarts.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.wildfly.quickstarts.model.NipURL;
import org.wildfly.quickstarts.model.endpoint.AccountRepository;
import org.wildfly.quickstarts.model.endpoint.UrlRepository;
import org.wildfly.quickstarts.model.entity.Account;
import org.wildfly.quickstarts.model.entity.Url;
import org.wildfly.quickstarts.shorten.ShortenUrl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Path("/urls")
public class URLShortenService {
    @Inject
    AccountRepository accountRepository;

    @Inject
    UrlRepository urlRepository;

    @GET
    @Path("/")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response getOptions() {
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity("").build();
    }

    @GET
    @Path("/{accountId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<NipURL> getShortUrls(@PathParam("accountId") Long accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account!= null) {
            if (account.getUrls() != null) {
                return account.getUrls().stream().map(url -> new NipURL(url.getShortUrl(), "0", "0", url.getLongUrl())).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    @POST
    @Path("/{accountId}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response createUrl(@PathParam("accountId") Long accountId, NipURL url) {

        int counter = 0;
        String valueToEncode = url.getLongUrl();
        while (counter < 10) {
            String shortUrl = ShortenUrl.shortenUrl(valueToEncode);
            Url storedNipUrl = urlRepository.findUrlByShortUrl(shortUrl);
            counter++;
            if (storedNipUrl == null) {
                url.setShortUrl(shortUrl);
                break;
            }
            valueToEncode = shortUrl;
        }
        if (url.getRetention() == null) {
            url.setRetention("3");
        }

        Account account = accountRepository.findAccountById(accountId);
        account.getUrls().add(new Url(null, url.getShortUrl(), Integer.parseInt(url.getRetention()), 0, url.getLongUrl()));

        accountRepository.updateAccount(account);
        return Response.status(Response.Status.CREATED).entity(url).build();
    }

    @PUT
    @Path("/{accountId}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateUrl(@PathParam("accountId") Long accountId, NipURL url) {
        Account account = accountRepository.findAccountById(accountId);
        List<Url> urls = account.getUrls().stream().filter(u -> u.getShortUrl().equals(url.getShortUrl())).collect(Collectors.toList());
        if (!urls.isEmpty()) {
            Url urlToUpdate = urls.get(0);
            urlToUpdate.setLongUrl(url.getLongUrl());
            urlToUpdate.setShortUrl(url.getShortUrl());
            urlToUpdate.setRetention(Integer.parseInt(url.getRetention()));
            urlToUpdate.setVisited(Integer.parseInt(url.getVisited()));
        } else {
            throw new NotFoundException("Stored URL not found");
        }
        accountRepository.updateAccount(account);

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{accountId}")
    public Response deleteUrl(@PathParam("accountId") Long accountId, NipURL url) {
        Account account = accountRepository.findAccountById(accountId);

        List<Url> urls = account.getUrls().stream().filter(u -> !u.getShortUrl().equals(url.getShortUrl())).collect(Collectors.toList());
        account.setUrls(urls);
        accountRepository.updateAccount(account);

        return Response.status(Response.Status.OK).build();
    }
}
