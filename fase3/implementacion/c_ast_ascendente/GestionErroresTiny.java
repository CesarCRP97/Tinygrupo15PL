package c_ast_ascendente;

public class GestionErroresTiny {
    public void errorLexico(int fila, int columna, String lexema) {
        throw new ErrorLexico("ERROR fila "+fila+", columna"+columna+": Caracter inexperado: "+lexema);
    }  
    public void errorSintactico(UnidadLexica unidadLexica) {
        throw new ErrorSintactico("ERROR fila "+unidadLexica.fila()+": Elemento inexperado: "+unidadLexica.lexema());
    }

    public static class ErrorLexico extends RuntimeException {
        public ErrorLexico(String msg) { super(msg); }
    }
    public static class ErrorSintactico extends RuntimeException {
        public ErrorSintactico(String msg) { super(msg); }
    }
}
