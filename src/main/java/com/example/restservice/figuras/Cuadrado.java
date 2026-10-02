package com.example.restservice.figuras;

public class Cuadrado implements Figura {
  private double lado;

  public double area() { return lado * lado; }
  public double perimetro() { return 4 * lado; }
}
