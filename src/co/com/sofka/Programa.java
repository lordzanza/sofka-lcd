package co.com.sofka;

import co.com.sofka.generador.NumeroLcd;
import co.com.sofka.modelo.Dato;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Programa {

  // flujo de ingreso
  private InputStream ingreso;
  // utilidad conversora
  private Function<Dato, String[]> conversor;
  // flujo de impresion
  private PrintStream salida;

  private Programa(InputStream ingreso, Function<Dato, String[]> conversor, PrintStream salida) {
    this.ingreso = ingreso;
    this.conversor = conversor;
    this.salida = salida;
  }

  // El metodo principal de la ejecución, no hace parte de la instancia al ser estatico
  public static void main(String[] args) throws Exception {
    Programa proceso = new Programa(System.in, new NumeroLcd(), System.out);
    // 1. imprimir opciones y leer datos
    System.out.println("ingrese un dato por linea, primero un tamaño de 1 a 10 y luego un numero,"
        + " separados por coma, para terminar ingrese 0,0 :");
    List<Dato> datos = proceso.getDatos();
    // 2. convertir formato lcd
    List<String> lcd = proceso.toLcd(datos);
    // 3. escribir lcd en pantalla
    proceso.print(lcd);
  }

  private List<Dato> getDatos() throws Exception {
    List<Dato> datos = new ArrayList<>();
    Scanner lector = new Scanner(ingreso);
    String linea = "";
    // pongo ! para un not equals
    while (!linea.equals("0,0")) {
      linea = lector.nextLine();
      if (!linea.equals("0,0")) {
        // separo por coma el registro y lo creo como una estructura de Dato
        String[] separados = linea.split(",");
        datos.add(new Dato(separados));
      }
    }
    return datos;
  }

  private List<String> toLcd(List<Dato> datos) {
    List<String> lcd = new ArrayList<>();
    for (Dato dato : datos) {
      lcd.add("");
      lcd.addAll(Arrays.asList(conversor.apply(dato)));
    }
    return lcd;
  }

  private void print(List<String> lineas) {
    for (String renglon : lineas) {
      salida.println(renglon);
    }
  }

}
