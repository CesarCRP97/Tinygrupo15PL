package c_ast_ascendente;


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
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INT,"<int>");
   }

   public UnidadLexica unidadReal() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REAL,"<real>");
   }

   public UnidadLexica unidadBool() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BOOL,"<bool>");
   }

   public UnidadLexica unidadString() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRING,"<string>");
   }

   public UnidadLexica unidadAnd() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND,"<and>");
   }

   public UnidadLexica unidadOr() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OR,"<or>");
   }

   public UnidadLexica unidadNot() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NOT,"<not>");
   }

   public UnidadLexica unidadNull() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NULL,"<null>");
   }

   public UnidadLexica unidadTrue() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRUE,"<true>");
   }

   public UnidadLexica unidadFalse() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSE,"<false>");
   }

   public UnidadLexica unidadProc() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PROC,"<proc>");
   }

   public UnidadLexica unidadIf() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IF,"<if>");
   }

   public UnidadLexica unidadElse() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ELSE,"<else>");
   }

   public UnidadLexica unidadWhile() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WHILE,"<while>");
   }

   public UnidadLexica unidadStruct() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRUCT,"<struct>");
   }

   public UnidadLexica unidadNew() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NEW,"<new>");
   }

   public UnidadLexica unidadDelete() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DELETE,"<delete>");
   }

   public UnidadLexica unidadRead() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.READ,"<read>");
   }

   public UnidadLexica unidadWrite() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WRITE,"<write>");
   }

   public UnidadLexica unidadType() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TYPE,"<type>");
   }

   public UnidadLexica unidadCall() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CALL,"<call>");
   }

   public UnidadLexica unidadSuma() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SUMA,"+");
   }

   public UnidadLexica unidadResta() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RESTA,"-");
   }

   public UnidadLexica unidadMult() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MULT,"*");
   }

   public UnidadLexica unidadDiv() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIV,"/");
   }

   public UnidadLexica unidadPorcentaje() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PORCENTAJE,"%");
   }

   public UnidadLexica unidadMenorQue() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENORQUE,"<");
   }

   public UnidadLexica unidadMayorQue() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYORQUE,">");
   }

   public UnidadLexica unidadMenorIgual() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL,"<=");
   }

   public UnidadLexica unidadMayorIgual() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL,">=");
   }

   public UnidadLexica unidadIgualComp() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL_COMP,"==");
   }

   public UnidadLexica unidadNoIgualComp() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NOIGUAL_COMP,"!=");
   }

   public UnidadLexica unidadParA() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAR_A,"(");
   }

   public UnidadLexica unidadParC() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAR_C,")");
   }

   public UnidadLexica unidadPuntoYComa() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTYCOMA,";");
   }

   public UnidadLexica unidadIgualOp() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL_OP,"=");
   }

   public UnidadLexica unidadCorchA() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CORCH_A,"[");
   }

   public UnidadLexica unidadCorchC() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CORCH_C,"]");
   }

   public UnidadLexica unidadPunto() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO,".");
   }

   public UnidadLexica unidadCapirote() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CAPIROTE,"^");
   }

   public UnidadLexica unidadComa() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA,",");
   }

   public UnidadLexica unidadLlaveA() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVE_A,"{");
   }

   public UnidadLexica unidadLlaveC() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVE_C,"}");
   }

   public UnidadLexica unidadAmpersand() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AMPERSAND,"&");
   }

   public UnidadLexica unidadDobleAmpersand() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DOBLEAMPERSAND,"&&");
   }

   public UnidadLexica unidadArroba() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ARROBA,"@");
   }

   public UnidadLexica unidadLitEntero() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LIT_ENTERO,alex.lexema());
   }

   public UnidadLexica unidadLitReal() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LIT_REAL,alex.lexema());
   }

   public UnidadLexica unidadIden() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDEN,alex.lexema());
   }

   public UnidadLexica unidadLitCadena() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LIT_CADENA,alex.lexema());
   }

   public UnidadLexica unidadNl() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NL,"<nl>");
   }

   public UnidadLexica unidadEof() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF,"<EOF>");
   }
}
