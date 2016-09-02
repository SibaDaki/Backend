package com.AttendBackEnd.client;

import com.AttendBackEnd.App;
import com.AttendBackEnd.domain.Bank;
import com.AttendBackEnd.factories.BankFactory;
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
public class BankAPITest extends AbstractJUnit4SpringContextTests {
    @Test
    public void testCreate() throws Exception
    {
        String URL = "http://localhost:8080/bank";
        RestTemplate restTemplate = new RestTemplate();
        Bank bank = BankFactory.getCustomer("Nedbank");
        restTemplate.postForObject(URL,bank, Bank.class);
    }
    @Test
    public void testFindById() throws Exception
    {
        String URL = "http://localhost:8080/bank/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Bank bank = restTemplate.getForObject(URL,Bank.class,"3");
        Assert.assertNotNull(bank);
        Assert.assertEquals("Nedbank",bank.getBank_name());
    }

    @Test
    public void testUpdate() throws Exception{
        String URI =  "http://localhost:8080/bank/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Bank bank = restTemplate.getForObject(URI, Bank.class, "3");
        if(bank != null) {
            String UPDATE_URI = "http://localhost:8080/bank";
            Bank update = new Bank.Builder()
                    .copy(bank)
                    .bank_name("Absa")
                    .build();
            restTemplate.put(UPDATE_URI,update);
            Bank updated = restTemplate.getForObject(URI, Bank.class, "3");

            Assert.assertEquals(updated.getBank_name(),"Absa");
        }
    }
    @Test
    public void testFindAll() throws Exception{
        String URI =  "http://localhost:8080/bank";
        RestTemplate restTemplate = new RestTemplate();
        Set bankSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(bankSet.size()>0);
    }
    @Test
    public void testDelete() throws Exception{
        String URI =  "http://localhost:8080/bank/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"3");
        Bank bank= restTemplate.getForObject(URI, Bank.class, "3");
        Assert.assertNull(bank);
    }
}
