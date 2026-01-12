package de.demonlords.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.demonlords.entity.Account;
import de.demonlords.repository.AccountRepository;

@Service
public class LoginDAO {

	
	@Autowired
	private AccountRepository accountRepository;

	public Optional<Account> login(String user, String passwort) {
		return accountRepository.findByNameAndPasswort(user, passwort);
	}
}