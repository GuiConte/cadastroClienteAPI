package com.guiconte.domain.entity;

import java.math.BigInteger;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
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

  @NotEmpty(message = "O campo nome é obrigatorio!")
  private String nome;

  @NotEmpty(message = "O campo cpf é obrigatorio!")
  @CPF(message = "O cpf é invalido!")
  private String cpf;

  @NotNull(message = "O campo idade é obrigatorio!")
  @Min(value = 0,message = "A idade é invalida! ")
  private Integer idade;

  @NotEmpty(message = "O campo email é obrigatorio!")
  @Email(message = "O email é invalido")
  private String email;

  @NotEmpty(message = "O campo endereço é obrigatorio!")
  private String endereco;

  @NotEmpty(message = "O campo cidade é obrigatorio!")
  private String cidade;

  @NotEmpty(message = "O campo telefone é obrigatorio!")
  private String telefone;

}
