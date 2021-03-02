package com.guiconte.service.impl;

import static com.guiconte.service.converter.ClienteConverter.dataToIdade;
import static com.guiconte.service.converter.ClienteConverter.toBusinessObject;
import static com.guiconte.service.converter.ClienteConverter.toDTO;

import com.guiconte.domain.entity.Cliente;
import com.guiconte.domain.entity.ClienteNullable;
import com.guiconte.dto.ClienteDTO;
import com.guiconte.exception.ClienteNotFoundException;
import com.guiconte.repository.ClienteRepository;
import com.guiconte.service.ClienteService;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public Cliente save(Cliente cliente) {
    ClienteDTO clienteDTO =  clienteRepository.save(toDTO(cliente));
    return toBusinessObject(clienteDTO);
  }

  @Override
  public Cliente update(BigInteger codigo, Cliente cliente) {
    return clienteRepository
            .findById(codigo)
            .map(clienteExists -> {
              cliente.setCodigo(clienteExists.getCod_cliente());
              cliente.setIdade(dataToIdade(clienteExists.getData_nascimento()));
              clienteRepository.save(toDTO(cliente));
              return cliente;
            })
            .orElseThrow(ClienteNotFoundException::new);
  }

  @Override
  public Cliente patchUpdate(BigInteger codigo, ClienteNullable cliente) {
    return clienteRepository
        .findById(codigo)
        .map(clienteDTO -> {
          clienteDTO = toDTO(cliente, clienteDTO);
          clienteRepository.save(clienteDTO);
          return toBusinessObject(clienteDTO);
        })
        .orElseThrow(ClienteNotFoundException::new);
  }

  @Override
  public void delete(BigInteger codigo) {
    clienteRepository
        .findById(codigo)
        .map(clienteExists -> {
          clienteRepository.delete(clienteExists);
          return clienteExists;
        })
        .orElseThrow(ClienteNotFoundException::new);
  }

  @Override
  public Page<Cliente> findAll(Pageable pageable) {
    Page<ClienteDTO> clientesDTO = clienteRepository.findAll(pageable);
    return toBusinessObject(clientesDTO);
  }

  @Override
  public Page<Cliente> findWithFilter(Cliente filter, Pageable pageable) {
    ExampleMatcher matcher = ExampleMatcher
                            .matching()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);
    Example example = Example.of(toDTO(filter),matcher);
    Page<ClienteDTO> clientesDTO =  clienteRepository.findAll(example,pageable);
    return toBusinessObject(clientesDTO);
  }
}
