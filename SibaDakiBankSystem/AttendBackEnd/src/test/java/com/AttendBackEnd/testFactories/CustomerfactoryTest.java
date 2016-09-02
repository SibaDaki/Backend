package com.AttendBackEnd.testFactories;

import com.AttendBackEnd.domain.Customer;
import com.AttendBackEnd.factories.CustomerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 9/1/2016.
 */
public class CustomerfactoryTest {
    @Test
    public void testPerson()throws Exception
    {
        Customer customer = CustomerFactory.getCustomer("ID_NO","Name","Tomson");
        Assert.assertNotNull(customer.getName());
    }
}
