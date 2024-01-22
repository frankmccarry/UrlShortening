package org.wildfly.quickstarts.model.endpoint;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import org.wildfly.quickstarts.model.entity.Url;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UrlRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Url> findAll() {
        return entityManager.createNamedQuery("Urls.findAll", Url.class).getResultList();
    }

    public Url findUrlByShortUrl(String shortUrl) {
        List<Url> urls = entityManager.createNamedQuery("findUrlWithShortUrl", Url.class).
                setParameter("shortUrl", shortUrl).getResultList();

        if ((null == urls) || (urls.isEmpty())) {
            return null;
        } else {
            return urls.get(0);
        }
    }

    public Url findUrlById(Long id) {
        Url url = entityManager.find(Url.class, id);

        if (url == null) {
            throw new WebApplicationException("Url with id of " + id + " does not exist.", 404);
        }
        return url;
    }

    @Transactional
    public void updateUrl(Url url) {
        entityManager.merge(url);
    }

    @Transactional
    public void createUrl(Url url) {
        entityManager.persist(url);
    }

    @Transactional
    public void deleteUrl(Long urlId) {
        Url url = findUrlById(urlId);
        entityManager.remove(url);
    }
}
