package co.com.sofka.modelo;

public class Dato {

  private int tam;
  private String num;

  public Dato(String[] separados) throws Exception {
    this.tam = Integer.parseInt(separados[0]);
    this.num = separados[1];
    // el simbolo || es para O
    if (this.tam < 1 || this.tam > 10) {
      throw new Exception("tamaño fuera de limites");
    }
  }

  public int getTam() {
    return tam;
  }

  public String getNum() {
    return num;
  }

}
