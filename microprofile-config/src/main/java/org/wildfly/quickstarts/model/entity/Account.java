package org.wildfly.quickstarts.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@NamedQuery(name = "Accounts.findAll", query = "SELECT acc FROM Account acc ORDER BY acc.id")
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Url.class, cascade = {jakarta.persistence.CascadeType.ALL})
    @JoinTable( name = "account_url",
            joinColumns = { @JoinColumn(name = "account_id")},
            inverseJoinColumns={@JoinColumn(name="url_id")})
    private List<Url> urls;

    public Account() {
    }

    public Account(Long id, String name, List<Url> urls) {
        this.id = id;
        this.name = name;
        this.urls = urls;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }
}
