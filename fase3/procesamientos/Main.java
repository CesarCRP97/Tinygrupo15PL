package procesamientos;

import asint.SintaxisAbstractaTiny.Prog;
import c_ast_ascendente.AnalizadorLexicoTiny;
import c_ast_descendente.ConstructorASTsTiny;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            System.err.println("Uso: java -jar Procesamientos.jar <fichero> <rec|inter|vis> <asc|desc>");
            System.exit(1);
        }
        String file = args[0];
        String proc = args[1];
        String tipo = args[2];

        //Inicializamos el arbol de sintaxis abstracta que vayamos a usar
        if(tipo.equals("asc") {
            Reader input = new InputStreamReader(new FileInputStream(file));
            AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
            c_ast_ascendente.ConstructorASTTiny asint_asc = new c_ast_ascendente.ConstructorASTTiny(alex);
            Prog prog_asc = (Prog)asint_asc.parse().value;
        } else if (tipo.equals("desc")) {
            c_ast_descendente.ConstructorASTsTiny asint_desc = new c_ast_descendente.ConstructorASTsTiny(new FileReader(file));
            asint_desc.disable_tracing();
            Prog prog_desc = asint_desc.analiza();
        } else {
            System.err.println("Uso: java -jar Procesamientos.jar <fichero> <rec|inter|vis> <asc|desc>");
            System.exit(1);
        }

        if(proc.equals("rec")) {
            if(tipo.equals("asc")) {
                System.out.println(new Procesamiento_rec().imprime(prog_asc));
            }
            else if (tipo.equals("desc")) {
                System.out.println(new Procesamiento_rec().imprime(prog_desc));
            }
        } else if(proc.equals("inter") {
            if(tipo.equals("asc")) {
                prog_asc.imprime();
            }
            else if(tipo.equals("desc")) {
                prog_desc.imprime();
            }
        }
    }
}   
