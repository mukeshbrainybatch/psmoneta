/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsc.moneta.util;

import com.rsc.moneta.Config;
import com.rsc.moneta.Currency;
import com.rsc.moneta.bean.Account;
import com.rsc.moneta.bean.PaymentOrder;
import com.rsc.moneta.bean.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author sulic
 */
public class Utils {

    static final String HEXES = "0123456789ABCDEF";

    public static String byteArrayToHexString(byte[] raw) {
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    public static String getMd5InHexString(String data) throws NoSuchAlgorithmException {
        // На вход подается строка, на выходе выдается MD5 в формате HEX в caps lock
        return Utils.byteArrayToHexString(Utils.md5(data));
    }

    public static byte[] md5(String data) throws NoSuchAlgorithmException {
        //вычисляет md5 хэш
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(data.getBytes());
        return digest.digest();
    }

    public static Long getLongValue(String tagName, Document doc) {
        NodeList list = doc.getElementsByTagName(tagName);
        try {
            Node node = list.item(0);
            if (node == null) {
                return null;
            }
            if (node.getTextContent() == null || "".equals(node.getTextContent())) {
                return null;
            }
            return Long.parseLong(node.getTextContent());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer getIntValue(String tagName, Document doc) {
        NodeList list = doc.getElementsByTagName(tagName);
        try {
            Node node = list.item(0);
            if (node == null) {
                return null;
            }
            if (node.getTextContent() == null || "".equals(node.getTextContent())) {
                return null;
            }
            return Integer.parseInt(node.getTextContent());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double getDoubleValue(String tagName, Document doc) {
        NodeList list = doc.getElementsByTagName(tagName);
        try {
            Node node = list.item(0);
            if (node == null) {
                return null;
            }
            if (node.getTextContent() == null || "".equals(node.getTextContent())) {
                return null;
            }
            return Double.parseDouble(node.getTextContent());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String createSignature(PaymentOrder key) throws NoSuchAlgorithmException {
        return createSignature(null, key);
    }

    public static String createSignature(String command, PaymentOrder key) throws NoSuchAlgorithmException {
        String str = "";
        if (command != null) {
            str += command;
        }
        str += key.getMarket().getId() + key.getTransactionId() + key.getId();
        str += key.getAmount();
        str += Utils.accountTypeToString(key.getCurrency()) + Utils.booleanToInt(key.getTest()) + key.getMarket().getPassword();
        return getMd5InHexString(str);
    }

    public static String accountTypeToString(int accountType) {
        switch (accountType) {
            case Currency.EURO:
                return "EURO";
            case Currency.USD:
                return "USD";
            case Currency.RUB:
                return "RUB";
            default:
                return "UNKNOWN";
        }
    }

    public static int currencyStringToAccountType(String MNT_CURRENCY_CODE) throws UnknownCurrencyException {
        if (MNT_CURRENCY_CODE.equalsIgnoreCase("RUB")) {
            return Currency.RUB;
        }
        if (MNT_CURRENCY_CODE.equalsIgnoreCase("EURO")) {
            return Currency.EURO;
        }
        if (MNT_CURRENCY_CODE.equalsIgnoreCase("USD")) {
            return Currency.USD;
        }
        throw new UnknownCurrencyException(MNT_CURRENCY_CODE);
    }

    private static int booleanToInt(Boolean test) {
        return (test) ? 1 : 0;
    }

    public static int getInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static long getLong(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /*
     * Возвращает true если string соотвествует шаблону regex,
    false - в прот. случ.
     */
    public static boolean regexMatch(String regex, String string) {
        Pattern pattern = null;
        pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getWebmoneyUrl(PaymentOrder paymentOrder) throws Exception {
        String url = "https://merchant.webmoney.ru/lmi/payment.asp?";
        url += "LMI_PAYMENT_AMOUNT=" + paymentOrder.getAmount();
        url += "&LMI_PAYMENT_DESC=" + paymentOrder.getDescription();
        url += "&LMI_PAYEE_PURSE=" + Config.getWebmoneyAccount(paymentOrder.getCurrency());
        url += "&LMI_PAYMENT_NO=" + paymentOrder.getId();
        if (paymentOrder.getTest()) {
            url += "&LMI_SIM_MODE=2";
        }
        return url;
    }

    public static Account getAccount(User user, String LMI_PAYEE_PURSE) {
        char ch = LMI_PAYEE_PURSE.charAt(0);
        if (ch == 'R'){
            return user.getAccount(Currency.RUB);
        }else if (ch == 'Z'){
            return user.getAccount(Currency.USD);
        }else if (ch == 'E'){
            return user.getAccount(Currency.EURO);
        }else{
            return null;
        }
    }
}
