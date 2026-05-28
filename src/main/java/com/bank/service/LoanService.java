package com.bank.service;

import com.bank.dao.LoanDao;
import com.bank.dto.Loan;
import com.bank.util.JpaUtil;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class LoanService {

    private final LoanDao loanDao = new LoanDao();

    public void save(Loan loan) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            loanDao.save(em, loan);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close(); // ✅ must
        }
    }

    public void update(Loan loan) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            loanDao.update(em, loan);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            loanDao.delete(em, id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    public Loan findById(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Loan loan = null;

        try {
            transaction.begin();
            loan = loanDao.findById(em, id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }

        return loan;
    }

    public List<Loan> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<Loan> loans = null;

        try {
            transaction.begin();
            loans = loanDao.findAll(em);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }

        return loans;
    }
}