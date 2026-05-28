package com.bank.service;

import com.bank.dao.BranchDao;
import com.bank.dto.Branch;
import com.bank.util.JpaUtil;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BranchService {

    private final BranchDao branchDao = new BranchDao();

    public void save(Branch branch) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            branchDao.save(em, branch);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close(); // ✅ important
        }
    }

    public void update(Branch branch) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            branchDao.update(em, branch);
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
            branchDao.delete(em, id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    public Branch findById(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Branch branch = null;

        try {
            transaction.begin();
            branch = branchDao.findById(em, id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }

        return branch;
    }

    public List<Branch> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<Branch> branches = null;

        try {
            transaction.begin();
            branches = branchDao.findAll(em);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }

        return branches;
    }
}