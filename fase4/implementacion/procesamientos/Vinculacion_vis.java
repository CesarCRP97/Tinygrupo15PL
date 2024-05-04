package procesamientos;

import asint.SintaxisAbstractaTiny;
import asint.SintaxisAbstractaTiny.*;
import asint.ProcesamientoDef;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Vinculacion_vis extends ProcesamientoDef {

    public  class ErrorVinculacion extends RuntimeException {
        public ErrorVinculacion(String msg) {
            super(msg + "\n" + ts.toString());
        }
    }

    private void errorDuplicado(String iden, int fila, int col) {
        //throw new ErrorVinculacion("Identificador duplicado: " + iden + " línea " + fila + " fila " + col);
        System.out.println("Identificador duplicado: " + iden + " línea " + fila + " fila " + col);
        error = true;
        
    }

    private void errorNoDeclarado(String iden, int fila, int col) {
        //throw new ErrorVinculacion("Identificador no declarado: " + iden + " línea " + fila + " fila " + col);
        System.out.println("Identificador no declarado: " + iden + " línea " + fila + " fila " + col);
        error = true;
    }

    private class TablaSimbolos {
        private class Ambito {
            private HashMap<String, Nodo> tabla;
            private Ambito padre;

            public Ambito(Ambito padre) {
                tabla = new HashMap<String, Nodo>();
                this.padre = padre;
            }

            public boolean contiene(String iden) {
                return tabla.containsKey(iden);
            }

            public void put(String iden, Nodo dec) {
                tabla.put(iden, dec);
            }

            public Ambito padre() {
                return padre;
            }

            public Nodo get(String iden) {
                if (tabla.containsKey(iden)) {
                    return tabla.get(iden);
                } else {
                    if (padre != null) {
                        return padre.get(iden);
                    } else {
                        return null;
                    }
                }
            }

            public String toString() {
                String res = "";
                for (String iden : tabla.keySet()) {
                    res += iden + " ";
                }
                return res;
            }
        }

        private ArrayList<Ambito> pila;
        private Ambito actual;

        public TablaSimbolos() {
            pila = new ArrayList<Ambito>();
        }

        public void abrirAmbito() {
            if (actual == null) {
                actual = new Ambito(null);
            } else {
                actual = new Ambito(actual);
            }
            pila.add(actual);
        }

        public void cerrarAmbito() {
            pila.remove(actual);
            actual = actual.padre();
        }

        public boolean contiene(String iden) {
            return actual.contiene(iden);
        }

        public void put(String iden, Nodo dec) {
            actual.put(iden, dec);
        }

        public Nodo infoVinculo(String iden) {
            return actual.get(iden);
        }

        public String toString() {
            String res = "";
            for (int i = 0; i < pila.size(); i++) {
                System.out.print("Ambito " + i + ": " + pila.get(i));
            }
            return res;
        }
    }

    private TablaSimbolos ts;
    private boolean error;

    public boolean hayErrores() {
        return error;
    }

    private TablaSimbolos creaTS() {
        return new TablaSimbolos();
    }

    private boolean contiene(TablaSimbolos ts, String iden) {
        return ts.contiene(iden);
    }

    private void inserta(TablaSimbolos ts, String iden, Nodo dec) {
        if (contiene(ts, iden)) {
            errorDuplicado(iden, dec.leeFila(), dec.leeCol());
        } else {
            ts.put(iden, dec);
        }
    }

    private Nodo infoVinculo(TablaSimbolos ts, String iden) {
        return ts.infoVinculo(iden);
    }

    private void abreAmbito(TablaSimbolos ts) {
        ts.abrirAmbito();
    }

    private void cierraAmbito(TablaSimbolos ts) {
        ts.cerrarAmbito();
    }

    public Vinculacion_vis() {
        error = false;
    }

    public void procesa(Prog p) {
        ts = creaTS();
        abreAmbito(ts);
        p.bloque().procesa(this);
        cierraAmbito(ts);
    }

    public void procesa(Bloque b) {
        b.decs().procesa1(this);
        b.decs().procesa2(this);
        b.insts().procesa(this);
    }

    public void procesa1(Si_decs decs) {
        decs.ldecs().procesa1(this);
    }

    public void procesa1(No_decs decs) {
    }
    public void procesa1(Muchas_decs decs) {
        decs.ldecs().procesa1(this);
        decs.dec().procesa1(this);
    }
    public void procesa1(Una_dec dec) {
        dec.dec().procesa1(this);
    }
    public void procesa1(Dec_variable dec) {
        dec.tipo().procesa1(this);
        inserta(ts, dec.iden(), dec);
    }
    public void procesa1(Dec_tipo dec) {
        dec.tipo().procesa1(this);
        inserta(ts, dec.iden(), dec);
    }
    public void procesa1(Dec_proc dec) {
        inserta(ts, dec.iden(), dec);
        abreAmbito(ts);
        dec.params_form().procesa1(this);
        dec.params_form().procesa2(this);
        dec.bloque().procesa(this);
        cierraAmbito(ts);
    }
    public void procesa1(Si_params_form params) {
        params.lparams_form().procesa1(this);
    }
    public void procesa1(No_params_form params) {
    }
    public void procesa1(Muchos_params_form params) {
        params.lparams_form().procesa1(this);
        params.param_form().procesa1(this);
    }
    public void procesa1(Un_param_form params) {
        params.param_form().procesa1(this);
    }
    public void procesa2(Si_params_form params) {
        params.lparams_form().procesa2(this);
    }
    public void procesa2(No_params_form params) {
    }
    public void procesa2(Muchos_params_form params) {
        params.lparams_form().procesa2(this);
        params.param_form().procesa2(this);
    }
    public void procesa2(Un_param_form params) {
        params.param_form().procesa2(this);
    }
    public void procesa1(Param_form_normal param) {
        param.tipo().procesa1(this);
        inserta(ts, param.iden(), param);
    }
    public void procesa1(Param_form_ref param) {
        param.tipo().procesa1(this);
        inserta(ts, param.iden(), param);
    }
    public void procesa2(Param_form_normal param) {
        param.tipo().procesa2(this);
    }
    public void procesa2(Param_form_ref param) {
        param.tipo().procesa2(this);
    }
    public void procesa1(Tipo_int tipo) {}
    public void procesa1(Tipo_bool tipo) {}
    public void procesa1(Tipo_real tipo) {}
    public void procesa1(Tipo_array tipo) {
        if (!(tipo.tipo() instanceof Tipo_iden)) {
            tipo.tipo().procesa1(this);
        }
    }
    public void procesa1(Tipo_string tipo) {}
    public void procesa1(Tipo_puntero tipo) {
        if (!(tipo.tipo() instanceof Tipo_iden)) {
            tipo.tipo().procesa1(this);
        }
    }
    public void procesa1(Tipo_struct tipo) {
        tipo.campos().procesa1(this);
    }
    public void procesa1(Campos campos) {
        campos.lcampos().procesa1(this);
    }
    public void procesa1(Muchos_campos campos) {
        campos.lcampos().procesa1(this);
        campos.campo().procesa1(this);
    }
    public void procesa1(Un_campo campo) {
        campo.campo().procesa1(this);
    }
    public void procesa1(Campo campo) {
        campo.tipo().procesa1(this);
    }

    public void procesa1(Tipo_iden tipo) {
        if (infoVinculo(ts, tipo.iden()) == null) {
            errorNoDeclarado(tipo.iden(), tipo.leeFila(), tipo.leeCol());
        } else {
            tipo.ponVinculo(infoVinculo(ts, tipo.iden()));
            //System.out.println("Vinculado: " + tipo.toString());
        }
    }

    public void procesa2(Si_decs decs) {
        decs.ldecs().procesa2(this);
    }
    public void procesa2(No_decs decs) {
    }
    public void procesa2(Muchas_decs decs) {
        decs.ldecs().procesa2(this);
        decs.dec().procesa2(this);
    }
    public void procesa2(Una_dec dec) {
        dec.dec().procesa2(this);
    }
    public void procesa2(Dec_variable dec) {
        dec.tipo().procesa2(this);
    }
    public void procesa2(Dec_tipo dec) {
        dec.tipo().procesa2(this);
    }
    public void procesa2(Dec_proc dec) {}

        public void procesa2(Tipo_int tipo) {}
    public void procesa2(Tipo_bool tipo) {}
    public void procesa2(Tipo_real tipo) {}
    public void procesa2(Tipo_array tipo) {
        if (tipo.tipo() instanceof Tipo_iden) {
            if( infoVinculo(ts, tipo.tipo().iden()) == null) {
                errorNoDeclarado(tipo.tipo().iden(), tipo.tipo().leeFila(), tipo.tipo().leeCol());
            } else {
                ((Tipo_iden)tipo.tipo()).ponVinculo(infoVinculo(ts, tipo.tipo().iden()));
                //System.out.println("Vinculado: " + tipo.toString());
            }
        }
    }
    public void procesa2(Tipo_string tipo) {}
    public void procesa2(Tipo_puntero tipo) {
        if (tipo.tipo() instanceof Tipo_iden) {
            if( infoVinculo(ts, tipo.tipo().iden()) == null) {
                errorNoDeclarado(tipo.tipo().iden(), tipo.tipo().leeFila(), tipo.tipo().leeCol());
            } else {
                ((Tipo_iden)tipo.tipo()).ponVinculo(infoVinculo(ts, tipo.tipo().iden()));
                //System.out.println("Vinculado: " + tipo.toString());
            }
        }
    }


    public void procesa(Si_instrs instrs) {
        instrs.linstrs().procesa(this);
    }
    public void procesa(No_instrs instrs) {
    }
    public void procesa(Muchas_instrs instrs) {
        instrs.linstrs().procesa(this);
        instrs.instr().procesa(this);
    }
    public void procesa(Una_instr instr) {
        instr.instr().procesa(this);
    }
    public void procesa(Eval instr) {
        instr.exp().procesa(this);
    }
    public void procesa(If instr) {
        instr.exp().procesa(this);
        abreAmbito(ts);
        instr.bloque().procesa(this);
        cierraAmbito(ts);
    }
    public void procesa(IfElse instr) {
        instr.exp().procesa(this);
        abreAmbito(ts);
        instr.bloque1().procesa(this);
        cierraAmbito(ts);
        abreAmbito(ts);
        instr.bloque2().procesa(this);
        cierraAmbito(ts);
    }
    public void procesa(While instr) {
        instr.exp().procesa(this);
        abreAmbito(ts);
        instr.bloque().procesa(this);
        cierraAmbito(ts);
    }
    public void procesa(Read instr) {
        instr.exp().procesa(this);
    }
    public void procesa(Write instr) {
        instr.exp().procesa(this);
    }
    public void procesa(NL instr) {
    }
    public void procesa(New instr) {
        instr.exp().procesa(this);
    }
    public void procesa(Delete instr) {
        instr.exp().procesa(this);
    }
    public void procesa(Instr_compuesta instr) {
        abreAmbito(ts);
        instr.bloque().procesa(this);
        cierraAmbito(ts);
    }
    public void procesa(Invoc instr) {
        if (infoVinculo(ts, instr.iden()) == null) {
            errorNoDeclarado(instr.iden(), instr.leeFila(), instr.leeCol());
        } else {
            instr.ponVinculo(infoVinculo(ts, instr.iden()));
            //System.out.println("Vinculado: " + instr.toString());
        }
        instr.params_reales().procesa(this);
    }
    public void procesa(Si_params_reales params) {
        params.lparams_reales().procesa(this);
    }
    public void procesa(No_params_reales params) {
    }
    public void procesa(Muchos_params_reales params) {
        params.lparams_reales().procesa(this);
        params.exp().procesa(this);
    }
    public void procesa(Un_param_real params) {
        params.exp().procesa(this);
    }
    public void procesa(Asignacion exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Igual_comp exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Distinto_comp exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Menor_que exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Mayor_que exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Menor_igual exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Mayor_igual exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Suma exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Resta exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(And exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Or exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Mul exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Div exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Mod exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Menos_unario exp) {
        exp.opnd().procesa(this);
    }
    public void procesa(Not exp) {
        exp.opnd().procesa(this);
    }
    public void procesa(Indexacion exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
    }
    public void procesa(Acceso exp) {
        exp.opnd().procesa(this);
    }
    public void procesa(Indireccion exp) {
        exp.opnd().procesa(this);
    }
    public void procesa(Lit_ent exp) {
    }
    public void procesa(Lit_real exp) {
    }
    public void procesa(True exp) {
    }
    public void procesa(False exp) {
    }
    public void procesa(Lit_cadena exp) {
    }
    public void procesa(Iden exp) {
        if (infoVinculo(ts, exp.iden()) == null) {
            errorNoDeclarado(exp.iden(), exp.leeFila(), exp.leeCol());
        } else {
            exp.ponVinculo(infoVinculo(ts, exp.iden()));
            //System.out.println("Vinculado: " + exp.toString());
        }
    }
    public void procesa(Null exp) {
    }
    public void procesa(String str) {
    }

}

