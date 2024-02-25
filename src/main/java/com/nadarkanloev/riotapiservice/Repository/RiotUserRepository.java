package com.nadarkanloev.riotapiservice.Repository;

import com.nadarkanloev.riotapiservice.Model.RiotUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiotUserRepository extends JpaRepository<RiotUser, String> {
    boolean existsByName(String name);
}