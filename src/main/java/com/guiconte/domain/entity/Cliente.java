package com.guiconte.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.guiconte.validation.ValidDate;
import java.math.BigInteger;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

  private BigInteger codigo;

  @NotEmpty(message = "O campo nome e obrigatorio!")
  private String nome;

  @NotEmpty(message = "O campo cpf e obrigatorio!")
  @CPF(message = "O cpf e invalido!")
  private String cpf;

  private Integer idade;

  @NotNull(message = "O campo data nascimento e obrigatorio!")
  @ValidDate
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate data_nascimento;

  @NotEmpty(message = "O campo email e obrigatorio!")
  @Email(message = "O email e invalido!")
  private String email;

  @NotEmpty(message = "O campo endereco e obrigatorio!")
  private String endereco;

  @NotEmpty(message = "O campo cidade e obrigatorio!")
  private String cidade;

  @NotEmpty(message = "O campo telefone e obrigatorio!")
  private String telefone;

}
