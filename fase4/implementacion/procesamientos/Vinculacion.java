package procesamientos;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Vinculacion extends ProcesamientoDef {

    public  class ErrorVinculacion extends RuntimeException {
        public ErrorVinculacion(String msg) {
            super(msg + "\n" + ts.toString());
        }
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
                res += "\n";
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

    private TablaSimbolos creaTS() {
        return new TablaSimbolos();
    }

    private boolean contiene(TablaSimbolos ts, String iden) {
        return ts.contiene(iden);
    }

    private void inserta(TablaSimbolos ts, String iden, Nodo dec) {
        ts.put(iden, dec);
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

    public void procesa(Prog prog) {
        ts = creaTS();
        abreAmbito(ts);
        prog.bloque().procesa(this);
        cierraAmbito(ts);
    }

    public void procesa(Bloque bloque) {
        bloque.decs().procesa(this);
        bloque.insts().procesa(this);
    }
    public void procesa(Si_decs decs) {
        decs.ldecs().procesa(this);
    }
    public void procesa(No_decs decs) {
    }
    public void procesa(Muchas_decs decs) {
        decs.ldecs().procesa(this);
        decs.dec().procesa(this);
    }
    public void procesa(Una_dec dec) {
        dec.dec().procesa(this);
    }
    public void procesa(Dec_variable dec) {
        if (contiene(ts, dec.iden())) {
            throw new ErrorVinculacion("Identificador duplicado: " + dec.iden() + " línea " + dec.leeFila() + " fila " + dec.leeCol());
        } else {
            inserta(ts, dec.iden(), dec);
        }
    }
    public void procesa(Dec_tipo dec) {
        if (contiene(ts, dec.iden())) {
            throw new ErrorVinculacion("Identificador duplicado: " + dec.iden() + " línea " + dec.leeFila() + " fila " + dec.leeCol());
        } else {
            inserta(ts, dec.iden(), dec);
        }
    }
    public void procesa(Dec_proc dec) {
        abreAmbito(ts);
        dec.params_form().procesa(this);
        dec.bloque().procesa(this);
        cierraAmbito(ts);
        if (contiene(ts, dec.iden())) {
            throw new ErrorVinculacion("Identificador duplicado: " + dec.iden() + " línea " + dec.leeFila() + " fila " + dec.leeCol());
        } else {
            inserta(ts, dec.iden(), dec);
        }
    }
    public void procesa(Si_params_form params) {
        params.lparams_form().procesa(this);
    }
    public void procesa(No_params_form params) {
    }
    public void procesa(Muchos_params_form params) {
        params.lparams_form().procesa(this);
        params.param_form().procesa(this);
    }
    public void procesa(Un_param_form params) {
        params.param_form().procesa(this);
    }
    public void procesa(Param_form_normal param) {
        if (contiene(ts, param.iden())) {
            throw new ErrorVinculacion("Identificador duplicado: " + param.iden() + " línea " + param.leeFila() + " fila " + param.leeCol());
        } else {
            inserta(ts, param.iden(), param);
        }
    }
    public void procesa(Param_form_ref param) {
        if (contiene(ts, param.iden())) {
            throw new ErrorVinculacion("Identificador duplicado: " + param.iden() + " línea " + param.leeFila() + " fila " + param.leeCol());
        } else {
            inserta(ts, param.iden(), param);
        }
    }
    public void procesa(Tipo_int tipo) {
    }
    public void procesa(Tipo_bool tipo) {
    }
    public void procesa(Tipo_real tipo) {
    }
    public void procesa(Tipo_array tipo) {
    }
    public void procesa(Tipo_string tipo) {
    }
    public void procesa(Tipo_puntero tipo) {
    }
    public void procesa(Tipo_struct tipo) {
        tipo.campos().procesa(this);
    }
    public void procesa(Tipo_iden tipo) {
        if (infoVinculo(ts, tipo.iden()) == null) {
            throw new ErrorVinculacion("Identificador no declarado: " + tipo.iden() + " línea " + tipo.leeFila() + " fila " + tipo.leeCol());
        }
    }
    public void procesa(Campos campos) {
        campos.lcampos().procesa(this);
    }
    public void procesa(Muchos_campos campos) {
        campos.lcampos().procesa(this);
        campos.campo().procesa(this);
    }
    public void procesa(Un_campo campo) {
        campo.campo().procesa(this);
    }
    public void procesa(Campo campo) {
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
            throw new ErrorVinculacion("Identificador no declarado: " + exp.iden() + " línea " + exp.leeFila() + " fila " + exp.leeCol());
        }
    }
    public void procesa(Null exp) {
    }
    public void procesa(String str) {
    }

}
