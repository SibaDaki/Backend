package com.AttendBackEnd.testFactories;

import com.AttendBackEnd.domain.Bank;
import com.AttendBackEnd.domain.Customer;
import com.AttendBackEnd.factories.BankFactory;
import com.AttendBackEnd.factories.CustomerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 9/1/2016.
 */
public class BankFactoryTest {
    @Test
    public void testPerson()throws Exception
    {
        Bank bank = BankFactory.getCustomer("Nedbank");
        Assert.assertNotNull(bank.getBank_name());
    }
}
