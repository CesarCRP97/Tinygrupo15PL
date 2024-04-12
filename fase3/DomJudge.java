import c_ast_ascendente.*;
import c_ast_descendente.*;
import asint.SintaxisAbstractaTiny.*;
import procesamientos.*;
import java.io.Reader;
import java.io.InputStreamReader;

public class DomJudge {
        public static void main(String[] args) throws Exception {

        char tipo;
        Prog prog = null;

        tipo = (char) System.in.read();
        
        //Inicializamos el arbol de sintaxis abstracta que vayamos a usar
        if(tipo == 'a') {

            System.out.println("CONSTRUCCION AST ASCENDENTE");

            Reader input = new InputStreamReader(System.in);
            AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
            c_ast_ascendente.ConstructorASTTinyDJ asint_asc = new c_ast_ascendente.ConstructorASTTinyDJ(alex);
            try {
                prog = (Prog)asint_asc.debug_parse().value;
            } catch (Exception E) {
                System.out.println("ERROR_SINTACTICO");
                System.exit(0);
            } catch (Error E) {
                System.out.println("ERROR_LEXICO");
                System.exit(0);
            }
        } else if (tipo == 'd') {

            System.out.println("CONSTRUCCION AST DESCENDENTE");

            c_ast_descendente.ConstructorASTsTinyDJ asint_desc = new c_ast_descendente.ConstructorASTsTinyDJ(System.in);
            try {
                prog = asint_desc.analiza();
            } catch (Exception|Error e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        }         
        System.out.println("IMPRESION RECURSIVA");
        System.out.println(new Procesamiento_rec().imprime(prog));
        System.out.println("IMPRESION INTERPRETE");
        prog.imprime();
        System.out.println("IMPRESION VISITANTE");
        prog.procesa(new Procesamiento_vis());
    }
}
