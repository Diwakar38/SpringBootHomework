package com.tutorial.module11.caching.controller;

import com.tutorial.module11.caching.entitiy.SalaryAccount;
import com.tutorial.module11.caching.repository.SalaryAccountRepository;
import com.tutorial.module11.caching.service.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salary")
@RequiredArgsConstructor
public class SalaryAccountController {

    private final SalaryAccountService salaryAccountService;

    @PutMapping("/incrementBalance/{accountId}")
    public ResponseEntity<SalaryAccount> incrementBalance(@PathVariable Long accountId) {
        SalaryAccount salaryAccount = salaryAccountService.incrementSalary(accountId);
        return ResponseEntity.ok(salaryAccount);
    }
}
