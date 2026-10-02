package com.example.restservice.figuras;

public class Rectangulo implements Figura {
  private double base;
  private double altura;

  public double area() { return base * altura; }
  public double perimetro() { return 2 * (base + altura); }
}
