package alex;

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
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INT);
   }

   public UnidadLexica unidadReal() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.REAL);
   }

   public UnidadLexica unidadBool() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.BOOL);
   }

   public UnidadLexica unidadString() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.STRING);
   }

   public UnidadLexica unidadAnd() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.AND);
   }

   public UnidadLexica unidadOr() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OR);
   }

   public UnidadLexica unidadNot() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NOT);
   }

   public UnidadLexica unidadNull() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NULL);
   }

   public UnidadLexica unidadTrue() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TRUE);
   }

   public UnidadLexica unidadFalse() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FALSE);
   }

   public UnidadLexica unidadProc() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PROC);
   }

   public UnidadLexica unidadIf() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IF);
   }

   public UnidadLexica unidadElse() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ELSE);
   }

   public UnidadLexica unidadWhile() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.WHILE);
   }

   public UnidadLexica unidadStruct() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.STRUCT);
   }

   public UnidadLexica unidadNew() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NEW);
   }

   public UnidadLexica unidadDelete() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DELETE);
   }

   public UnidadLexica unidadRead() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.READ);
   }

   public UnidadLexica unidadWrite() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.WRITE);
   }

   public UnidadLexica unidadType() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TYPE);
   }

   public UnidadLexica unidadCall() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CALL);
   }

   public UnidadLexica unidadSuma() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.SUMA);
   }

   public UnidadLexica unidadResta() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.RESTA);
   }

   public UnidadLexica unidadMult() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MULT);
   }

   public UnidadLexica unidadDiv() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DIV);
   }

   public UnidadLexica unidadPorcentaje() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PORCENTAJE);
   }

   public UnidadLexica unidadMenorQue() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENORQUE);
   }

   public UnidadLexica unidadMayorQue() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYORQUE);
   }

   public UnidadLexica unidadMenorIgual() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL);
   }

   public UnidadLexica unidadMayorIgual() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL);
   }

   public UnidadLexica unidadIgualComp() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IGUAL_COMP);
   }

   public UnidadLexica unidadNoIgualComp() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NOIGUAL_COMP);
   }

   public UnidadLexica unidadParA() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PAR_A);
   }

   public UnidadLexica unidadParC() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PAR_C);
   }

   public UnidadLexica unidadPuntoYComa() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTOYCOMA);
   }

   public UnidadLexica unidadIgualOp() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IGUAL_OP);
   }

   public UnidadLexica unidadCorchA() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCH_A);
   }

   public UnidadLexica unidadCorchC() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCH_C);
   }

   public UnidadLexica unidadPunto() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO);
   }

   public UnidadLexica unidadCapirote() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CAPIROTE);
   }

   public UnidadLexica unidadComa() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.COMA);
   }

   public UnidadLexica unidadLlaveA() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_A);
   }

   public UnidadLexica unidadLlaveC() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_C);
   }

   public UnidadLexica unidadAmpersand() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.AMPERSAND);
   }

   public UnidadLexica unidadDobleAmpersand() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DOBLEAMPERSAND);
   }

   public UnidadLexica unidadArroba() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ARROBA);
   }

   public UnidadLexica unidadLitEntero() {
      return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LITENTERO, alex.lexema());
   }

   public UnidadLexica unidadLitReal() {
      return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LITREAL, alex.lexema());
   }

   public UnidadLexica unidadIden() {
      return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.IDEN,  alex.lexema());
   }

   public UnidadLexica unidadLitCadena() {
      return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LITCADENA, alex.lexema());
   }

   public UnidadLexica unidadNl() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NL);
   }

   public UnidadLexica unidadEof() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.EOF);
   }

   public void error() {
      throw new ECaracterInesperado("***"+alex.fila()+","+alex.columna()+": Caracter inexperado: "+alex.lexema());
   }
}
