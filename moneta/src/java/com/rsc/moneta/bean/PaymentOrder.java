/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rsc.moneta.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

import com.rsc.moneta.Const;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author sulic
 * PaymentOrder это короче говоря один заказ.
 * Он генерится когда присылает ТАИС мне запрос
 * что пользователь заказ билет и хочет оплатить
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"transactionId", "marketId"})})
public class PaymentOrder implements Serializable {
    @OneToMany(mappedBy = "key")
    private List<PaymentParameter> paymentParameters;
    private static final long serialVersionUID = 1L;

    // TODO: изменить ххх на нормальное название проекта
    // Идентификатор кода заказа ПС ххх
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Это номер заказа в системе интернет магазина.
    @Column(name = "transactionId")
    private String transactionId;

    // Сумма заказа
    private Double amount;
    // Это тестовый платеж?
    private Boolean test;
    // Описание
    private String description;
    private String custom1;
    private String custom2;
    private String custom3;
    // Куда отправть пользователя после успешной оплаты.
    private String successUrl;
    // Куда отправить пользователь при ошибке об оплате.
    private String failUrl;


    private String paymentSystemUnitId;
    private String paymentSystemLimitIds;
    // Код валюты. Оплату можно производить в разных валютах
    // пока не используется вся валюту это рубль.
    private int currency;

    // Статус заказа ТЛСМ
    @Column(name = "orderstatus")
    private int status = Const.ORDER_STATUS_ACCEPTED;

    @Column(name = "_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    @Column(insertable=false, updatable=false)
    private long marketId;
    @ManyToOne
    @JoinColumn(name = "marketId")
    private Market market;

    public long getMarketId() {
        return marketId;
    }

    public void setMarketId(long marketId) {
        this.marketId = marketId;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public List<PaymentParameter> getPaymentParameters() {
        return paymentParameters;
    }

    public void setPaymentParameters(List<PaymentParameter> paymentParameters) {
        this.paymentParameters = paymentParameters;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustom1() {
        return custom1;
    }

    public void setCustom1(String custom1) {
        this.custom1 = custom1;
    }

    public String getCustom2() {
        return custom2;
    }

    public void setCustom2(String custom2) {
        this.custom2 = custom2;
    }

    public String getCustom3() {
        return custom3;
    }

    public void setCustom3(String custom3) {
        this.custom3 = custom3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFailUrl() {
        return failUrl;
    }

    public void setFailUrl(String failUrl) {
        this.failUrl = failUrl;
    }

    public String getPaymentSystemLimitIds() {
        return paymentSystemLimitIds;
    }

    public void setPaymentSystemLimitIds(String paymentSystemLimitIds) {
        this.paymentSystemLimitIds = paymentSystemLimitIds;
    }

    public String getPaymentSystemUnitId() {
        return paymentSystemUnitId;
    }

    public void setPaymentSystemUnitId(String paymentSystemUnitId) {
        this.paymentSystemUnitId = paymentSystemUnitId;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public Boolean getTest() {
        return test;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int orderStatus) {
        this.status = orderStatus;
    }
    
    
}