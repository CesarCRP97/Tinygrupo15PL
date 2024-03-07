package implementacion_cup;

import implementacion_cup.alex.AnalizadorLexicoTiny;
import implementacion_cup.asint.AnalizadorSintacticoTiny;
import implementacion_cup.asint.AnalizadorSintacticoTinyDJ;
import implementacion_cup.errors.GestionErroresTiny.ErrorLexico;
import implementacion_cup.errors.GestionErroresTiny.ErrorSintactico;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class DomJudge {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(System.in);
     AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
     AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTinyDJ(alex);
	 //asint.setScanner(alex);
     try {    
        asint.debug_parse();
     }
     catch(ErrorLexico e) {
        System.out.println("ERROR_LEXICO"); 
     }
     catch(ErrorSintactico e) {
        System.out.println("ERROR_SINTACTICO"); 
     }
 }

}
