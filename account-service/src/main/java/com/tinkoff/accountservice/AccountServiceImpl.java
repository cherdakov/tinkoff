package com.tinkoff.accountservice;


import entity.ResponseData;
import repository.account.AccountRepository;
import entity.Account;
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


    private void validateExist(UUID id) throws AccountServiceException {
        if (!accountRepository.existsById(id)) {
            throw new AccountServiceException("Account with id " + id + " doesn't exist");
        }
    }

    @Override
    public ResponseData<List<Account>> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return new ResponseData<>(accounts, ResponseData.ResultCode.OK);
    }

    @Override
    public ResponseData<List<Account>> getAccounts(UUID ownerId) {
        List<Account> accounts = accountRepository.findAllByOwnerId(ownerId);
        return new ResponseData<>(accounts, ResponseData.ResultCode.OK);
    }

    @Override
    public ResponseData<Account> getAccount(UUID id) throws AccountServiceException {
        validateExist(id);
        Account account = accountRepository.getOne(id);
        return new ResponseData<>(account, ResponseData.ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> updateAccount(Account account) throws AccountServiceException {
        if(account.getId() == null){
            throw new AccountServiceException("Account must have id for updating");
        }
        accountRepository.save(account);
        return new ResponseData<>(account.getId(), ResponseData.ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> addAccount(Account account) {
        accountRepository.save(account);
        return new ResponseData<>(account.getId(), ResponseData.ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> deleteAccount(UUID id) throws AccountServiceException {
        validateExist(id);
        accountRepository.deleteById(id);
        return new ResponseData<>(id, ResponseData.ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> creditAccount(UUID id, long amount) throws AccountServiceException {
        Account account = getAccount(id).getData();
        account.credit(amount);
        updateAccount(account);
        return new ResponseData<>(id, ResponseData.ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> debitAccount(UUID id, long amount) throws AccountServiceException {
        Account account = getAccount(id).getData();
        account.debit(amount);
        updateAccount(account);
        return new ResponseData<>(id, ResponseData.ResultCode.OK);
    }

}
