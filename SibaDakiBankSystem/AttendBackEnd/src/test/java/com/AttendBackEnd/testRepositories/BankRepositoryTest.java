package com.AttendBackEnd.testRepositories;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.Bank;
import com.AttendBackEnd.repositories.BankRepo;
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
public class BankRepositoryTest extends AbstractTestNGSpringContextTests {
    private static final String TAG = "EventContact Test";
    @Autowired
    private BankRepo repo;
    private Long id;

    @Test
    public void testCreate() throws Exception {
        Bank createEntity = new Bank.Builder()
                .bank_name("Absa")
                .build();
        Bank insertedEntity = repo.save(createEntity);
        id=insertedEntity.getBank_id();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);
    }

    @Test
    public void testReadAll() throws Exception {
        Iterable<Bank> bankIterable = repo.findAll();
        Assert.assertNotNull(" READ ALL", bankIterable);
    }

    @Test
    public void testUpdate() throws Exception {
        Bank entity = repo.findOne(3L);
        Bank updateEntity = new Bank.Builder()
                .copy(entity)
                .bank_name("Absa")
                .build();
        Bank newEntity = repo.save(updateEntity);
        Assert.assertEquals(TAG + " UPDATE ENTITY", newEntity.getBank_name(),entity.getBank_name());
    }

    @Test
    public void testDelete () throws Exception
    {
        Bank bank = repo.findOne(2L);
        if ( bank != null)
        {
            Assert.assertNotNull("Before deleting", bank);
            repo.delete(2L);
            Bank deletedEvent = repo.findOne(2L);
            Assert.assertNull("Deleted",deletedEvent);
        }
    }
}
