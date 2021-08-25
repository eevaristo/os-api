package com.evaristo.os.repositories;

import com.evaristo.os.domain.Cliente;
import com.evaristo.os.domain.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsRepository extends JpaRepository<OS,Integer> {
}