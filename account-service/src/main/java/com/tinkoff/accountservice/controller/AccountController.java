package com.tinkoff.accountservice.controller;

import com.tinkoff.accountservice.AccountService;
import com.tinkoff.accountservice.AccountServiceException;
import entity.Account;
import entity.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(
            value = {"/accounts"},
            method = {RequestMethod.GET}
    )
    public ResponseData<List<Account>> getAccounts(){
        return accountService.getAccounts();
    }

    @RequestMapping(
            value = {"/accounts/{id}"},
            method = {RequestMethod.GET}
    )
    public ResponseData<List<Account>> getAccounts(@PathVariable("id") UUID ownerId){
        return accountService.getAccounts(ownerId);
    }

    @RequestMapping(
            value = {"/account/{id}"},
            method = {RequestMethod.GET}
    )
    public ResponseData<Account> getAccount(@PathVariable("id") UUID id) throws AccountServiceException {
        return accountService.getAccount(id);
    }

    @RequestMapping(
            value = {"/account/{id}"},
            method = {RequestMethod.POST}
    )
    public ResponseData<UUID> updateAccount(@PathVariable("id") UUID id, @RequestBody Account account) throws AccountServiceException {
        return accountService.updateAccount(account);
    }

    @RequestMapping(
            value = {"/accounts"},
            method = {RequestMethod.POST}
    )
    public ResponseData<UUID> addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

    @RequestMapping(
            value = {"/account/{id}"},
            method = {RequestMethod.DELETE}
    )
    public ResponseData<UUID> deleteAccount(@PathVariable("id") UUID id) throws AccountServiceException {
        return accountService.deleteAccount(id);
    }

    @RequestMapping(
            value = {"/account/{id}/credit"},
            method = {RequestMethod.POST}
    )
    public ResponseData<UUID> creditAccount(@PathVariable("id") UUID id, @RequestParam long amount) throws AccountServiceException {
        return accountService.creditAccount(id, amount);
    }

    @RequestMapping(
            value = {"/account/{id}/debit"},
            method = {RequestMethod.POST}
    )
    public ResponseData<UUID> debitAccount(@PathVariable("id") UUID id, @RequestParam long amount) throws AccountServiceException {
        return accountService.debitAccount(id, amount);
    }
}