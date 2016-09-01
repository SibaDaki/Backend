package SibaDaki.testService;

import SibaDaki.App;
import SibaDaki.Domain.Customer;
import SibaDaki.Factory.CustomerFactory;
import SibaDaki.Repository.CustomerRepo;
import SibaDaki.Services.CustomerServiceImpl;
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
public class CustomerServiceTest extends AbstractTestNGSpringContextTests{
    @Autowired
    CustomerServiceImpl service;

    @Autowired
    CustomerRepo repo;
    @Test
    public void testCreate() throws Exception{
        Customer customer= CustomerFactory.getCustomer("ID_NO","Name","Surname");
        Customer savedCustomer=service.save(customer);

        Assert.assertNotNull(savedCustomer);
    }
    @Test
    public void testReadAll() throws Exception{
        Iterable<Customer> customerIterable=service.findAll();
        Assert.assertNotNull(customerIterable);
    }

    @Test
    public void testUpdate() throws Exception{
        Customer entity=service.findById(3L);
        Customer updateEntity=new Customer.Builder()
                .copy(entity)
                .name("New Name")
                .build();


        Customer updatedEntity=service.update(updateEntity);
        Assert.assertEquals(updatedEntity.getName(),"New Name");
    }

    @Test
    public void testDelete() throws Exception{
        Customer customer=service.findById(2L);
        if(customer!=null){
            Assert.assertNotNull("Before deleting.."+customer);
            service.delete(customer);
            Customer deleted=service.findById(2L);
            Assert.assertNull("Deleted",deleted);
        }
    }
}
