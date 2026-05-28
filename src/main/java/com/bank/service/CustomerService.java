package com.bank.service;

import com.bank.dao.CustomerDao;
import com.bank.dto.Customer;
import com.bank.util.JpaUtil;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CustomerService {

    private final CustomerDao customerDao = new CustomerDao();

    public void save(Customer customer) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            customerDao.save(em, customer);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close(); // ✅ important
        }
    }

    public void update(Customer customer) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            customerDao.update(em, customer);
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
            customerDao.delete(em, id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    public Customer findById(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Customer customer = null;

        try {
            transaction.begin();
            customer = customerDao.findById(em, id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }

        return customer;
    }

    public List<Customer> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<Customer> customers = null;

        try {
            transaction.begin();
            customers = customerDao.findAll(em);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }

        return customers;
    }
}