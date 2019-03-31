package com.tinkoff.accountservice;

import entity.Account;
import entity.ResponseData;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface AccountService {
        ResponseData<List<Account>> getAccounts();

        ResponseData<List<Account>> getAccounts(UUID ownerId);

        ResponseData<Account> getAccount(UUID id) throws AccountServiceException;

        ResponseData<UUID> updateAccount(Account account) throws AccountServiceException;

        ResponseData<UUID> addAccount(Account account) throws IOException;

        ResponseData<UUID> deleteAccount(UUID id) throws AccountServiceException;

        ResponseData<UUID> creditAccount(UUID id, long amount) throws AccountServiceException;

        ResponseData<UUID> debitAccount(UUID id, long amount) throws AccountServiceException;
}