package com.guiconte.controller;

import com.guiconte.domain.entity.Cliente;
import com.guiconte.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api("API Clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation("Save new Cliente")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Successfully saved"),
      @ApiResponse(code = 400, message = "Validation error")
  })
  public Cliente save(@RequestBody @Valid @ApiParam("Entity Cliente") Cliente cliente){
    return clienteService.save(cliente);
  }

  @PutMapping("/{codigo}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation("Update Cliente")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully updated"),
      @ApiResponse(code = 400, message = "Validation error"),
      @ApiResponse(code = 404, message = "Not found error")
  })
  public Cliente update(@PathVariable @ApiParam("Cliente code") BigInteger codigo,
                        @RequestBody @Valid @ApiParam("Entity Cliente") Cliente cliente){
    return clienteService.update(codigo,cliente);
  }

  @PatchMapping("/{codigo}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation("Update partialy Cliente")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully deleted"),
      @ApiResponse(code = 400, message = "Validation error"),
      @ApiResponse(code = 404, message = "Not found error")
  })
  public Cliente partialyUpdate(@PathVariable @ApiParam("Cliente code") BigInteger codigo,
                                @RequestBody @Valid @ApiParam("Entity Cliente") Cliente cliente){
    return clienteService.partialyUpdate(codigo,cliente);
  }

  @DeleteMapping("/{codigo}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation("Delete Cliente")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Successfully deleted"),
      @ApiResponse(code = 404, message = "Not found error")
  })
  public void delete(@PathVariable @ApiParam("Cliente code") BigInteger codigo){
    clienteService.delete(codigo);
  }

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation("List all Clientes")
  @ApiResponse(code = 200, message = "Successfully listed")
  public List<Cliente> findAll(){
    return clienteService.findAll();
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation("List Clientes with filter")
  @ApiResponse(code = 200, message = "Successfully listed")
  public List<Cliente> findWithFilter(@ApiParam("Cliente params") Cliente filter){
    return clienteService.findWithFilter(filter);
  }

}
