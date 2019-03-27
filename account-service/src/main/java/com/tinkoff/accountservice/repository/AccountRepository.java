package com.tinkoff.accountservice.repository;

import com.tinkoff.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findAllByOwnerId(UUID ownerId);
}