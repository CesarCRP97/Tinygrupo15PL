package errors;

import alex.UnidadLexica;
import alex.ClaseLexica;
import java.util.Set;

public class GestionErroresTiny0 {

    public static class ErrorLexico extends RuntimeException {
        public ErrorLexico(String msg) {
            super(msg);
        }
    }

    public static class ErrorSintactico extends RuntimeException {
        public ErrorSintactico(String msg) {
            super(msg);
        }
    }
   public void errorLexico(int fila, int col, String lexema) {
     System.out.println("ERROR fila "+fila+","+col+": Caracter inexperado: "+lexema); 
     System.exit(1);
   }  
   public void errorSintactico(int fila, int col, ClaseLexica encontrada, 
                               Set<ClaseLexica> esperadas) {
     System.out.print("ERROR fila "+fila+","+col+": Encontrado "+encontrada+" Se esperaba: ");
     for(ClaseLexica esperada: esperadas)
         System.out.print(esperada+" ");
     System.out.println();
     System.exit(1);
   }
   public void errorFatal(Exception e) {
     System.out.println(e);
     e.printStackTrace();
     System.exit(1);
   }

   public void errorSintactico(UnidadLexica unidadLexica) {
    throw new ErrorSintactico("ERROR fila "+unidadLexica.fila()+", columna "+unidadLexica.columna()+" : Elemento inexperado "+unidadLexica.lexema());
  }
}
