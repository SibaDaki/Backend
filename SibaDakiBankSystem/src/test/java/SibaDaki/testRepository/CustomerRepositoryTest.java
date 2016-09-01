package SibaDaki.testRepository;

import SibaDaki.App;
import SibaDaki.Domain.Customer;
import SibaDaki.Repository.CustomerRepo;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

/**
 * Created by User on 2016/09/01.
 */

@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class CustomerRepositoryTest extends AbstractTestNGSpringContextTests{
    private Long id;
    @Autowired
    private CustomerRepo repo;

    @Test
    public void testCreate() throws  Exception{
        Customer createEntity=new Customer.Builder()
                .idNo("ID_NO")
                .name("Name")
                .surName("Surname")
                .build();
        Customer insertedEntity=repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull("Create",insertedEntity);
    }
    /*@Test
    public void testReadAll() throws Exception{
        Iterable<Customer> customerIterable=repo.findOne(3L);
        Assert.assertNotNull(customerIterable);
    }*/

    @Test
    public void testUpdate() throws Exception{
        Customer entity=repo.findOne(3L);
        Customer updateEntity=new Customer.Builder()
                .copy(entity)
                .name("New Name")
                .build();


        Customer updatedEntity=repo.save(updateEntity);
        Assert.assertEquals(updatedEntity.getName(),entity.getName());
    }

    @Test
    public void testDelete() throws Exception{
        Customer customer=repo.findOne(2L);
        if(customer!=null){
            Assert.assertNotNull("Before deleting.."+customer);
            repo.delete(2L);
            Customer deleted=repo.findOne(2L);
            Assert.assertNull("Deleted",deleted);
        }
    }
}
