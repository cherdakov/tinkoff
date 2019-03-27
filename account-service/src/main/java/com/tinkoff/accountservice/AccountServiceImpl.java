package com.tinkoff.accountservice;

import com.tinkoff.accountservice.entity.Account;
import com.tinkoff.accountservice.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @PostConstruct
    void init(){
        logger.info("AccountService created");
    }

    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    public List<Account> getAccounts(UUID ownerId) {
        List<Account> accounts = accountRepository.findAllByOwnerId(ownerId);
        return accounts;
    }

    public Account getAccount(UUID id) {
        Account account = accountRepository.getOne(id);
        return account;
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(UUID id) {
        accountRepository.deleteById(id);
    }
}
