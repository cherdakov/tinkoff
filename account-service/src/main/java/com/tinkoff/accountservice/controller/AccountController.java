package com.tinkoff.accountservice.controller;


import com.tinkoff.accountservice.AccountService;
import com.tinkoff.accountservice.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @RequestMapping(
            value = {"/accounts/{id}"},
            method = {RequestMethod.GET}
    )
    public List<Account> getAccounts(UUID ownerId){
        return accountService.getAccounts(ownerId);
    }

    @RequestMapping(
            value = {"/account/{id}"},
            method = {RequestMethod.GET}
    )
    public Account getAccount(@PathVariable("id") UUID id){
        return accountService.getAccount(id);
    }

    @RequestMapping(
            value = {"/account/{id}"},
            method = {RequestMethod.POST}
    )
    public void updateAccount(@PathVariable("id") UUID id, @RequestBody Account account){
        accountService.updateAccount(account);
    }

    @RequestMapping(
            value = {"/accounts"},
            method = {RequestMethod.POST}
    )
    public void addAccount(@RequestBody Account account){
        accountService.addAccount(account);
    }

    @RequestMapping(
            value = {"/account/{id}"},
            method = {RequestMethod.DELETE}
    )
    public void deleteAccount(@PathParam("id") UUID id){
        accountService.deleteAccount(id);
    }

    @RequestMapping(
            value = {"/account/credit/{id}"},
            method = {RequestMethod.POST}
    )
    public void creditAccount(@PathVariable("id") UUID id, @RequestParam long amount){
        accountService.creditAccount(id, amount);
    }

    @RequestMapping(
            value = {"/account/debit/{id}"},
            method = {RequestMethod.POST}
    )
    public void debitAccount(@PathVariable("id") UUID id, @RequestParam long amount){
        accountService.debitAccount(id, amount);
    }
}