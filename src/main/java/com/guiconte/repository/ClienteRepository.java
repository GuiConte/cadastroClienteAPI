package com.guiconte.repository;

import com.guiconte.dto.ClienteDTO;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteDTO, BigInteger> {

}
