package com.bank.dao;

import com.bank.dto.Loan;
import javax.persistence.EntityManager;
import java.util.List;

public class LoanDao {

    public void save(EntityManager em, Loan loan) {
        em.persist(loan);
    }

    public void update(EntityManager em, Loan loan) {
        em.merge(loan);
    }

    public void delete(EntityManager em, Long id) {
        Loan loan = em.find(Loan.class, id);
        if (loan != null) {
            em.remove(loan);
        }
    }

    public Loan findById(EntityManager em, Long id) {
        return em.find(Loan.class, id);
    }

    public List<Loan> findAll(EntityManager em) {
        return em.createQuery("from Loan", Loan.class).getResultList();
    }
}
