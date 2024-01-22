package org.wildfly.quickstarts.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQuery(
        name="findUrlWithShortUrl",
        query="SELECT u FROM Url u WHERE u.shortUrl = :shortUrl"
)
@Table(name="url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "short_url", unique = true)
    private String shortUrl;

    @Column(name = "retention")
    private int retention;

    @Column(name = "visited")
    private int visited;

    @Column(name = "long_url")
    private String longUrl;

    public Url() {
    }

    public Url(Long id, String shortUrl, int retention, int visited, String longUrl) {
        this.id = id;
        this.shortUrl = shortUrl;
        this.retention = retention;
        this.visited = visited;
        this.longUrl = longUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getRetention() {
        return retention;
    }

    public void setRetention(int retention) {
        this.retention = retention;
    }

    public int getVisited() {
        return visited;
    }

    public void setVisited(int visited) {
        this.visited = visited;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
