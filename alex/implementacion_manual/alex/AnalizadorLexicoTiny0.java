package alex;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AnalizadorLexicoTiny0 {

    public static class ECaracterInesperado extends RuntimeException {
       public ECaracterInesperado(String msg) {
           super(msg);
       }
    };

    private Reader input;
    private StringBuffer lex;
    private int sigCar;
    private int filaInicio;
    private int columnaInicio;
    private int filaActual;
    private int columnaActual;
    private static String NL = System.getProperty("line.separator");

    private static enum Estado {
        INICIO, REC_EOF, REC_COM0, REC_COM1, REC_MULT, REC_DIV, REC_MENORQUE, REC_MAYORQUE, REC_MENORIGUAL, REC_MAYORIGUAL,
        REC_IGUAL, REC_IGUALCOMPARACION, REC_PAP, REC_PCIERR, REC_PUNTOCOMA, REC_LLAVEAP, REC_LLAVECIERR, REC_ARROBA,
        REC_ID, REC_AMPERSAN0, REC_AMPERSAN1, REC_MAS, REC_MENOS, REC_0, REC_ENT, REC_IDEC, REC_DEC, REC_0DEC, REC_IEXP,
        REC_EXP, REC_EXP0, REC_EXPSIGN, REC_NOIGUAL0, REC_NOIGUAL1
    }

    private Estado estado;

    public AnalizadorLexicoTiny0(Reader input) throws IOException {
        this.input = input;
        lex = new StringBuffer();
        sigCar = input.read();
        filaActual = 1;
        columnaActual = 1;
    }

    public UnidadLexica sigToken() throws IOException {
        estado = Estado.INICIO;
        filaInicio = filaActual;
        columnaInicio = columnaActual;
        lex.delete(0, lex.length());
        while (true) {
            switch (estado) {
                case INICIO:
                    if (hayBarraBaja() || hayLetra()) transita(Estado.REC_ID);
                    else if (hayDigitoPos()) transita(Estado.REC_ENT);
                    else if (hayCero()) transita(Estado.REC_0);
                    else if (haySuma()) transita(Estado.REC_MAS);
                    else if (hayResta()) transita(Estado.REC_MENOS);
                    else if (hayMul()) transita(Estado.REC_MULT);
                    else if (hayDiv()) transita(Estado.REC_DIV);
                    else if (hayPAp()) transita(Estado.REC_PAP);
                    else if (hayPCierre()) transita(Estado.REC_PCIERR);
                    else if (hayLlaveApertura()) transita(Estado.REC_LLAVEAP);
                    else if (hayLlaveCierre()) transita(Estado.REC_LLAVECIERR);
                    else if (hayIgual()) transita(Estado.REC_IGUAL);
                    else if (hayExclamacion()) transita(Estado.REC_NOIGUAL0);
                    else if (hayPuntoYComa()) transita(Estado.REC_PUNTOCOMA);
                    else if (hayMenorQue()) transita(Estado.REC_MENORQUE);
                    else if (hayMayorQue()) transita(Estado.REC_MAYORQUE);
                    else if (hayArroba()) transita(Estado.REC_ARROBA);
                    else if (hayAmpersand()) transita(Estado.REC_AMPERSAN0);
                    else if (hayAlmohadilla()) transitaIgnorando(Estado.REC_COM0);
                    else if (haySep()) transitaIgnorando(Estado.INICIO);
                    else if (hayEOF()) transita(Estado.REC_EOF);
                    else error();
                    break;
                case REC_ID:
                    if (hayLetra() || hayDigito() || hayBarraBaja()) transita(Estado.REC_ID);
                    else return unidadId();
                    break;
                case REC_ENT:
                    if (hayDigito()) transita(Estado.REC_ENT);
                    else if (hayPunto()) transita(Estado.REC_IDEC);
                    else if (hayE()) transita (Estado.REC_IEXP);
                    else return unidadEnt();
                    break;
                case REC_0:
                    if (hayPunto()) transita(Estado.REC_IDEC);
                    else if (hayE()) transita(Estado.REC_IEXP);
                    else return unidadEnt();
                    break;
                case REC_MAS:
                    if (hayDigitoPos()) transita(Estado.REC_ENT);
                    else if (hayCero()) transita(Estado.REC_0);
                    else return unidadMas();
                    break;
                case REC_MENOS:
                    if (hayDigitoPos()) transita(Estado.REC_ENT);
                    else if (hayCero()) transita(Estado.REC_0);
                    else return unidadMenos();
                    break;
                case REC_MULT:
                    return unidadPor();
                case REC_DIV:
                    return unidadDiv();
                case REC_MAYORQUE:
                    if (hayIgual()) transita(Estado.REC_MAYORIGUAL);
                    else return unidadMayorQue();
                    break;
                case REC_MENORQUE:
                    if (hayIgual()) transita(Estado.REC_MENORIGUAL);
                    else return unidadMenorQue();
                    break;
                case REC_MAYORIGUAL:
                    return unidadMayorIgual();
                case REC_MENORIGUAL:
                    return unidadMenorIgual();
                case REC_IGUAL:
                    if (hayIgual()) transita(Estado.REC_IGUALCOMPARACION);
                    else return unidadIgual();
                    break;
                case REC_IGUALCOMPARACION:
                    return unidadIgualComparacion();
                case REC_NOIGUAL0:
                    if(hayIgual()) transita(Estado.REC_NOIGUAL1);
                    else error();
                    break;
                case REC_NOIGUAL1:
                    return unidadNoIgual();
                case REC_PAP:
                    return unidadPAp();
                case REC_PCIERR:
                    return unidadPCierre();
                case REC_LLAVEAP:
                    return unidadLlaveApertura();
                case REC_LLAVECIERR:
                    return unidadLlaveCierre();
                case REC_PUNTOCOMA:
                    return unidadPuntoYComa();
                case REC_ARROBA:
                    return unidadArroba();
                case REC_AMPERSAN0:
                    if (hayAmpersand()) transita(Estado.REC_AMPERSAN1);
                    else error();
                    break;
                case REC_AMPERSAN1:
                    return unidadAmpersand();
                case REC_COM0:
                    if (hayAlmohadilla()) transitaIgnorando(Estado.REC_COM1);
                    else error();
                    break;
                case REC_COM1:
                    if (hayNL()) transitaIgnorando(Estado.INICIO);
                    else if (hayEOF()) transita(Estado.REC_EOF);
                    else transitaIgnorando(Estado.REC_COM1);
                    break;
                case REC_EOF:
                    return unidadEof();
                case REC_IDEC:
                    if (hayDigito()) transita(Estado.REC_DEC);
                    else error();
                    break;
                case REC_DEC:
                    if (hayDigitoPos()) transita(Estado.REC_DEC);
                    else if (hayCero()) transita(Estado.REC_0DEC);
                    else if (hayE()) transita(Estado.REC_IEXP);
                    else return unidadReal();
                    break;
                case REC_0DEC:
                    if (hayCero()) transita(Estado.REC_0DEC);
                    if (hayDigitoPos()) transita(Estado.REC_DEC);
                    else error();
                    break;
                case REC_IEXP:
                    if (hayCero()) transita(Estado.REC_EXP0);
                    else if (hayDigitoPos()) transita(Estado.REC_EXP);
                    else if (haySuma() || hayResta()) transita(Estado.REC_EXPSIGN);
                    else error();
                    break;
                case REC_EXP0:
                    return unidadReal();
                case REC_EXP:
                    if (hayDigito()) transita(Estado.REC_EXP);
                    else return unidadReal();
                    break;
                case REC_EXPSIGN:
                    if (hayCero()) transita(Estado.REC_EXP0);
                    else if (hayDigitoPos()) transita(Estado.REC_EXP);
                    else error();
                    break;
            }
        }
    }

    private void transita(Estado sig) throws IOException {
        lex.append((char) sigCar);
        sigCar();
        estado = sig;
    }

    private void transitaIgnorando(Estado sig) throws IOException {
        sigCar();
        filaInicio = filaActual;
        columnaInicio = columnaActual;
        estado = sig;
    }

    private void sigCar() throws IOException {
        sigCar = input.read();
        if (sigCar == NL.charAt(0)) saltaFinDeLinea();
        if (sigCar == '\n') {
            filaActual++;
            columnaActual = 0;
        } else {
            columnaActual++;
        }
    }

    private void saltaFinDeLinea() throws IOException {
        for (int i = 1; i < NL.length(); i++) {
            sigCar = input.read();
            if (sigCar != NL.charAt(i)) error();
        }
        sigCar = '\n';
    }

    private boolean hayBarraBaja() {
        return sigCar == '_';
    }

    private boolean hayLetra() {
        return sigCar >= 'a' && sigCar <= 'z' ||
                sigCar >= 'A' && sigCar <= 'Z';
    }

    private boolean hayDigitoPos() {
        return sigCar >= '1' && sigCar <= '9';
    }

    private boolean hayE() {
        return sigCar == 'e' || sigCar == 'E';
    }

    private boolean hayCero() {
        return sigCar == '0';
    }

    private boolean hayDigito() {
        return hayDigitoPos() || hayCero();
    }

    private boolean haySuma() {
        return sigCar == '+';
    }

    private boolean hayResta() {
        return sigCar == '-';
    }

    private boolean hayMul() {
        return sigCar == '*';
    }

    private boolean hayDiv() {
        return sigCar == '/';
    }

    private boolean hayPAp() {
        return sigCar == '(';
    }

    private boolean hayPCierre() {
        return sigCar == ')';
    }

    private boolean hayLlaveApertura() {
        return sigCar == '{';
    }

    private boolean hayLlaveCierre() {
        return sigCar == '}';
    }

    private boolean hayExclamacion() {
        return sigCar == '!';
    }

    private boolean hayIgual() {
        return sigCar == '=';
    }

    private boolean hayMenorQue() {
        return sigCar == '<';
    }

    private boolean hayMayorQue() {
        return sigCar == '>';
    }

    private boolean hayPuntoYComa() {
        return sigCar == ';';
    }

    private boolean hayPunto() {
        return sigCar == '.';
    }

    private boolean hayAlmohadilla() {
        return sigCar == '#';
    }

    private boolean hayArroba() {
        return sigCar == '@';
    }

    private boolean hayAmpersand() {
        return sigCar == '&';
    }

    private boolean haySep() {
        return sigCar == ' ' || sigCar == '\t' || sigCar == '\n';
    }

    private boolean hayNL() {
        return sigCar == '\r' || sigCar == '\b' || sigCar == '\n';
    }

    private boolean hayEOF() {
        return sigCar == -1;
    }

    private UnidadLexica unidadId() {
        switch (lex.toString().toLowerCase()) {
            case "int":
                return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.INT);
            case "real":
                return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.REAL);
            case "bool":
                return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.BOOL);
            case "and":
                return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.AND);
            case "or":
                return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OR);
            case "not":
                return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.NOT);
            case "true":
                return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.TRUE);
            case "false":
                return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FALSE);
            default:
                return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.IDEN, lex.toString());
        }
    }

    private UnidadLexica unidadEnt() {
        return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.ENT, lex.toString());
    }

    private UnidadLexica unidadReal() {
        return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.NUM_REAL, lex.toString());
    }

    private UnidadLexica unidadMas() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAS);
    }

    private UnidadLexica unidadMenos() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOS);
    }

    private UnidadLexica unidadPor() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.POR);
    }

    private UnidadLexica unidadDiv() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DIV);
    }

    private UnidadLexica unidadPAp() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PAR_APERTURA);
    }


    private UnidadLexica unidadPCierre() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PAR_CIERRE);
    }

    private UnidadLexica unidadLlaveApertura() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.LLAVE_APERTURA);
    }
    private UnidadLexica unidadLlaveCierre() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.LLAVE_CIERRE);
    }

    private UnidadLexica unidadIgual() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.IGUAL);
    }
    private UnidadLexica unidadMayorQue() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYORQUE);
    }
    private UnidadLexica unidadMenorQue() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENORQUE);
    }
    private UnidadLexica unidadMayorIgual() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYORIGUAL);
    }
    private UnidadLexica unidadMenorIgual() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENORIGUAL);
    }
    private UnidadLexica unidadIgualComparacion() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.IGUAL_COMP);
    }
    private UnidadLexica unidadNoIgual() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.NOIGUAL_COMP);
    }

    private UnidadLexica unidadPuntoYComa() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PUNTOYCOMA);
    }
    private UnidadLexica unidadArroba() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.ARROBA);
    }
    private UnidadLexica unidadAmpersand() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DOBLEAMPERSAND);
    }



    private UnidadLexica unidadEof() {
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.EOF);
    }

    private void error() {
        int curCar = sigCar;
        try {
            sigCar();
        } catch (IOException ignored) {}
        throw new ECaracterInesperado("(" + filaActual + ',' + columnaActual + "):Caracter inesperado"+(char)curCar);
    }

    public static void main(String[] arg) throws IOException {
        Reader input = new InputStreamReader(new FileInputStream(arg[0]));
        AnalizadorLexicoTiny0 al = new AnalizadorLexicoTiny0(input);
        UnidadLexica unidad;
        do {
            unidad = al.sigToken();
            System.out.println(unidad);
        }
        while (unidad.clase() != ClaseLexica.EOF);
    }
}
