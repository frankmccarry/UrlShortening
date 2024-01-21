package org.wildfly.quickstarts.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NipURL {
    private String shortUrl;
    private String retention;
    private String visited;
    private String longUrl;

    public NipURL() {
    }

    public NipURL(String shortUrl, String retention, String visited, String longUrl) {
        this.shortUrl = shortUrl;
        this.retention = retention;
        this.visited = visited;
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getRetention() {
        return retention;
    }

    public String getVisited() {
        return visited;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setShortUrl(final String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public void setRetention(final String retention) {
        this.retention = retention;
    }

    public void setVisited(final String visited) {
        this.visited = visited;
    }

    public void setLongUrl(final String longUrl) {
        this.longUrl = longUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        NipURL url = (NipURL) o;

        if (shortUrl != null ? !shortUrl.equals(url.shortUrl) : url.shortUrl != null)
            return false;
        return longUrl != null ? longUrl.equals(url.longUrl) : url.longUrl == null;

    }

    @Override
    public int hashCode() {
        int result = shortUrl != null ? shortUrl.hashCode() : 0;
        result = 31 * result + (longUrl != null ? longUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NipUrl{" +
                "shortUrl='" + shortUrl + '\'' +
                ", retention='" + retention + '\'' +
                ", visited='" + visited + '\'' +
                ", longUrl='" + longUrl + '\'' +
                '}';
    }
}
