package SibaDaki;

import SibaDaki.Domain.Customer;
import SibaDaki.Repository.CustomerRepo;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by User on 2016/08/29.
 */

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class TestCrudCustomer extends AbstractTestNGSpringContextTests {

    private Long id;
    private List<Customer> customerList;

    @Autowired
    CustomerRepo repository;

    //Test
  /*  public void create() throws Exception{
        Customer customer = CustomerFactory.getCustomer("243527","Siba","Daki");
        repository.save(customerList);
        Assert.assertNotNull(customer);
    } */


    @Test
   public void add() throws Exception {
        //Customer customer = CustomerFactory.getCustomer("21232","Siba","Daki");
        //customerList.add(customer);
        Customer customer = new Customer.Builder()
                .name("Siba")
                .surName("Daki")
                .build();
        Customer insertEntity = repository.save(customer);
        id = customer.getId();
        //customerId = bill.getId();
        Assert.assertNotNull(customer.getId());
    }

    @Test
    public void read() throws Exception {
        Customer customer = repository.findOne(id);
        Assert.assertEquals("Siba", customer.getName());
    }

 /*   public void testReadAll() throws Exception {
        Iterable<CommentOnPost> personSet = repository.findAll();
        Assert.assertNotNull(" READ ALL", personSet);
    }
*/
    @Test (dependsOnMethods = "read")
   public void update() throws Exception {
        Customer customer = repository.findOne(id);
        //Bill bill = repository.findOne(id).getBill();

        Customer newCust = new Customer.Builder()
                .idNo(customer.getIdNo())
                .name("Surname")
                .surName("Siba")
                .build();
                repository.save(newCust);

        Customer updatedCustomer = repository.findOne(id);
        Assert.assertEquals(123,updatedCustomer.getIdNo());

   }

    @Test
            //(dependsOnMethods = "update")
    public void delete() throws Exception {
        Customer customer = repository.findOne(id);
       repository.delete(customer);
        Customer newCust = repository.findOne(id);
        Assert.assertNull(newCust);
  }

}
