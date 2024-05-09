import c_ast_ascendente.*;
import c_ast_descendente.*;
import asint.SintaxisAbstractaTiny.*;
import procesamientos.*;
import java.io.Reader;
import java.io.InputStreamReader;
import maquinap.MaquinaP;

public class DomJudge {
        public static void main(String[] args) throws Exception {

        char tipo;
        Prog prog = null;

        tipo = (char) System.in.read();
        
        //Inicializamos el arbol de sintaxis abstracta que vayamos a usar
        if(tipo == 'a') {
            Reader input = new InputStreamReader(System.in);
            AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
            c_ast_ascendente.ConstructorASTTinyDJ asint_asc = new c_ast_ascendente.ConstructorASTTinyDJ(alex);
            try {
                prog = (Prog)asint_asc.debug_parse().value;
            } catch (Exception e) {
                System.out.println("ERROR_SINTACTICO");
                System.exit(0);
            } catch (Error e) {
                System.out.println("ERROR_LEXICO");
                System.exit(0);
            }
        } else if (tipo == 'd') {
            c_ast_descendente.ConstructorASTsTinyDJ asint_desc = new c_ast_descendente.ConstructorASTsTinyDJ(System.in);
            try {
                prog = asint_desc.analiza();
            } catch (Exception e) {
                System.out.println("ERROR_SINTACTICO");
                System.exit(0);
            } catch (Error e) {
                System.out.println("ERROR_LEXICO");
                System.exit(0);
            }
        }

        Vinculacion_vis vinc = new Vinculacion_vis();
        prog.procesa(vinc);
        if(vinc.hayErrores()) {
            System.err.println("Errores de vinculacion");
            return;
        } else {
            System.out.println("Vinculacion correcta");

        }

        ComprobacionTipos_vis comp = new ComprobacionTipos_vis();
        prog.procesa(comp);
        if(comp.hayErrores()) {
            System.err.println("Errores de tipos");
            return;
        } else {
            System.out.println("Tipado correcto");
        }
        
        AsignacionEspacio_vis asig = new AsignacionEspacio_vis();
        prog.procesa(asig);
        int maxtam = asig.getMaxTamNivel();
        int maxnivel = asig.getMaxNivel();

        System.out.println("Etiquetando codigo...");
        Etiquetado_vis etiq = new Etiquetado_vis();
        prog.procesa(etiq);

        System.out.println("Generando codigo...");
        MaquinaP maq = new MaquinaP(new InputStreamReader(System.in), maxtam, 2*maxtam, 2*maxtam, maxnivel);
        GeneracionCod_vis gen = new GeneracionCod_vis(maq);
        prog.procesa(gen);         

        
    }
}
