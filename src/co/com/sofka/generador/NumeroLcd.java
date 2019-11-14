package co.com.sofka.generador;

import co.com.sofka.modelo.Dato;
import java.util.Arrays;
import java.util.function.Function;

public class NumeroLcd implements Function<Dato, String[]> {

  private static final DigitoLcd[] DIGITO_LCDS = {
      /*0*/new DigitoLcd(true, false, true, true, true, true, true),
      /*1*/new DigitoLcd(false, false, false, false, false, true, true),
      /*2*/new DigitoLcd(true, true, true, false, true, true, false),
      /*3*/new DigitoLcd(true, true, true, false, false, true, true),
      /*4*/new DigitoLcd(false, true, false, true, false, true, true),
      /*5*/new DigitoLcd(true, true, true, true, false, false, true),
      /*6*/new DigitoLcd(true, true, true, true, true, false, true),
      /*7*/new DigitoLcd(true, false, false, false, false, true, true),
      /*8*/new DigitoLcd(true, true, true, true, true, true, true),
      /*9*/new DigitoLcd(true, true, true, true, false, true, true)
  };

  // como la pelicula XD
  private char[][] getMatrix(int tam, int cifra) {
    final char[][] matrix = new char[2 * tam + 3][tam + 2];
    DigitoLcd dig = DIGITO_LCDS[cifra];
    // ciclo desde 0 hasta antes del tamaño
    for (int i = 1; i <= tam; i++) {
      // horizontales
      matrix[0][i] = dig.sup ? '-' : ' ';
      matrix[matrix.length / 2][i] = dig.med ? '-' : ' ';
      matrix[matrix.length - 1][i] = dig.inf ? '-' : ' ';
      // verticales
      matrix[i][0] = dig.izqsup ? '|' : ' ';
      matrix[i][tam + 1] = dig.dersup ? '|' : ' ';
      matrix[matrix.length / 2 + i][0] = dig.izqinf ? '|' : ' ';
      matrix[matrix.length / 2 + i][tam + 1] = dig.derinf ? '|' : ' ';
    }
    return matrix;
  }

  public String[] apply(Dato dato) {
    // creando vector donde agregaremos todas las cifras del numero en formato LCD
    String[] vectorLcd = new String[2 * dato.getTam() + 3];
    Arrays.fill(vectorLcd, "");
    // obtenemos la matriz de cada cifra
    for (char cifra : dato.getNum().toCharArray()) {
      char[][] matrix = getMatrix(dato.getTam(), Character.getNumericValue(cifra));
      // Agrego cada linea de cada cifra al vector
      for (int i = 0; i < vectorLcd.length; i++) {
        vectorLcd[i] += String.valueOf(matrix[i]);
      }
    }
    return vectorLcd;
  }

  private static class DigitoLcd {

    boolean sup, med, inf, izqsup, izqinf, dersup, derinf;

    public DigitoLcd(boolean sup, boolean med, boolean inf, boolean izqsup, boolean izqinf,
        boolean dersup, boolean derinf) {
      this.sup = sup;
      this.med = med;
      this.inf = inf;
      this.izqsup = izqsup;
      this.izqinf = izqinf;
      this.dersup = dersup;
      this.derinf = derinf;
    }
  }
}