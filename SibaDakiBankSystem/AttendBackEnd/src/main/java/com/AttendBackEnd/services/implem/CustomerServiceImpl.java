package com.AttendBackEnd.services.implem;

import com.AttendBackEnd.domain.Customer;
import com.AttendBackEnd.repositories.CustomerRepo;
import com.AttendBackEnd.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 2016/08/29.
 */

@Service
public  class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo repo;
    @Override
    public Customer create(Customer entity) {
        return repo.save(entity);
    }

    @Override
    public Customer readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<Customer> readAll() {
        Iterable<Customer> customerIterable = repo.findAll();
        Set customerSet = new HashSet();
        for (Customer customer:customerIterable)
        {
            customerSet.add(customer);
        }
        return customerSet;
    }

    @Override
    public Customer update(Customer entity) {
        Customer customer = readById(entity.getId());
        if (customer == null)
        {
            return null;
        }
        return repo.save(entity);
    }

    @Override
    public void delete(Customer entity) {
        repo.delete(entity);
    }
}
