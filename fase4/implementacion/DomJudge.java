import c_ast_ascendente.*;
import c_ast_descendente.*;
import asint.SintaxisAbstractaTiny.*;
import procesamientos.*;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import maquinap.MaquinaP;

public class DomJudge {

    public static void main(String[] args) throws Exception {
        char tipo = (char) System.in.read();
        Reader input = new BISReader(System.in);
        Prog prog = construyeAST(input, tipo);
        if(prog != null) {
            procesa(prog, input);
        }

    }

    private static Prog construyeAST(Reader input, char tipo) throws Exception {
        Prog prog = null;
        if(tipo == 'a') {
            AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
            c_ast_ascendente.ConstructorASTTiny asint_asc = new c_ast_ascendente.ConstructorASTTiny(alex);
            try {
                prog = (Prog)asint_asc.parse().value;
            } catch (Exception e) {
                System.out.println("ERROR_SINTACTICO");
                System.exit(0);
            } catch (Error e) {
                System.out.println("ERROR_LEXICO");
                System.exit(0);
            }
        } else if (tipo == 'd') {
            c_ast_descendente.ConstructorASTsTiny asint_desc = new c_ast_descendente.ConstructorASTsTiny(input);
            asint_desc.disable_tracing();
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
        return prog;
    }

    private static void procesa(Prog prog, Reader datos) throws Exception {
        Vinculacion_vis vinc = new Vinculacion_vis();
        prog.procesa(vinc);
        if(vinc.hayErrores()) {
            return;
        }

        ComprobacionTipos_vis comp = new ComprobacionTipos_vis();
        prog.procesa(comp);
        if(comp.hayErrores()) {
            return;
        }

        AsignacionEspacio_vis asig = new AsignacionEspacio_vis();
        prog.procesa(asig);

        Etiquetado_vis etiq = new Etiquetado_vis();
        prog.procesa(etiq);

        MaquinaP maq = new MaquinaP(datos, 500, 5000, 5000, 10);
        GeneracionCod_vis gen = new GeneracionCod_vis(maq);
        prog.procesa(gen); 

        maq.ejecuta();
    }



    static class BISReader extends InputStreamReader {

        public BISReader(InputStream in) {
            super(in);
        }

        @Override
        public int read(char[] cbuf, int offset, int length) throws IOException {
            int c = read();
            if (c == -1) return -1;
            else {
                cbuf[offset] = (char) c;
                return 1;
            }
        }
    }
}


