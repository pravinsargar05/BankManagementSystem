package com.bank.dao;

import com.bank.dto.Branch;
import javax.persistence.EntityManager;
import java.util.List;

public class BranchDao {

    public void save(EntityManager em, Branch branch) {
        em.persist(branch);
    }

    public void update(EntityManager em, Branch branch) {
        em.merge(branch);
    }

    public void delete(EntityManager em, Long id) {
        Branch branch = em.find(Branch.class, id);
        if (branch != null) {
            em.remove(branch);
        }
    }

    public Branch findById(EntityManager em, Long id) {
        return em.find(Branch.class, id);
    }

    public List<Branch> findAll(EntityManager em) {
        return em.createQuery("from Branch", Branch.class).getResultList();
    }
}
