package com.tinkoff.accountservice;

import com.tinkoff.accountservice.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {
        List<Account> getAccounts();

        List<Account> getAccounts(UUID ownerId);

        Account getAccount(UUID id);

        void updateAccount(Account account);

        void addAccount(Account account);

        void deleteAccount(UUID id);

        void creditAccount(UUID id, long amount);

        void debitAccount(UUID id, long amount);
}