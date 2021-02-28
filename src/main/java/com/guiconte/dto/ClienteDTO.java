package com.guiconte.dto;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class ClienteDTO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger cod_cliente;

  @Column(length = 100)
  private String nome;

  @Column(length = 11)
  private String cpf;

  @Column
  private Integer idade;

  @Column(length = 70)
  private String email;

  @Column(length = 100)
  private String endereco;

  @Column(length = 70)
  private String cidade;

  @Column(length = 30)
  private String telefone;

}
