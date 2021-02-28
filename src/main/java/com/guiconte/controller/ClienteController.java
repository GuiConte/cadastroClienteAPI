package com.guiconte.controller;

import com.guiconte.domain.entity.Cliente;
import com.guiconte.service.ClienteService;
import java.math.BigInteger;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cliente save(@RequestBody @Valid Cliente cliente){
    return clienteService.save(cliente);
  }

  @PutMapping("/{codigo}")
  @ResponseStatus(HttpStatus.OK)
  public Cliente update(@PathVariable BigInteger codigo,
                        @RequestBody @Valid Cliente cliente){
    return clienteService.update(codigo,cliente);
  }

  @PatchMapping("/{codigo}")
  @ResponseStatus(HttpStatus.OK)
  public Cliente partialyUpdate(@PathVariable BigInteger codigo,
                             @RequestBody @Valid Cliente cliente){
    return clienteService.partialyUpdate(codigo,cliente);
  }

  @DeleteMapping("/{codigo}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable BigInteger codigo){
    clienteService.delete(codigo);
  }

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  public List<Cliente> findAll(){
    return clienteService.findAll();
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Cliente> findWithFilter(Cliente filter){
    return clienteService.findWithFilter(filter);
  }

}
