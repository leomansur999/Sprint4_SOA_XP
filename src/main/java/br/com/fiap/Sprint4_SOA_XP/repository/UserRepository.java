package br.com.fiap.Sprint4_SOA_XP.repository;

import br.com.fiap.Sprint4_SOA_XP.modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
