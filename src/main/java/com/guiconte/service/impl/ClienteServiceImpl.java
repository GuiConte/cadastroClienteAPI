package com.guiconte.service.impl;

import static com.guiconte.service.converter.ClienteConverter.partialyConvertDTO;
import static com.guiconte.service.converter.ClienteConverter.toBusinessObject;
import static com.guiconte.service.converter.ClienteConverter.toDTO;

import com.guiconte.domain.entity.Cliente;
import com.guiconte.dto.ClienteDTO;
import com.guiconte.exception.ClienteNotFoundException;
import com.guiconte.repository.ClienteRepository;
import com.guiconte.service.ClienteService;
import java.math.BigInteger;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
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
              clienteRepository.save(toDTO(cliente));
              return cliente;
            })
            .orElseThrow(ClienteNotFoundException::new);
  }

  @Override
  public Cliente partialyUpdate(BigInteger codigo, Cliente cliente) {
    return clienteRepository
        .findById(codigo)
        .map(clienteDTO -> {
          clienteDTO = partialyConvertDTO(cliente, clienteDTO);
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
  public List<Cliente> findAll() {
    List<ClienteDTO> clientesDTO = clienteRepository.findAll();
    return toBusinessObject(clientesDTO);
  }

  @Override
  public List<Cliente> findWithFilter(Cliente filter) {
    ExampleMatcher matcher = ExampleMatcher
                            .matching()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);
    Example example = Example.of(toDTO(filter),matcher);
    List<ClienteDTO> clientesDTO =  clienteRepository.findAll(example);
    return toBusinessObject(clientesDTO);
  }
}
