package co.com.sofka.modelo;

public class Dato {

  private int tam;
  private String num;

  public Dato(String[] separados) throws Exception {
    // pasar a la estructura Dato los números sin espacios
    this.tam = Integer.parseInt(separados[0].trim());
    this.num = separados[1].trim();
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
