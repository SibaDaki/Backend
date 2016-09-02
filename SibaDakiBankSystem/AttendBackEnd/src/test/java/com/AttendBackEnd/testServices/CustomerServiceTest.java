package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.Customer;
import com.AttendBackEnd.factories.CustomerFactory;
import com.AttendBackEnd.services.implem.CustomerServiceImpl;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 9/1/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class CustomerServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private CustomerServiceImpl service;

    @Test
    public void testCreate () throws Exception
    {
        Customer customer = CustomerFactory.getCustomer("ID_NO","Name","Surname");
        Customer createEntity = service.create(customer);
        Assert.assertNotNull(createEntity);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<Customer> customerIterable = service.readAll();
        Assert.assertNotNull(" READ ALL", customerIterable);
    }

    @Test
    public void testUpdate() throws Exception {
        Customer entity = service.readById(1L);
        Customer updateEntity = new Customer.Builder()
                .copy(entity)
                .name("New name")
                .build();
        service.create(updateEntity);
        Customer newEntity = service.create(updateEntity);
        Assert.assertEquals(newEntity.getName(),"New name");
    }

    @Test
    public void testDelete () throws Exception
    {
        Customer customer = service.readById(2L);
        if ( customer != null)
        {
            Assert.assertNotNull("Before deleting", customer);
            service.delete(customer);
            Customer deleted = service.readById(2L);
            Assert.assertNull("Deleted",deleted);
        }
    }
}
