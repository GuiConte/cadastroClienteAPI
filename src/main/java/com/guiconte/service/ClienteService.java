package com.guiconte.service;

import com.guiconte.domain.entity.Cliente;
import com.guiconte.domain.entity.ClienteNullable;
import java.math.BigInteger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

  Cliente save(Cliente cliente);

  Cliente update(BigInteger codigo, Cliente cliente);

  Cliente patchUpdate(BigInteger codigo, ClienteNullable cliente);

  void delete(BigInteger codigo);

  Page<Cliente> findAll(Pageable pageable);

  Page<Cliente> findWithFilter(Cliente filter,Pageable pageable);

}
