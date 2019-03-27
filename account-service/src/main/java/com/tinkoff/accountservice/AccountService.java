package com.tinkoff.accountservice;

import com.tinkoff.accountservice.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {
        List<Account> getAccounts();

        List<Account> getAccounts(UUID ownerId);

        Account getAccount(UUID id) throws AccountServiceException;

        void updateAccount(Account account) throws AccountServiceException;

        void addAccount(Account account);

        void deleteAccount(UUID id) throws AccountServiceException;

        void creditAccount(UUID id, long amount) throws AccountServiceException;

        void debitAccount(UUID id, long amount) throws AccountServiceException;
}