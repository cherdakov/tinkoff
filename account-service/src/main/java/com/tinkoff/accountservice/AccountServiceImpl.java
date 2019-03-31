package com.tinkoff.accountservice;


import client.CustomerServiceHttpClient;
import entity.Customer;
import entity.ResponseData;
import entity.ResultCode;
import repository.account.AccountRepository;
import entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
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

    CustomerServiceHttpClient customerServiceHttpClient = new CustomerServiceHttpClient();

    private void validateAccountExist(UUID id) throws AccountServiceException {
        if (!accountRepository.existsById(id)) {
            throw new AccountServiceException("Account with id " + id + " doesn't exist", ResultCode.NOT_EXISTS);
        }
    }

    @Override
    public ResponseData<List<Account>> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return new ResponseData<>(accounts, ResultCode.OK);
    }

    @Override
    public ResponseData<List<Account>> getAccounts(UUID ownerId) {
        List<Account> accounts = accountRepository.findAllByOwnerId(ownerId);
        return new ResponseData<>(accounts, ResultCode.OK);
    }

    @Override
    public ResponseData<Account> getAccount(UUID id) throws AccountServiceException {
        validateAccountExist(id);
        Account account = accountRepository.getOne(id);
        return new ResponseData<>(account, ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> updateAccount(Account account) throws AccountServiceException {
        if(account.getId() == null){
            throw new AccountServiceException("Account must have id for updating", ResultCode.ERROR);
        }
        accountRepository.save(account);
        return new ResponseData<>(account.getId(), ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> addAccount(Account account) throws IOException, AccountServiceException {
        validateOwnerExists(account.getOwnerId());
        accountRepository.save(account);
        return new ResponseData<>(account.getId(), ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> deleteAccount(UUID id) throws AccountServiceException {
        validateAccountExist(id);
        accountRepository.deleteById(id);
        return new ResponseData<>(id, ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> creditAccount(UUID id, long amount) throws AccountServiceException {
        Account account = getAccount(id).getData();
        account.credit(amount);
        updateAccount(account);
        return new ResponseData<>(id, ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> debitAccount(UUID id, long amount) throws AccountServiceException {
        Account account = getAccount(id).getData();
        account.debit(amount);
        updateAccount(account);
        return new ResponseData<>(id, ResultCode.OK);
    }

    private void validateOwnerExists(UUID ownerId) throws IOException, AccountServiceException {
        ResponseData<Customer> response = customerServiceHttpClient.getCustomer(ownerId);
        if (response.getResultCode() == ResultCode.NOT_EXISTS) {
            throw new AccountServiceException("Owner with id"+ownerId+"doesn't exist", ResultCode.NOT_EXISTS);
        } else if (response.getResultCode() != ResultCode.OK) {
            throw new AccountServiceException(response.getErrorMessage(), response.getResultCode());
        }
    }

}
