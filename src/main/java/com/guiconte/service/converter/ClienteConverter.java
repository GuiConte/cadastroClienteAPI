package com.guiconte.service.converter;

import com.guiconte.domain.entity.Cliente;
import com.guiconte.dto.ClienteDTO;
import java.util.ArrayList;
import java.util.List;

public class ClienteConverter {

  public static ClienteDTO toDTO(Cliente cliente){
    return ClienteDTO
        .builder()
        .cod_cliente(cliente.getCodigo())
        .nome(cliente.getNome())
        .cpf(cliente.getCpf())
        .idade(cliente.getIdade())
        .email(cliente.getEmail())
        .endereco(cliente.getEndereco())
        .cidade(cliente.getCidade())
        .telefone(cliente.getTelefone())
        .build();
  }

  public static Cliente toBusinessObject(ClienteDTO clienteDTO){
    return Cliente
        .builder()
        .codigo(clienteDTO.getCod_cliente())
        .nome(clienteDTO.getNome())
        .cpf(clienteDTO.getCpf())
        .idade(clienteDTO.getIdade())
        .email(clienteDTO.getEmail())
        .endereco(clienteDTO.getEndereco())
        .cidade(clienteDTO.getCidade())
        .telefone(clienteDTO.getTelefone())
        .build();
  }

  public static List<Cliente> toBusinessObject(List<ClienteDTO> clientesDTO){
    List<Cliente> clientes = new ArrayList<Cliente>();
    clientesDTO.forEach(
        clienteDTO -> {
          clientes.add(toBusinessObject(clienteDTO));
        }
    );
    return clientes;
  }

  public static ClienteDTO partialyConvertDTO(Cliente cliente, ClienteDTO clienteDTO){

    if(cliente.getNome() != null){
      clienteDTO.setNome(cliente.getNome());
    }
    if(cliente.getCpf() != null){
      clienteDTO.setCpf(cliente.getCpf());
    }
    if(cliente.getIdade() != null){
      clienteDTO.setIdade(cliente.getIdade());
    }
    if(cliente.getEmail() != null){
      clienteDTO.setEmail(cliente.getEmail());
    }
    if(cliente.getEndereco() != null){
      clienteDTO.setEndereco(cliente.getEndereco());
    }
    if(cliente.getCidade() != null){
      clienteDTO.setCidade(cliente.getCidade());
    }
    if(cliente.getTelefone() != null){
      clienteDTO.setTelefone(cliente.getTelefone());
    }

    return clienteDTO;
  }

}
