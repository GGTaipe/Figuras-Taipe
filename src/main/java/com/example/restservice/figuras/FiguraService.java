package com.example.restservice.figuras;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import groovy.lang.GroovyClassLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class FiguraService {

  private final Map<String, Class<?>> figurasGroovy = new ConcurrentHashMap<>();
  private final GroovyClassLoader groovyLoader = new GroovyClassLoader(getClass().getClassLoader());

  public FiguraService() throws IOException {
    cargarGroovy(new ClassPathResource("figuras/Pentagono.groovy").getContentAsString(StandardCharsets.UTF_8));
  }

  public Figura crear(String nombre, Map<String, String> parametros) throws Exception {
    Class<?> clase = buscarClase(nombre);
    Figura figura = (Figura) clase.getDeclaredConstructor().newInstance();

    for (Map.Entry<String, String> p : parametros.entrySet()) {
      Field campo = clase.getDeclaredField(p.getKey());
      campo.setAccessible(true);
      campo.set(figura, Double.parseDouble(p.getValue()));
    }
    return figura;
  }

  public String cargarGroovy(String codigo) {
    Class<?> clase = groovyLoader.parseClass(codigo);
    if (!Figura.class.isAssignableFrom(clase)) {
      throw new IllegalArgumentException(clase.getSimpleName() + " no implementa Figura");
    }
    figurasGroovy.put(clase.getSimpleName(), clase);
    return clase.getSimpleName();
  }

  private Class<?> buscarClase(String nombre) throws ClassNotFoundException {
    if (figurasGroovy.containsKey(nombre)) {
      return figurasGroovy.get(nombre);
    }
    Class<?> clase = Class.forName("com.example.restservice.figuras." + nombre);
    if (!Figura.class.isAssignableFrom(clase) || clase.isInterface()) {
      throw new ClassNotFoundException("No existe la figura " + nombre);
    }
    return clase;
  }
}
