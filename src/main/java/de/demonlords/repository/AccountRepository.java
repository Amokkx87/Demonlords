package de.demonlords.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.demonlords.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByNameAndPasswort(String name, String passwort);
}
