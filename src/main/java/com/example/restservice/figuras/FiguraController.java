package com.example.restservice.figuras;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FiguraController {

  private final FiguraService service;

  public FiguraController(FiguraService service) {
    this.service = service;
  }

  @GetMapping("/figuras/{nombre}")
  public Map<String, Object> calcular(@PathVariable String nombre,
                                      @RequestParam Map<String, String> parametros) throws Exception {
    Figura figura = service.crear(nombre, parametros);
    return Map.of(
        "figura", nombre,
        "parametros", parametros,
        "area", figura.area(),
        "perimetro", figura.perimetro());
  }

  @PostMapping("/figuras/groovy")
  public String cargarGroovy(@RequestBody String codigo) {
    return "Figura cargada: " + service.cargarGroovy(codigo);
  }
}
