package com.guiconte.exception;

public class ClienteNotFoundException extends RuntimeException{

  public ClienteNotFoundException() {
    super("Cliente não existente !");
  }
}
