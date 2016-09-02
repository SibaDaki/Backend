package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.Customer;
import com.AttendBackEnd.repositories.CustomerRepo;
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
public class CustomerFactoryTest extends AbstractTestNGSpringContextTests {
    private static final String TAG = "EventContact Test";
    @Autowired
    private CustomerRepo repo;
    private Long id;

    @Test
    public void testCreate() throws Exception {
        Customer createEntity = new Customer.Builder()
                .idNo("ID_NO")
                .name("Name")
                .surName("Surname")
                .build();
        Customer insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<Customer> customerIterable = repo.findAll();
        Assert.assertNotNull(" READ ALL", customerIterable);
    }

    @Test
    public void testUpdate() throws Exception {
        Customer entity = repo.findOne(3L);
        Customer updateEntity = new Customer.Builder()
                .copy(entity)
                .name("New Name")
                .build();
        Customer newEntity = repo.save(updateEntity);
        Assert.assertEquals(TAG + " UPDATE ENTITY", newEntity.getName(),entity.getName());
    }

    @Test
    public void testDelete () throws Exception
    {
        Customer customer = repo.findOne(2L);
        if ( customer != null)
        {
            Assert.assertNotNull("Before deleting", customer);
            repo.delete(2L);
            Customer deletedEvent = repo.findOne(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}
