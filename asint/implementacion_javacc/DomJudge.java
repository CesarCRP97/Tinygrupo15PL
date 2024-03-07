package implementacion_javacc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import implementacion_javacc.asint.AnalizadorSintacticoTinyDJ;
import implementacion_javacc.asint.AnalizadorSintacticoTiny;
import implementacion_javacc.asint.TokenMgrError;
import implementacion_javacc.asint.ParseException;


public class DomJudge {


    public static void main(String[] args) throws IOException {
        AnalizadorSintacticoTinyDJ asint = new AnalizadorSintacticoTinyDJ(System.in);
        try {
            asint.analiza();
        }
        catch(TokenMgrError elex) {
            System.out.println("ERROR_LEXICO");
        }
        catch(ParseException esint) {
            System.out.println("ERROR_SINTACTICO");
        }
    } 
}

