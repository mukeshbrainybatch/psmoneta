/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsc.moneta.dao;

import com.rsc.moneta.bean.PaymentOrder;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author sulic
 */
public class PaymentOrderDao extends Dao {

    public PaymentOrderDao(EntityManager em) {
        super(em);
    }

    public PaymentOrder getPaymentOrder(String transactionId, long marketId) {
        try {
            Query q = em.createQuery("select p from PaymentOrder p where p.transactionId=:txid and p.market.id=:mid");
            q.setParameter("txid", transactionId);
            q.setParameter("mid", marketId);
            return (PaymentOrder) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public PaymentOrder getPaymentOrderById(long id) {
        try {
            Query q = em.createQuery("select p from PaymentOrder p where p.id=:id");
            q.setParameter("id", id);
            return (PaymentOrder) q.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}