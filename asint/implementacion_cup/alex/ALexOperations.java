package alex;

import alex.ClaseLexica;

public class ALexOperations {
    public static class ECaracterInesperado extends RuntimeException {
      public ECaracterInesperado(String msg) {
          super(msg);
      }
    }
   private AnalizadorLexicoTiny alex;

   public ALexOperations(AnalizadorLexicoTiny alex) {
      this.alex = alex;
   }

   public UnidadLexica unidadInt() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INT,alex.lexema());
   }

   public UnidadLexica unidadReal() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.REAL,alex.lexema());
   }

   public UnidadLexica unidadBool() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.BOOL,alex.lexema());
   }

   public UnidadLexica unidadString() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.STRING,alex.lexema());
   }

   public UnidadLexica unidadAnd() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.AND,alex.lexema());
   }

   public UnidadLexica unidadOr() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OR,alex.lexema());
   }

   public UnidadLexica unidadNot() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NOT,alex.lexema());
   }

   public UnidadLexica unidadNull() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NULL,alex.lexema());
   }

   public UnidadLexica unidadTrue() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TRUE,alex.lexema());
   }

   public UnidadLexica unidadFalse() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FALSE,alex.lexema());
   }

   public UnidadLexica unidadProc() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PROC,alex.lexema());
   }

   public UnidadLexica unidadIf() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IF,alex.lexema());
   }

   public UnidadLexica unidadElse() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ELSE,alex.lexema());
   }

   public UnidadLexica unidadWhile() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.WHILE,alex.lexema());
   }

   public UnidadLexica unidadStruct() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.STRUCT,alex.lexema());
   }

   public UnidadLexica unidadNew() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NEW,alex.lexema());
   }

   public UnidadLexica unidadDelete() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DELETE,alex.lexema());
   }

   public UnidadLexica unidadRead() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.READ,alex.lexema());
   }

   public UnidadLexica unidadWrite() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.WRITE,alex.lexema());
   }

   public UnidadLexica unidadType() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TYPE,alex.lexema());
   }

   public UnidadLexica unidadCall() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CALL,alex.lexema());
   }

   public UnidadLexica unidadSuma() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.SUMA,alex.lexema());
   }

   public UnidadLexica unidadResta() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.RESTA,alex.lexema());
   }

   public UnidadLexica unidadMult() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MULT,alex.lexema());
   }

   public UnidadLexica unidadDiv() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DIV,alex.lexema());
   }

   public UnidadLexica unidadPorcentaje() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PORCENTAJE,alex.lexema());
   }

   public UnidadLexica unidadMenorQue() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENORQUE,alex.lexema());
   }

   public UnidadLexica unidadMayorQue() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYORQUE,alex.lexema());
   }

   public UnidadLexica unidadMenorIgual() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL,alex.lexema());
   }

   public UnidadLexica unidadMayorIgual() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL,alex.lexema());
   }

   public UnidadLexica unidadIgualComp() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IGUAL_COMP,alex.lexema());
   }

   public UnidadLexica unidadNoIgualComp() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NOIGUAL_COMP,alex.lexema());
   }

   public UnidadLexica unidadParA() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PAR_A,alex.lexema());
   }

   public UnidadLexica unidadParC() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PAR_C,alex.lexema());
   }

   public UnidadLexica unidadPuntoYComa() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTYCOMA,alex.lexema());
   }

   public UnidadLexica unidadIgualOp() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IGUAL_OP,alex.lexema());
   }

   public UnidadLexica unidadCorchA() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCH_A,alex.lexema());
   }

   public UnidadLexica unidadCorchC() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCH_C,alex.lexema());
   }

   public UnidadLexica unidadPunto() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO,alex.lexema());
   }

   public UnidadLexica unidadCapirote() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CAPIROTE,alex.lexema());
   }

   public UnidadLexica unidadComa() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.COMA,alex.lexema());
   }

   public UnidadLexica unidadLlaveA() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_A,alex.lexema());
   }

   public UnidadLexica unidadLlaveC() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_C,alex.lexema());
   }

   public UnidadLexica unidadAmpersand() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.AMPERSAND,alex.lexema());
   }

   public UnidadLexica unidadDobleAmpersand() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DOBLEAMPERSAND,alex.lexema());
   }

   public UnidadLexica unidadArroba() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ARROBA,alex.lexema());
   }

   public UnidadLexica unidadLitEntero() {
      return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LIT_ENTERO,alex.lexema());
   }

   public UnidadLexica unidadLitReal() {
      return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LIT_REAL,alex.lexema());
   }

   public UnidadLexica unidadIden() {
      return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.IDEN,alex.lexema());
   }

   public UnidadLexica unidadLitCadena() {
      return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LIT_CADENA,alex.lexema());
   }

   public UnidadLexica unidadNl() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NL,alex.lexema());
   }

   public UnidadLexica unidadEof() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.EOF,alex.lexema());
   }

   public void error() {
      throw new ECaracterInesperado("***"+alex.fila()+","+alex.columna()+": Caracter inexperado: "+alex.lexema());
   }
}
