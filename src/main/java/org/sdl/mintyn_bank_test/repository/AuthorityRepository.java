package org.sdl.mintyn_bank_test.repository;

import org.sdl.mintyn_bank_test.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
    Optional<Authority> findAuthorityByName(String authorityName);
}
