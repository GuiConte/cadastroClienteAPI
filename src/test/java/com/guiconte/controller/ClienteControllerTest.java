package com.guiconte.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guiconte.dto.ClienteDTO;
import com.guiconte.repository.ClienteRepository;
import com.guiconte.service.ClienteService;
import com.guiconte.service.impl.ClienteServiceImpl;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(
    value = {
        ClienteController.class,
        ClienteServiceImpl.class
    }
)
@AutoConfigureMockMvc
public class ClienteControllerTest {

  @Autowired
  private ClienteService clienteService;

  @MockBean
  private ClienteRepository clientesRepository;

  @Autowired
  private MockMvc mvc;

  @Test
  public void save_201() throws Exception {

    ClienteDTO clienteDTO = ClienteDTO
        .builder()
        .cod_cliente(BigInteger.valueOf(1))
        .nome("Cliente Teste")
        .cpf("46444042097")
        .idade(23)
        .email("email@email.com")
        .endereco("Rua brasil, 2021")
        .cidade("Sao Paulo")
        .telefone("(19)981234567")
        .build();

    when(clientesRepository.save(any())).thenReturn(clienteDTO);

    mvc.perform(MockMvcRequestBuilders
        .post("/clientes")
        .content(toJson(clienteDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.codigo").exists())
        .andExpect(jsonPath("$.nome").exists())
        .andExpect(jsonPath("$.cpf").exists())
        .andExpect(jsonPath("$.idade").exists())
        .andExpect(jsonPath("$.email").exists())
        .andExpect(jsonPath("$.endereco").exists())
        .andExpect(jsonPath("$.cidade").exists())
        .andExpect(jsonPath("$.telefone").exists());
  }

  @Test
  public void save_invalidCpf_400() throws Exception {

    ClienteDTO clienteDTO = ClienteDTO
        .builder()
        .cod_cliente(BigInteger.valueOf(1))
        .nome("Cliente Teste")
        .cpf("00000000000")
        .idade(23)
        .email("email@email.com")
        .endereco("Rua brasil, 2021")
        .cidade("Sao Paulo")
        .telefone("(19)981234567")
        .build();

    when(clientesRepository.save(any())).thenReturn(clienteDTO);

    mvc.perform(MockMvcRequestBuilders
        .post("/clientes")
        .content(toJson(clienteDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors[*]").exists())
        .andExpect(jsonPath("$.errors[*]", hasItem("O cpf e invalido!")));
  }

  @Test
  public void save_invalidEmail_400() throws Exception {

    ClienteDTO clienteDTO = ClienteDTO
        .builder()
        .cod_cliente(BigInteger.valueOf(1))
        .nome("Cliente Teste")
        .cpf("46444042097")
        .idade(23)
        .email("emailteste.com")
        .endereco("Rua brasil, 2021")
        .cidade("Sao Paulo")
        .telefone("(19)981234567")
        .build();

    when(clientesRepository.save(any())).thenReturn(clienteDTO);

    mvc.perform(MockMvcRequestBuilders
        .post("/clientes")
        .content(toJson(clienteDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors[*]").exists())
        .andExpect(jsonPath("$.errors[*]", hasItem("O email e invalido!")));
  }

  @Test
  public void save_invalidIdade_400() throws Exception {

    ClienteDTO clienteDTO = ClienteDTO
        .builder()
        .cod_cliente(BigInteger.valueOf(1))
        .nome("Cliente Teste")
        .cpf("46444042097")
        .idade(-1)
        .email("email@email.com")
        .endereco("Rua brasil, 2021")
        .cidade("Sao Paulo")
        .telefone("(19)981234567")
        .build();

    when(clientesRepository.save(any())).thenReturn(clienteDTO);

    mvc.perform(MockMvcRequestBuilders
        .post("/clientes")
        .content(toJson(clienteDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors[*]").exists())
        .andExpect(jsonPath("$.errors[*]", hasItem("A idade e invalida!")));
  }

  @Test
  public void save_nullFields_400() throws Exception {

    ClienteDTO clienteDTO = ClienteDTO
        .builder()
        .cpf("46444042097")
        .idade(20)
        .email("email@email.com")
        .build();

    when(clientesRepository.save(any())).thenReturn(clienteDTO);

    mvc.perform(MockMvcRequestBuilders
        .post("/clientes")
        .content(toJson(clienteDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors[*]").exists())
        .andExpect(jsonPath("$.errors[*]", hasItem("O campo nome e obrigatorio!")))
        .andExpect(jsonPath("$.errors[*]", hasItem("O campo endereco e obrigatorio!")))
        .andExpect(jsonPath("$.errors[*]", hasItem("O campo cidade e obrigatorio!")))
        .andExpect(jsonPath("$.errors[*]", hasItem("O campo telefone e obrigatorio!")));
  }

  @Test
  public void update_200() throws Exception {

    ClienteDTO clienteDTO = ClienteDTO
        .builder()
        .cod_cliente(BigInteger.valueOf(1))
        .nome("Cliente Teste")
        .cpf("46444042097")
        .idade(23)
        .email("email@email.com")
        .endereco("Rua brasil, 2021")
        .cidade("Sao Paulo")
        .telefone("(19)981234567")
        .build();

    BigInteger codigo = clienteDTO.getCod_cliente();

    when(clientesRepository.findById(codigo)).thenReturn(Optional.of(clienteDTO));
    when(clientesRepository.save(any())).thenReturn(clienteDTO);

    mvc.perform(MockMvcRequestBuilders
        .put("/clientes/"+codigo)
        .content(toJson(clienteDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.codigo").exists())
        .andExpect(jsonPath("$.nome").exists())
        .andExpect(jsonPath("$.cpf").exists())
        .andExpect(jsonPath("$.idade").exists())
        .andExpect(jsonPath("$.email").exists())
        .andExpect(jsonPath("$.endereco").exists())
        .andExpect(jsonPath("$.cidade").exists())
        .andExpect(jsonPath("$.telefone").exists());
  }

  @Test
  public void update_clienteNotFound_404() throws Exception {

    ClienteDTO clienteDTO = ClienteDTO
        .builder()
        .cod_cliente(BigInteger.valueOf(1))
        .nome("Cliente Teste")
        .cpf("46444042097")
        .idade(23)
        .email("email@email.com")
        .endereco("Rua brasil, 2021")
        .cidade("Sao Paulo")
        .telefone("(19)981234567")
        .build();

    BigInteger codigo = clienteDTO.getCod_cliente();

    when(clientesRepository.findById(codigo)).thenReturn(Optional.of(clienteDTO));
    when(clientesRepository.save(any())).thenReturn(clienteDTO);

    mvc.perform(MockMvcRequestBuilders
          .put("/clientes/2")
          .content(toJson(clienteDTO))
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNotFound())
          .andExpect(jsonPath("$.errors[*]").exists())
          .andExpect(jsonPath("$.errors[*]", hasItem("Cliente nao existente!")));

  }

  @Test
  public void delete_204() throws Exception {
    ClienteDTO clienteDTO = ClienteDTO
        .builder()
        .cod_cliente(BigInteger.valueOf(1))
        .nome("Cliente Teste")
        .cpf("46444042097")
        .idade(23)
        .email("email@email.com")
        .endereco("Rua brasil, 2021")
        .cidade("Sao Paulo")
        .telefone("(19)981234567")
        .build();

    BigInteger codigo = clienteDTO.getCod_cliente();

    when(clientesRepository.findById(codigo)).thenReturn(Optional.of(clienteDTO));

    mvc.perform(MockMvcRequestBuilders
        .delete("/clientes/"+codigo))
        .andDo(print())
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$").doesNotHaveJsonPath());
  }

  @Test
  public void delete_clienteNotFound_404() throws Exception {
    ClienteDTO clienteDTO = ClienteDTO
        .builder()
        .cod_cliente(BigInteger.valueOf(1))
        .nome("Cliente Teste")
        .cpf("46444042097")
        .idade(23)
        .email("email@email.com")
        .endereco("Rua brasil, 2021")
        .cidade("Sao Paulo")
        .telefone("(19)981234567")
        .build();

    BigInteger codigo = clienteDTO.getCod_cliente();

    when(clientesRepository.findById(codigo)).thenReturn(Optional.of(clienteDTO));

    mvc.perform(MockMvcRequestBuilders
        .delete("/clientes/2"))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[*]").exists())
        .andExpect(jsonPath("$.errors[*]", hasItem("Cliente nao existente!")));
  }

  @Test
  public void findAll_200() throws Exception {
    List<ClienteDTO> clientesDTO = Arrays.asList(
        ClienteDTO.builder().cod_cliente(BigInteger.valueOf(1)).nome("Cliente teste 1").cpf("21101519002")
            .idade(19).email("email1@email.com").endereco("Rua um, 1").cidade("Sao Paulo")
            .telefone("(11)98111-1111").build(),
        ClienteDTO.builder().cod_cliente(BigInteger.valueOf(2)).nome("Cliente teste 2").cpf("36208393019")
            .idade(22).email("email2@email.com").endereco("Rua dois, 2").cidade("Campinas")
            .telefone("(22)98222-2222").build()
    );

    when(clientesRepository.findAll()).thenReturn(clientesDTO);

    mvc.perform(MockMvcRequestBuilders
        .get("/clientes/all")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[*].codigo").exists())
        .andExpect(jsonPath("$[*].nome").exists())
        .andExpect(jsonPath("$[*].cpf").exists())
        .andExpect(jsonPath("$[*].idade").exists())
        .andExpect(jsonPath("$[*].email").exists())
        .andExpect(jsonPath("$[*].endereco").exists())
        .andExpect(jsonPath("$[*].cidade").exists())
        .andExpect(jsonPath("$[*].telefone").exists());
  }

  @Test
  public void findWithFilter_200() throws Exception {
    ClienteDTO clienteDTO = ClienteDTO
        .builder()
        .cod_cliente(BigInteger.valueOf(1))
        .nome("Cliente Teste")
        .cpf("46444042097")
        .idade(23)
        .email("email@email.com")
        .endereco("Rua brasil, 2021")
        .cidade("Sao Paulo")
        .telefone("(19)981234567")
        .build();

    when(clientesRepository.findAll(any(Example.class))).thenReturn(Arrays.asList(clienteDTO));

    mvc.perform(MockMvcRequestBuilders
        .get("/clientes")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[*].codigo").exists())
        .andExpect(jsonPath("$[*].nome").exists())
        .andExpect(jsonPath("$[*].cpf").exists())
        .andExpect(jsonPath("$[*].idade").exists())
        .andExpect(jsonPath("$[*].email").exists())
        .andExpect(jsonPath("$[*].endereco").exists())
        .andExpect(jsonPath("$[*].cidade").exists())
        .andExpect(jsonPath("$[*].telefone").exists());
  }

  private String toJson(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
