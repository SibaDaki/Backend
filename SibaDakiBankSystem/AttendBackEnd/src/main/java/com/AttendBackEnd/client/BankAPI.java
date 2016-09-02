package com.AttendBackEnd.client;

import com.AttendBackEnd.domain.Bank;
import com.AttendBackEnd.services.implem.BankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by User on 2016/08/31.
 */
@RestController
@RequestMapping(value = "/bank")
public class BankAPI {
    @Autowired
    private BankServiceImpl service;

    /************ Create *************/
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private Bank create(@RequestBody Bank resource)
    {
        return service.create(resource);
    }


    /***************Find by id******************/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Bank findbyId(@PathVariable Long id)
    {
        return service.readById(id);
    }

    /**************Find all**********************/
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<Bank> findAll()
    {
        return service.readAll();
    }

    /**************Delete id******************************/
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id)
    {
        Bank deleted = service.readById(id);
        if (deleted != null)
        {
            service.delete(deleted);
        }
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Bank resource){
        service.update(resource);
    }
}
