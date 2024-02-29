package asint;

import java.io.Reader;
import alex.UnidadLexica;
import errors.GestionErroresTiny0;


public class AnalizadorSintacticoTiny0DJ extends AnalizadorSintacticoTiny0 {

    public AnalizadorSintacticoTiny0DJ(Reader input) {
        super(input);
    }

    public AnalizadorSintacticoTiny0DJ(Reader input, GestionErroresTiny0 errores) {
        super(input, errores);
    }

    @Override
    protected void traza_emparejamiento(UnidadLexica anticipo) {
        switch(anticipo.clase()) {
            case IDEN: case ENT: case NUM_REAL: System.out.println(anticipo.lexema()); break;
            default: System.out.println(anticipo.clase().getImage());
        }
    }
}
