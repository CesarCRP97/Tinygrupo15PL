package alexTiny0;

public class UnidadLexicaUnivaluada extends UnidadLexica {
   public String lexema() {throw new UnsupportedOperationException();}   
   public UnidadLexicaUnivaluada(int fila, int columna, ClaseLexica clase) {
     super(fila,columna,clase);  
   }
  public String toString1() {
    return "[clase:"+clase()+",fila:"+fila()+",col:"+columna()+"]";  
  }
  public String toString() {
    return "[clase:"+clase()+"]";
  }
}
