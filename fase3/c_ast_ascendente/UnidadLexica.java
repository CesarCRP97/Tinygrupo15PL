package alex;

public abstract class UnidadLexica extends Symbol{

    public static class StringLocalizado {
        private int fila; 
        private int columna;
        private String s;
        public StringLocalizado(String s, int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
            this.s = s;
        }
        public int fila() {return fila;}
        public int columna() {return columna;}
        public String str() {return s;}
    }
   public UnidadLexica(int fila, int columna, int clase, String lexema) {
       super(clase, new StringLocalizado(lexema, fila, columna));
   }
   public int clase () {return sym;}
   public int fila() {return ((StringLocalizado)valor).fila();}
   public int columna() {return ((StringLocalizado)valor).columna();}
}
