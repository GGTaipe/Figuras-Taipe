package com.example.restservice.figuras;

public class Circulo implements Figura {
  private double radio;

  public double area() { return Math.PI * radio * radio; }
  public double perimetro() { return 2 * Math.PI * radio; }
}
