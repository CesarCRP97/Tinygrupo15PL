import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import asint.AnalizadorSintacticoTinyDJ;
import asint.AnalizadorSintacticoTiny;
import asint.TokenMgrError;
import asint.ParseException;


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

