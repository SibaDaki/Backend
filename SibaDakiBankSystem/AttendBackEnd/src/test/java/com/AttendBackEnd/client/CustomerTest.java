package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.Customer;
import com.AttendBackEnd.factories.CustomerFactory;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;
import java.util.Set;
/**
 * Created by Administrator on 9/1/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class CustomerTest extends AbstractJUnit4SpringContextTests {
    @Test
    public void testCreate() throws Exception
    {
        String URL = "http://localhost:8080//customer";
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = CustomerFactory.getCustomer("ID_No","Name","Surname");
        restTemplate.postForObject(URL,customer, Customer.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/customer/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = restTemplate.getForObject(URL,Customer.class,"1");
        Assert.assertNotNull(customer);
        Assert.assertEquals("New name",customer.getName());
    }

    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/customer/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = restTemplate.getForObject(URI, Customer.class, "1");
        if(customer != null) {
            String UPDATE_URI = "http://localhost:8080/customer";
            Customer updateEvent = new Customer.Builder()
                    .copy(customer)
                    .name("New Name")
                    .build();
            restTemplate.put(UPDATE_URI,updateEvent);
            Customer updatedEvent = restTemplate.getForObject(URI, Customer.class, "1");

            Assert.assertEquals(updatedEvent.getName(),"New Name");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/customer";
        RestTemplate restTemplate = new RestTemplate();
        Set custSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(custSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/customer/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"8");
        Customer customer= restTemplate.getForObject(URI, Customer.class, "1");
        Assert.assertNotNull(customer);
    }
}
