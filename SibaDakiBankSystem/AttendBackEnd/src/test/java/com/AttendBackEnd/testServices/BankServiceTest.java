package com.AttendBackEnd.testServices;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.Bank;
import com.AttendBackEnd.domain.Customer;
import com.AttendBackEnd.factories.BankFactory;
import com.AttendBackEnd.services.implem.BankServiceImpl;
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
public class BankServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private BankServiceImpl service;

    @Test
    public void testCreate () throws Exception
    {
        Bank bank = BankFactory.getCustomer("Nedbank");
        Bank createEntity = service.create(bank);
        Assert.assertNotNull(createEntity);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<Bank> bankIterable = service.readAll();
        Assert.assertNotNull(" READ ALL", bankIterable);
    }

    @Test
    public void testUpdate() throws Exception {
        Bank entity = service.readById(1L);
        Bank updateEntity = new Bank.Builder()
                .copy(entity)
                .bank_name("Absa")
                .build();
        Bank newEntity = service.create(updateEntity);
        Assert.assertEquals(newEntity.getBank_name(),"Absa");
    }

    @Test
    public void testDelete () throws Exception
    {
        Bank bank = service.readById(2L);
        if ( bank != null)
        {
            Assert.assertNotNull("Before deleting", bank);
            service.delete(bank);
            Bank deleted = service.readById(2L);
            Assert.assertNull("Deleted",deleted);
        }
    }
}
