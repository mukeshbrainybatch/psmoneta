/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rsc.moneta.module;

/**
 *
 * @author sulic
 */
public class ResultCode {
    public static final int SUCCESS_WITH_AMOUNT = 0;
    public static final int SUCCESS_WITHOUT_AMOUNT = 3;
    public static final int ORDER_ALREADY_PAID = 1;
    public static final int ORDER_PROCESSING = 2;
    public static final int ORDER_NOT_ACTUAL = 4;
    public static final int ERROR_TRY_AGAIN = 10;
    public static final int INTERNAL_ERROR = 11;
    public static final int UNKNOWN_CODE = 12;
    public static final int ORDER_NOT_FOUND = 13;
    public static final int INVALID_SIGN = 14;
    public static final int MARKET_ID_NOT_DEFINE = 15;
    public static final int TRANSACTIONID_NOT_DEFINE = 16;
}