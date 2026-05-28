package com.bank.dao;

import com.bank.dto.Account;
import java.util.List;

import javax.persistence.EntityManager;

public class AccountDao {

    public void save(EntityManager em, Account account) {
        em.persist(account);
    }

    public void update(EntityManager em, Account account) {
        em.merge(account);
    }

    public void delete(EntityManager em, Long id) {
        Account account = em.find(Account.class, id);
        if (account != null) {
            em.remove(account);
        }
    }

    public Account findById(EntityManager em, Long id) {
        return em.find(Account.class, id);
    }

    public List<Account> findAll(EntityManager em) {
        return em.createQuery("from Account", Account.class).getResultList();
    }
}
