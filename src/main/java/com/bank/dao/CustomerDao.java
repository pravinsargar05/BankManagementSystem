package com.bank.dao;

import com.bank.dto.Customer;
import javax.persistence.EntityManager;
import java.util.List;

public class CustomerDao {

    public void save(EntityManager em, Customer customer) {
        em.persist(customer);
    }

    public void update(EntityManager em, Customer customer) {
        em.merge(customer);
    }

    public void delete(EntityManager em, Long id) {
        Customer customer = em.find(Customer.class, id);
        if (customer != null) {
            em.remove(customer);
        }
    }

    public Customer findById(EntityManager em, Long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll(EntityManager em) {
        return em.createQuery("from Customer", Customer.class).getResultList();
    }
}
