package SibaDaki.testFactory;

import SibaDaki.Domain.Customer;
import SibaDaki.Factory.CustomerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by User on 2016/09/01.
 */
public class CustomerFactoryTest {
    @Test
    public void testCreate(){
        Customer myCustomer= CustomerFactory.getCustomer("ID","Name","Surname");
        Assert.assertEquals("Name",myCustomer.getName());
    }
    /*public static Customer getCustomer(String idNo, String name, String surName)
    {
        Customer myCustomer = new Customer.Builder()
                .idNo(idNo)
                .name(name)
                .surName(surName)
                .build();

        return myCustomer;

    }*/
}
