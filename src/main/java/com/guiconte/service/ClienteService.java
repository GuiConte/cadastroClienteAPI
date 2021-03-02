package com.guiconte.service;

import com.guiconte.domain.entity.Cliente;
import com.guiconte.domain.entity.ClienteNullable;
import java.math.BigInteger;
import java.util.List;

public interface ClienteService {

  Cliente save(Cliente cliente);

  Cliente update(BigInteger codigo, Cliente cliente);

  Cliente patchUpdate(BigInteger codigo, ClienteNullable cliente);

  void delete(BigInteger codigo);

  List<Cliente> findAll();

  List<Cliente> findWithFilter(Cliente filter);

}
