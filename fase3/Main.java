import asint.SintaxisAbstractaTiny.Prog;
import c_ast_ascendente.AnalizadorLexicoTiny;
import c_ast_descendente.ConstructorASTsTinyDJ;
import procesamientos.Procesamiento_rec;
import procesamientos.Procesamiento_vis;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
    public static void launch(String[] args) throws Exception {

        char tipo;
        Prog prog = null;

        tipo = (char) System.in.read();
        
        //Inicializamos el arbol de sintaxis abstracta que vayamos a usar
        if(tipo == 'a') {

            System.out.prinln("CONSTRUCCION AST ASCENDENTE");

            Reader input = new InputStreamReader(System.in);
            AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
            c_ast_ascendente.ConstructorASTTiny asint_asc = new c_ast_ascendente.ConstructorASTTiny(alex);
            try {
                prog = (Prog)asint_asc.debug_parse().value;
            } catch (Exception|Error e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        } else if (tipo == 'd') {
            c_ast_descendente.ConstructorASTsTiny asint_desc = new c_ast_descendente.ConstructorASTsTiny(System.in);
            asint_desc.disable_tracing();
            try {
                prog = asint_desc.analiza();
            } catch (Exception|Error e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        } else {
            prog = null;
        }
        
        System.out.println("IMPRESION RECURSIVA");
        System.out.println(new Procesamiento_rec().imprime(prog));
        System.out.println("IMPRESION INTERPRETE");
        prog.imprime();
        System.out.println("IMPRESION VISITANTE");
        prog.procesa(new Procesamiento_vis());
    }


    public static void main(String[] args) throws Exception {
        if(args.length != 3) {
            System.err.println("Uso: java -cp java-cup-11b-29160615-3.jar:. procesamientos.Main <fichero> <rec|inter|vis> <asc|desc>");
            System.exit(1);
        }
        String file = args[0];
        String proc = args[1];
        String tipo = args[2];

        //Inicializamos el arbol de sintaxis abstracta que vayamos a usar
        Prog prog = null;
        if(tipo.equals("asc")) {
            Reader input = new InputStreamReader(new FileInputStream(file));
            AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
            c_ast_ascendente.ConstructorASTTinyDJ asint_asc = new c_ast_ascendente.ConstructorASTTinyDJ(alex);
            try {
                prog = (Prog)asint_asc.debug_parse().value;
            } catch (Exception|Error e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        } else if (tipo.equals("desc")) {
            c_ast_descendente.ConstructorASTsTinyDJ asint_desc = new c_ast_descendente.ConstructorASTsTinyDJ(new FileReader(file));
            try {
                prog = asint_desc.analiza();
            } catch (Exception|Error e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        } else {
            System.err.println("Uso: java -cp java-cup-11b-29160615-3.jar:. procesamientos.Main <fichero> <rec|inter|vis> <asc|desc>");
            System.exit(1);
        }

        if(proc.equals("rec")) {
            //System.out.println(new Procesamiento_rec().imprime(prog));
        } else if(proc.equals("inter")) {
            //prog.imprime();
        } else if (proc.equals("vis")) {
            //prog.procesa(new Procesamiento_vis());
        } else {
            System.err.println("Uso: java -cp java-cup-11b-29160615-3.jar:. procesamientos.Main <fichero> <rec|inter|vis> <asc|desc>");
            System.exit(1);
        }
    }
}   
