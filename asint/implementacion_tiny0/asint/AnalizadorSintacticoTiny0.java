package asint;

import alex.UnidadLexica;
import alex.AnalizadorLexicoTiny0;
import alex.ClaseLexica;
import errors.GestionErroresTiny0;
import java.io.IOException;
import java.io.Reader;
import java.util.EnumSet;
import java.util.Set;

public class AnalizadorSintacticoTiny0 {
    private UnidadLexica anticipo;       // token adelantado
    private AnalizadorLexicoTiny0 alex;   // analizador léxico 
    private GestionErroresTiny0 errores;  // gestor de errores
    private Set<ClaseLexica> esperados;  // clases léxicas esperadas

    public AnalizadorSintacticoTiny0(Reader input) {
        // se crea el gestor de errores
        errores = new GestionErroresTiny0();
        // se crea el analizador léxico
        try {
            alex = new AnalizadorLexicoTiny0(input);
        } catch (IOException ex) {
            System.out.println("Error al leer el input con el Analizador lexico");
        }
        // se fija el gestor de errores en el analizador léxico
        // (debe añadirse el método 'fijaGestionErrores' a
        //  dicho analizador)
        alex.fijaGestionErrores(errores);
        // se crea el conjunto de clases léxicas esperadas
        // (estará incializado a vacío)
        esperados = EnumSet.noneOf(ClaseLexica.class);
        // Se lee el primer token adelantado
        sigToken();                      
    }
    public void analiza() {
        programa();
        empareja(ClaseLexica.EOF);
    }
    private void programa() {
        bloque();
    }

    private void bloque() {
        empareja(ClaseLexica.LLAVE_APERTURA);
        declaraciones();
        instrucciones();
        empareja(ClaseLexica.LLAVE_CIERRE);
    }



    private void declaraciones() {
        switch(anticipo.clase()) {
            case INT:
			case REAL:
			case BOOL:
                lista_declaraciones();
                empareja(ClaseLexica.DOBLEAMPERSAND);
                break;
            default:        
                esperados(ClaseLexica.INT,ClaseLexica.REAL,ClaseLexica.BOOL);
                break;
        } 
    }

    private void lista_declaraciones() {
        declaracion();
        rlista_decs();
    }

    private void declaracion() {
        tipo();
        empareja(ClaseLexica.IDEN);
    }

    private void tipo() {
        switch(anticipo.clase()) {
            case INT: empareja(ClaseLexica.INT); break;
            case REAL: empareja(ClaseLexica.REAL); break;
            case BOOL: empareja(ClaseLexica.BOOL); break;
            default:
                esperados(ClaseLexica.INT,ClaseLexica.REAL,ClaseLexica.BOOL);
                error();
                break;
        }
    }

    private void rlista_decs() {
        switch(anticipo.clase()) {
            case PUNTOYCOMA:
                empareja(ClaseLexica.PUNTOYCOMA);
                declaracion();
                rlista_decs();
                break;
            default:
                esperados(ClaseLexica.PUNTOYCOMA);
                break;
        }
    }

    private void instrucciones() {
        switch(anticipo.clase()) {
            case ARROBA:
                instruccion();
                rinstrucciones();
                break;
            default:
                esperados(ClaseLexica.ARROBA);
                break;
        }
    }

    private void rinstrucciones() {
        switch(anticipo.clase()) {
            case PUNTOYCOMA:
                empareja(ClaseLexica.PUNTOYCOMA);
                instruccion();
                rinstrucciones();
                break;
            default:
                break;
        }
    }

    private void instruccion() {
        empareja(ClaseLexica.ARROBA);
        expresion();
    }

    private void expresion() {
        E0();
    }


    private void E0() {
        E1();
        RE0();
    }

    private void RE0() {
        switch(anticipo.clase()) {
            case IGUAL: 
                OP0();
                E0();
                break;
            default: 
                esperados(ClaseLexica.IGUAL);
                break;
        }    
    }


    private void E1() {
        E2();
        RE1();
    }

    private void RE1() {
        switch(anticipo.clase()) {
            case IGUAL_COMP:
			case MAYORQUE:
			case MENORQUE:
			case MAYORIGUAL:
			case MENORIGUAL:
			case NOIGUAL_COMP: 
                OP1();
                E2();
                break;
            default: 
                esperados(ClaseLexica.IGUAL_COMP, ClaseLexica.MAYORQUE, ClaseLexica.MENORQUE, ClaseLexica.MAYORIGUAL, ClaseLexica.MENORIGUAL, ClaseLexica.NOIGUAL_COMP);
                break;
        }    
    }

    private void E2() {
        E3();
        RE2();
        RE21();
    }

    private void RE2() {
        switch(anticipo.clase()) {
            case MENOS:
                OP2_NOASOC();
                E3();
                break;
            default:
                esperados(ClaseLexica.MENOS);
                break;
        }
    }
    private void RE21(){
        switch (anticipo.clase()) {
            case MAS:
                OP2_IZQ();
                E3();
                RE21();
                break;
            default:
                esperados(ClaseLexica.MAS);
                break;
        }
    }

    private void E3() {
        E4();
        RE3();
    }

    private void RE3() {
        switch(anticipo.clase()) {
            case AND:
                OP3_DER();
                E3();
                break;
            case OR:
                OP3_NOASOC();
                E4();
                break;
            default:
                esperados(ClaseLexica.AND, ClaseLexica.OR);
                break;
        }
    }

    private void E4() {
        E5();
        RE4();
    }

    private void RE4() {
        switch(anticipo.clase()) {
            case POR:
			case DIV:
                OP4();
                E5();
                RE4();
                break;
            default:
                esperados(ClaseLexica.POR, ClaseLexica.DIV);
                break;
        }
    }

    private void E5() {
        switch(anticipo.clase()) {
            case MENOS:
			case NOT:
                OP5();
                E5();
                break;
            case PAR_APERTURA:
			case ENT:
			case NUM_REAL:
			case TRUE:
			case FALSE:
			case IDEN:
                E6();
                break;
            default:
                esperados(ClaseLexica.MENOS, ClaseLexica.NOT, ClaseLexica.PAR_APERTURA, ClaseLexica.ENT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE, ClaseLexica.FALSE, ClaseLexica.IDEN);
                error();
                break;
        }
    }

    private void E6() {
        switch(anticipo.clase()) {
            case PAR_APERTURA:
                empareja(ClaseLexica.PAR_APERTURA);
                E0();
                empareja(ClaseLexica.PAR_CIERRE);
                break;
            case ENT:
            case NUM_REAL:
            case TRUE:
			case FALSE:
			case IDEN:
                expresion_basica();
                break;
            default:
                esperados(ClaseLexica.PAR_APERTURA, ClaseLexica.ENT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE, ClaseLexica.FALSE, ClaseLexica.IDEN);
                error();
                break;
        }
    }

    private void expresion_basica() {
        switch(anticipo.clase()) {
            case ENT:
                empareja(ClaseLexica.ENT);
                break;
            case NUM_REAL:
                empareja(ClaseLexica.NUM_REAL);
                break;
            case TRUE:
                empareja(ClaseLexica.TRUE);
                break;
            case FALSE:
                empareja(ClaseLexica.FALSE);
                break;
            case IDEN:
                empareja(ClaseLexica.IDEN);
                break;
            default:
                esperados(ClaseLexica.ENT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE, ClaseLexica.FALSE, ClaseLexica.IDEN);
                error();
                break;
        }
    }

    private void OP0() {
        empareja(ClaseLexica.IGUAL);
    }

    private void OP1() {
        switch(anticipo.clase()) {
            case IGUAL_COMP:
                empareja(ClaseLexica.IGUAL_COMP);
                break;
            case MAYORQUE:
                empareja(ClaseLexica.MAYORQUE);
                break;
            case MENORQUE:
                empareja(ClaseLexica.MENORQUE);
                break;
            case MAYORIGUAL:
                empareja(ClaseLexica.MAYORIGUAL);
                break;
            case MENORIGUAL:
                empareja(ClaseLexica.MENORIGUAL);
                break;
            case NOIGUAL_COMP:
                empareja(ClaseLexica.NOIGUAL_COMP);
                break;
            default:
                esperados(ClaseLexica.IGUAL_COMP, ClaseLexica.MAYORQUE, ClaseLexica.MENORQUE, ClaseLexica.MAYORIGUAL, ClaseLexica.MENORIGUAL, ClaseLexica.NOIGUAL_COMP);
                error();
                break;
        }
    }

    private void OP2_NOASOC() {
        empareja(ClaseLexica.MENOS);
    }

    private void OP2_IZQ() {
        empareja(ClaseLexica.MAS);
    }

    private void OP3_DER() {
        empareja(ClaseLexica.AND);
    }

    private void OP3_NOASOC() {
        empareja(ClaseLexica.OR);
    }

    private void OP4() {
        switch(anticipo.clase()) {
            case POR:
                empareja(ClaseLexica.POR);
                break;
            case DIV:
                empareja(ClaseLexica.DIV);
                break;
            default:
                esperados(ClaseLexica.POR, ClaseLexica.DIV);
                error();
                break;
        }
    }

    private void OP5() {
        switch(anticipo.clase()) {
            case MENOS:
                empareja(ClaseLexica.MENOS);
                break;
            case NOT:
                empareja(ClaseLexica.NOT);
                break;
            default:
                esperados(ClaseLexica.MENOS, ClaseLexica.NOT);
                error();
                break;
        }
    }


    private void esperados(ClaseLexica ... esperadas) {
        for(ClaseLexica c: esperadas) {
            esperados.add(c);
        }
    }


    private void empareja(ClaseLexica claseEsperada) {
        if (anticipo.clase() == claseEsperada)
            sigToken();
        else {
            esperados(claseEsperada);
            error();
        }
    }

    private void sigToken() {
        try {
            anticipo = alex.sigToken();
            esperados.clear();
        }
        catch(IOException e) {
            errores.errorFatal(e);
        }
    }

    private void error() {
        errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), esperados);
    }

    }
