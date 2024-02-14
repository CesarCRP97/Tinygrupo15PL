package alexTiny0;

public class UnidadLexicaMultivaluada extends UnidadLexica {
  private String lexema;
  public UnidadLexicaMultivaluada(int fila, int columna, ClaseLexica clase, String lexema) {
     super(fila,columna,clase);  
     this.lexema = lexema;
   }
  public String lexema() {return lexema;}
  public String toString1() {
    return "[clase:"+clase()+",fila:"+fila()+",col:"+columna()+",lexema:"+lexema()+"]";  
  }
  public String toString() {
    return "[clase:"+clase()+",lexema:"+lexema()+"]";
  }
}
