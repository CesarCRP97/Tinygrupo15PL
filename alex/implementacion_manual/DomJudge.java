import alex.AnalizadorLexicoTiny0.ECaracterInesperado;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny0;
import alex.ClaseLexica;
import alex.UnidadLexica;

public class DomJudge {
	private static void imprime(UnidadLexica unidad) {
		switch(unidad.clase()) {
		   case IDEN: case ENT: case NUM_REAL: System.out.println(unidad.lexema()); break;
                   default: System.out.println(unidad.clase().getImage());
		}
	}	

   public static void main(String[] args) throws IOException {
     Reader input  = new InputStreamReader(System.in);
     AnalizadorLexicoTiny0 al = new AnalizadorLexicoTiny0(input);
     UnidadLexica unidad = null;
     do {
       try {
           unidad = al.sigToken();
           imprime(unidad);
       }
       catch(ECaracterInesperado e) {
              System.out.println("ERROR");
       }
     }
     while (unidad == null || unidad.clase() != ClaseLexica.EOF);
    }        
} 
