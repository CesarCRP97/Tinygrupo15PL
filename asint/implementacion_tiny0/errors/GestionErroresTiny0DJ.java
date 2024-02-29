package errors;

import alex.ClaseLexica;
import java.util.Set;

public class GestionErroresTiny0DJ extends GestionErroresTiny0{

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
        throw new ErrorLexico("ERROR fila "+fila+","+col+": Caracter inesperado: "+lexema);
    }  
    public void errorSintactico(int fila, int col, ClaseLexica encontrada, 
            Set<ClaseLexica> esperadas) {
        String msg = "ERROR fila "+fila+","+col+": Encontrado "+encontrada+" Se esperaba: ";
        for(ClaseLexica esperada: esperadas)
            msg += esperada+" ";
        throw new ErrorSintactico(msg);
    }
}


