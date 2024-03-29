package com.rsc.moneta.bean;

import com.rsc.moneta.bean.User;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: sulic
 * Date: 14.02.2008
 * Time: 22:08:38
 * Данный класс описывает абонентский платеж.
 * Абонентский платеж это платеж самого обычного пользователя, который оплачивает услугу транзитом через нашу систему.
 * Транзитом это означает, что платеж производится из одной платежной системы в другую
 *
 */
@Entity
public class CyberplatPayment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    private String account;
    private String comment;   
    private String authCode;    
    private String transId;
    private int status;
    private int errorMessage;
    @Column(insertable=false, updatable=false, nullable=false)
    private long psPaymentId;
    @OneToOne
    @JoinColumn(name="psPaymentId")
    private PSPayment psPayment;
    @Column(name = "_session")
    private String session;
    private double amount;
    @Column(name = "_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    

    public final static int SUCCESS = 0;
    public static final int NEW = 1;
    public static final int CHECKED = 2;
    public static final int PAYMENT = 3;
    public static final int CHECKED_ERROR = 4;
    public static final int PAYMENT_ERROR = 5;
    public static final int ERROR = 6;
    public static final int UNKNOWN = 7;
    public static final int PROCESSING = 8;

    public CyberplatPayment() {
        this.date = new Date();
    }

    public CyberplatPayment(PSPayment pay) {
        this.psPayment = pay;
        this.amount = pay.getAmount();
        this.account = pay.getBill();
        this.number = pay.getNumber();
        this.date = new Date();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }   

    public int getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(int errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    } 

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }   

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

  
}
