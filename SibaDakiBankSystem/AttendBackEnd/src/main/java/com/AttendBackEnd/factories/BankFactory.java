package com.AttendBackEnd.factories;

import com.AttendBackEnd.domain.Bank;

/**
 * Created by User on 2016/08/31.
 */
public class BankFactory {
    public static Bank getCustomer(String bank_name)
    {
        Bank bank = new Bank.Builder()
                .bank_name(bank_name)
                .build();
        return bank;
    }
}
