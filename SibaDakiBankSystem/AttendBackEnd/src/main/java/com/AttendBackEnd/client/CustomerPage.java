package com.AttendBackEnd.client;

import com.AttendBackEnd.domain.Customer;
import com.AttendBackEnd.services.implem.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.Set;


/**
 * Created by User on 2016/08/29.
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerPage {

    @Autowired
    private CustomerServiceImpl service;

    /************ Create *************/
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private Customer create(@RequestBody Customer resource)
    {
        return service.create(resource);
    }


    /***************Find by id******************/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer findbyId(@PathVariable Long id)
    {
        return service.readById(id);
    }

    /**************Find all**********************/
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<Customer> findAll()
    {
        return service.readAll();
    }

    /**************Delete id******************************/
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id)
    {
        Customer eventContactTodelete = service.readById(id);
        if (eventContactTodelete != null)
        {
            service.delete(eventContactTodelete);
        }
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Customer resource){
        service.update(resource);
    }
}

