package de.demonlords.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.demonlords.entity.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

    Optional<UserLogin> findByUsernameAndPassword(String username, String password);
}
