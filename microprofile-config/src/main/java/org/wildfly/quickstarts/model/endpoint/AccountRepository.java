package org.wildfly.quickstarts.model.endpoint;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import org.wildfly.quickstarts.model.entity.Account;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Account> findAll() {
        return entityManager.createNamedQuery("Accounts.findAll", Account.class).getResultList();
    }

    public Account findAccountById(Long id) {
        Account account = entityManager.find(Account.class, id);

        if (account == null) {
            throw new WebApplicationException("Account with id of " + id + " does not exist.", 404);
        }
        return account;
    }

    @Transactional
    public void updateAccount(Account account) {
        entityManager.merge(account);
    }

    @Transactional
    public void createAccount(Account account) {
        entityManager.persist(account);
    }

    @Transactional
    public void deleteAccount(Long accountId) {
        Account account = findAccountById(accountId);
        entityManager.remove(account);
    }
}
