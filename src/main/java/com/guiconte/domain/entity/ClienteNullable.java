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
public class ClienteNullable {
  private BigInteger codigo;

  private String nome;

  @CPF(message = "O cpf e invalido!")
  private String cpf;

  private Integer idade;

  @ValidDate
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate data_nascimento;

  @Email(message = "O email e invalido!")
  private String email;

  private String endereco;

  private String cidade;

  private String telefone;
}
