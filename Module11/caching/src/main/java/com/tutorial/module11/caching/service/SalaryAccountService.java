package com.tutorial.module11.caching.service;

import com.tutorial.module11.caching.entitiy.Employee;
import com.tutorial.module11.caching.entitiy.SalaryAccount;

public interface SalaryAccountService {
    void createAccount(Employee employee);

    SalaryAccount incrementSalary(Long accountId);
}
