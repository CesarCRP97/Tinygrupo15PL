import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny0;
import alex.UnidadLexica;
import alex.ClaseLexica;
import asint.AnalizadorSintacticoTiny0DJ;
import errors.GestionErroresTiny0;
import errors.GestionErroresTiny0DJ;
import errors.GestionErroresTiny0DJ.ErrorLexico;
import errors.GestionErroresTiny0DJ.ErrorSintactico;

public class DomJudge {
    private static void imprime(UnidadLexica unidad) {
        switch(unidad.clase()) {
            case IDEN: case ENT: case NUM_REAL: System.out.println(unidad.lexema()); break;
            default: System.out.println(unidad.clase().getImage());
        }
    }	

    public static void main(String[] args) throws IOException {
        Reader input  = new InputStreamReader(System.in);
        GestionErroresTiny0DJ errores = new GestionErroresTiny0DJ();
        AnalizadorSintacticoTiny0DJ asint = new AnalizadorSintacticoTiny0DJ(input, errores);
        try {
            asint.analiza();
        }
        catch(ErrorLexico elex) {
            System.out.println("ERROR_LEXICO");
        }
        catch(ErrorSintactico esint) {
            System.out.println("ERROR_SINTACTICO");
        }
    } 
}

