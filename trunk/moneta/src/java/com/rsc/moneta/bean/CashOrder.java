/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rsc.moneta.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

/**
 *
 * @author sulic
 */
@Entity
@SequenceGenerator(
    name="seq_cash_order",
    sequenceName="seq_cash_order"
)
public class CashOrder implements Serializable {
    public static int OPEN = 0;
    public static int PROCESSED = 1;
    public static int CANCEL = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq_cash_order")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Account account;

    @Column(nullable=false)
    private double amount;

    @Column(nullable=false)
    private int status;

    @Column(name="_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }  

}