package com.tutorial.module11.caching.service.impl;

import com.tutorial.module11.caching.entitiy.Employee;
import com.tutorial.module11.caching.entitiy.SalaryAccount;
import com.tutorial.module11.caching.exception.ResourceNotFoundException;
import com.tutorial.module11.caching.repository.SalaryAccountRepository;
import com.tutorial.module11.caching.service.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalaryAccountServiceImpl implements SalaryAccountService {

    private final SalaryAccountRepository salaryAccountRepository;

    @Override
    public void createAccount(Employee employee) {

        SalaryAccount salaryAccount = SalaryAccount.builder()
                .employee(employee)
                .balance(BigDecimal.ZERO)
                .build();

        salaryAccountRepository.save(salaryAccount);
    }

    @Override
    @Transactional
    public SalaryAccount incrementSalary(Long accountId) {
        SalaryAccount salaryAccount = salaryAccountRepository.findById(accountId)
                .orElseThrow();
        BigDecimal prevBalance = salaryAccount.getBalance();
        salaryAccount.setBalance(prevBalance.add(BigDecimal.valueOf(1)));
        return salaryAccountRepository.save(salaryAccount);
    }
}
