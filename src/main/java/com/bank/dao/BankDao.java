package com.bank.dao;

import com.bank.dto.Bank;
import java.util.List;

import javax.persistence.EntityManager;

public class BankDao {

    public void save(EntityManager em, Bank bank) {
        em.persist(bank);
    }

    public void update(EntityManager em, Bank bank) {
        em.merge(bank);
    }

    public void delete(EntityManager em, Long id) {
        Bank bank = em.find(Bank.class, id);
        if (bank != null) {
            em.remove(bank);
        }
    }

    public Bank findById(EntityManager em, Long id) {
        return em.find(Bank.class, id);
    }

    public List<Bank> findAll(EntityManager em) {
        return em.createQuery("from Bank", Bank.class).getResultList();
    }
}
