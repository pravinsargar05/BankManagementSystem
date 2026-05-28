package com.bank.service;

import com.bank.dao.AccountDao;
import com.bank.dto.Account;
import com.bank.util.JpaUtil;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class AccountService {

    private final AccountDao accountDao = new AccountDao();

    public void save(Account account) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            accountDao.save(em, account);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close(); // ✅ important
        }
    }

    public void update(Account account) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            accountDao.update(em, account);
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
            accountDao.delete(em, id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    public Account findById(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Account account = null;

        try {
            transaction.begin();
            account = accountDao.findById(em, id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }

        return account;
    }

    public List<Account> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<Account> accounts = null;

        try {
            transaction.begin();
            accounts = accountDao.findAll(em);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }

        return accounts;
    }
}