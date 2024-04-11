import asint.SintaxisAbstractaTiny.Prog;
import c_ast_ascendente.AnalizadorLexicoTiny;
import c_ast_descendente.ConstructorASTsTiny;
import procesamientos.Procesamiento_rec;
import procesamientos.Procesamiento_vis;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length != 3) {
            System.err.println("Uso: java -cp java-cup-11b-29160615-3.jar:. procesamientos.Main <fichero> <rec|inter|vis> <asc|desc>");
            System.exit(1);
        }
        String file = args[0];
        String proc = args[1];
        String tipo = args[2];

        //Inicializamos el arbol de sintaxis abstracta que vayamos a usar
        Prog prog_asc = null, prog_desc = null;
        if(tipo.equals("asc")) {
            Reader input = new InputStreamReader(new FileInputStream(file));
            AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
            c_ast_ascendente.ConstructorASTTiny asint_asc = new c_ast_ascendente.ConstructorASTTiny(alex);
            try {
                prog_asc = (Prog)asint_asc.parse().value;
            } catch (Exception|Error e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        } else if (tipo.equals("desc")) {
            c_ast_descendente.ConstructorASTsTiny asint_desc = new c_ast_descendente.ConstructorASTsTiny(new FileReader(file));
            asint_desc.disable_tracing();
            try {
                prog_desc = asint_desc.analiza();
            } catch (Exception|Error e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        } else {
            System.err.println("Uso: java -cp java-cup-11b-29160615-3.jar:. procesamientos.Main <fichero> <rec|inter|vis> <asc|desc>");
            System.exit(1);
        }

        if(proc.equals("rec")) {
            if(tipo.equals("asc")) {
                System.out.println(new Procesamiento_rec().imprime(prog_asc));

            }
            else if (tipo.equals("desc")) {
                System.out.println(new Procesamiento_rec().imprime(prog_desc));
            }
        } else if(proc.equals("inter")) {
            if(tipo.equals("asc")) {
                prog_asc.imprime();
            }
            else if(tipo.equals("desc")) {
                prog_desc.imprime();
            }
        } else if (proc.equals("vis")) {
            if(tipo.equals("asc")) {
                prog_asc.procesa(new Procesamiento_vis());
            }
            else if(tipo.equals("desc")) {
                prog_desc.procesa(new Procesamiento_vis());
            }
        } else {
            System.err.println("Uso: java -cp java-cup-11b-29160615-3.jar:. procesamientos.Main <fichero> <rec|inter|vis> <asc|desc>");
            System.exit(1);
        }
    }
}   
