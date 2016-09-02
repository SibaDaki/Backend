package com.AttendBackEnd.services.implem;


import com.AttendBackEnd.domain.Bank;
import com.AttendBackEnd.repositories.BankRepo;
import com.AttendBackEnd.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 2016/08/31.
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepo repo;
    @Override
    public Bank create(Bank entity) {
        return repo.save(entity);
    }

    @Override
    public Bank readById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Set<Bank> readAll() {
        Iterable<Bank> bankIterable = repo.findAll();
        Set bankSet = new HashSet();
        for (Bank bank:bankIterable)
        {
            bankSet.add(bank);
        }
        return bankSet;
    }

    @Override
    public Bank update(Bank entity) {
        Bank bank = readById(entity.getBank_id());
        if (bank == null)
        {
            return null;
        }
        return repo.save(entity);
    }

    @Override
    public void delete(Bank entity) {
        repo.delete(entity);
    }
}
