package com.AttendBackEnd.repositories;


import com.AttendBackEnd.domain.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 2016/08/31.
 */

@Repository
public interface BankRepo extends CrudRepository<Bank, Long> {
}
