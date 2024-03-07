package alex;
import asint.ClaseLexica;
import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {
   private int fila;
   private int col;
   private String lexema;
   public UnidadLexica(int fila, int col, int clase, String lexema) {
     super(clase,lexema);
	 this.fila = fila;
	 this.col = col;
	 this.lexema=lexema;
	 System.out.println(this.toString());
   }
   public String toString() {
       return clase2string(sym);
   }
   private String clase2string(int clase) {
       switch(clase) {
       case ClaseLexica.INT: return "<int>";
       case ClaseLexica.BOOL: return "<bool>";
       case ClaseLexica.STRING: return "<string>";
       case ClaseLexica.AND: return "<and>";
       case ClaseLexica.OR: return "<or>";
       case ClaseLexica.NOT: return "<not>";
       case ClaseLexica.NULL: return "<null>";
       case ClaseLexica.TRUE: return "<true>";
       case ClaseLexica.FALSE: return "<false>";
       case ClaseLexica.PROC: return "<proc>";
       case ClaseLexica.IF: return "<if>";
       case ClaseLexica.ELSE: return "<else>";
       case ClaseLexica.WHILE: return "<while>";
       case ClaseLexica.STRUCT: return "<struct>";
       case ClaseLexica.NEW: return "<new>";
       case ClaseLexica.DELETE: return "<delete>";
       case ClaseLexica.READ: return "<read>";
       case ClaseLexica.WRITE: return "<write>";
       case ClaseLexica.NL: return "<nl>";
       case ClaseLexica.TYPE: return "<type>";
       case ClaseLexica.CALL: return "<call>";
       case ClaseLexica.SUMA: return "+";
       case ClaseLexica.RESTA: return "-";
       case ClaseLexica.MULT: return "*";
       case ClaseLexica.DIV: return ("/");
       case ClaseLexica.PORCENTAJE: return "%";
       case ClaseLexica.MENORQUE: return "<";
       case ClaseLexica.MAYORQUE: return ">";
       case ClaseLexica.MENORIGUAL: return "<=";
       case ClaseLexica.MAYORIGUAL: return ">=";
       case ClaseLexica.IGUAL_COMP: return "==";
       case ClaseLexica.NOIGUAL_COMP: return "!=";
       case ClaseLexica.PAR_A: return "(";
       case ClaseLexica.PAR_C: return ")";
       case ClaseLexica.PUNTYCOMA: return ";";
       case ClaseLexica.IGUAL_OP: return "=";
       case ClaseLexica.CORCH_A: return "[";
       case ClaseLexica.CORCH_C: return "]";
       case ClaseLexica.PUNTO: return ".";
       case ClaseLexica.CAPIROTE: return "^";
       case ClaseLexica.COMA: return ",";
       case ClaseLexica.LLAVE_A: return "{";
       case ClaseLexica.LLAVE_C: return "}";
       case ClaseLexica.AMPERSAND: return "&";
       case ClaseLexica.DOBLEAMPERSAND: return "&&";
       case ClaseLexica.ARROBA: return "@";
       case ClaseLexica.LIT_ENTERO: return lexema;
       case ClaseLexica.IDEN: return lexema;
       case ClaseLexica.LIT_CADENA: return lexema;
       case ClaseLexica.LIT_REAL: return lexema ;
       case ClaseLexica.EOF: return "<EOF>";
       
         default: return "?";               
       }
    }
   public int clase () {return sym;}
   public String lexema() {return (String)value;}
   public int fila() {return fila;}
   public int columna() {return col;}
}
