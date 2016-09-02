package com.AttendBackEnd.factories;


import com.AttendBackEnd.domain.Customer;

/**
 * Created by User on 2016/08/27.
 */


public class CustomerFactory{


        public static Customer getCustomer(String idNo, String name, String surName)
        {
            Customer myCustomer = new Customer.Builder()
                    .idNo(idNo)
                    .name(name)
                    .surName(surName)
                    .build();

            return myCustomer;

        }

}
