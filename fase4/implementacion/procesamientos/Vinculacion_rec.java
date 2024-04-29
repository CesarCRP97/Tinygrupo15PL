package procesamientos;

import asint.SintaxisAbstractaTiny;
import java.util.HashMap;
import java.util.Map;

public class Vinculacion_rec extends SintaxisAbstractaTiny {

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

    public Procesamiento_rec() {
        error = false;
    }

    public void vincula(Prog p) {
        ts = creaTS();
        abreAmbito(ts);
        vincula(p.bloque());
        cierraAmbito(ts);
    }

    public void vincula(Bloque b) {
        vincula1(b.decs());
        vincula2(b.decs());
        vincula(b.ins());
    }

    public void vincula1(Si_decs decs) {
        vincula1(decs.ldecs());
    }

    public void vincula1(No_decs decs) {
    }
    public void vincula1(Muchas_decs decs) {
        vincula1(decs.ldecs());
        vincula1(decs.dec());
    }
    public void vincula1(Una_dec dec) {
        vincula1(dec.dec());
    }
    public void vincula1(Dec_variable dec) {
        vincula1(dec.tipo());
        inserta(ts, dec.iden(), dec);
    }
    public void vincula1(Dec_tipo dec) {
        vincula1(dec.tipo());
        inserta(ts, dec.iden(), dec);
    }
    public void vincula1(Dec_proc dec) {
        inserta(ts, dec.iden(), dec);
        abreAmbito(ts);
        vincula1(dec.params_form());
        vincula2(dec.params_form());
        vincula(dec.bloque());
        cierraAmbito(ts);
    }
    public void vincula1(Si_params_form params) {
        vincula1(params.lparams_form());
    }
    public void vincula1(No_params_form params) {
    }
    public void vincula1(Muchos_params_form params) {
        vincula1(params.lparams_form());
        vincula1(params.param_form());
    }
    public void vincula1(Un_param_form params) {
        vincula1(params.param_form());
    }
    public void vincula2(Si_params_form params) {
        vincula2(params.lparams_form());
    }
    public void vincula2(No_params_form params) {
    }
    public void vincula2(Muchos_params_form params) {
        vincula2(params.lparams_form());
        vincula2(params.param_form());
    }
    public void vincula2(Un_param_form params) {
        vincula1(params.param_form());
    }
    public void vincula1(Param_form_normal param) {
        vincula1(param.tipo());
        inserta(ts, param.iden(), param);
    }
    public void vincula1(Param_form_ref param) {
        vincula1(param.tipo());
        inserta(ts, param.iden(), param);
    }
    public void vincula2(Param_form_normal param) {
        vincula2(param.tipo());
    }
    public void vincula2(Param_form_ref param) {
        vincula2(param.tipo());
    }
    public void vincula1(Tipo_int tipo) {}
    public void vincula1(Tipo_bool tipo) {}
    public void vincula1(Tipo_real tipo) {}
    public void vincula1(Tipo_array tipo) {
        if (!(tipo.tipo() instanceof Dec_tipo)) {
            vincula1(tipo.tipo());
        }
    }
    public void vincula1(Tipo_string tipo) {}
    public void vincula1(Tipo_puntero tipo) {
        if (!(tipo.tipo() instanceof Dec_tipo)) {
            vincula1(tipo.tipo());
        }
    }
    public void vincula1(Tipo_struct tipo) {}
    public void vincula1(Tipo_iden tipo) {
        if (infoVinculo(ts, tipo.iden()) == null) {
            errorNoDeclarado(tipo.iden(), tipo.leeFila(), tipo.leeCol());
        } else {
            tipo.ponVinculo(infoVinculo(ts, tipo.iden()));
        }
    }

    public void vincula2(Si_decs decs) {
        vincula2(decs.ldecs());
    }
    public void vincula2(No_decs decs) {
    }
    public void vincula2(Muchas_decs decs) {
        vincula2(decs.ldecs());
        vincula2(decs.dec());
    }
    public void vincula2(Una_dec dec) {
        vincula2(dec.dec());
    }
    public void vincula2(Dec_variable dec) {
        vincula2(dec.tipo());
    }
    public void vincula2(Dec_tipo dec) {
        vincula2(dec.tipo());
    }
    public void vincula2(Dec_proc dec) {}

    public void vincula2(Si_params_form params) {
        vincula2(params.lparams_form());
    }
    public void vincula2(No_params_form params) {}
    public void vincula2(Muchos_params_form params) {
        vincula2(params.lparams_form());
        vincula2(params.param_form());
    }
    public void vincula2(Un_param_form params) {
        vincula2(params.param_form());
    }
    public void vincula2(Param_form_normal param) {
        vincula2(param.tipo());
    }
    public void vincula2(Param_form_ref param) {
        vincula2(param.tipo());
    }
    public void vincula2(Tipo_int tipo) {}
    public void vincula2(Tipo_bool tipo) {}
    public void vincula2(Tipo_real tipo) {}
    public void vincula2(Tipo_array tipo) {
        if (tipo.tipo() instanceof Dec_tipo) {
            if( infoVinculo(ts, tipo.tipo().iden()) == null) {
                errorNoDeclarado(tipo.tipo().iden(), tipo.tipo().leeFila(), tipo.tipo().leeCol());
            } else {
                tipo.tipo().ponVinculo(infoVinculo(ts, tipo.tipo().iden()));
            }
        }
    }
    public void vincula2(Tipo_string tipo) {}
    public void vincula2(Tipo_puntero tipo) {
        if (tipo.tipo() instanceof Dec_tipo) {
            if( infoVinculo(ts, tipo.tipo().iden()) == null) {
                errorNoDeclarado(tipo.tipo().iden(), tipo.tipo().leeFila(), tipo.tipo().leeCol());
            } else {
                tipo.tipo().ponVinculo(infoVinculo(ts, tipo.tipo().iden()));
            }
        }
    }


    public void vincula(Si_instrs instrs) {
        vincula(instrs.linstrs());
    }
    public void vincula(No_instrs instrs) {
    }
    public void vincula(Muchas_instrs instrs) {
        vincula(instrs.linstrs());
        vincula(instrs.instr());
    }
    public void vincula(Una_instr instr) {
        vincula(instr.instr());
    }
    public void vincula(Eval instr) {
        vincula(instr.exp());
    }
    public void vincula(If instr) {
        vincula(instr.exp());
        abreAmbito(ts);
        vincula(instr.bloque());
        cierraAmbito(ts);
    }
    public void vincula(IfElse instr) {
        vincula(instr.exp());
        abreAmbito(ts);
        vincula(instr.bloque1());
        cierraAmbito(ts);
        abreAmbito(ts);
        vincula(instr.bloque2());
        cierraAmbito(ts);
    }
    public void vincula(While instr) {
        vincula(instr.exp());
        abreAmbito(ts);
        vincula(instr.bloque());
        cierraAmbito(ts);
    }
    public void vincula(Read instr) {
        vincula(instr.exp());
    }
    public void vincula(Write instr) {
        vincula(instr.exp());
    }
    public void vincula(NL instr) {
    }
    public void vincula(New instr) {
        vincula(instr.exp());
    }
    public void vincula(Delete instr) {
        vincula(instr.exp());
    }
    public void vincula(Instr_compuesta instr) {
        abreAmbito(ts);
        vincula(instr.bloque());
        cierraAmbito(ts);
    }
    public void vincula(Invoc instr) {
        if (infoVinculo(ts, instr.iden()) == null) {
            errorNoDeclarado(instr.iden(), instr.leeFila(), instr.leeCol());
        } else {
            instr.ponVinculo(infoVinculo(ts, instr.iden()));
        }
        vincula(instr.params_reales());
    }
    public void vincula(Si_params_reales params) {
        vincula(params.lparams_reales());
    }
    public void vincula(No_params_reales params) {
    }
    public void vincula(Muchos_params_reales params) {
        vincula(params.lparams_reales());
        vincula(params.exp());
    }
    public void vincula(Un_param_real params) {
        vincula(params.exp());
    }
    public void vincula(Asignacion exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Igual_comp exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Distinto_comp exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Menor_que exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Mayor_que exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Menor_igual exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Mayor_igual exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Suma exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Resta exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(And exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Or exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Mul exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Div exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Mod exp) {
        vincula(exp.opnd0());
        vincula(exp.opnd1());
    }
    public void vincula(Menos_unario exp) {
        vincula(exp.opnd());
    }
    public void vincula(Not exp) {
        vincula(exp.opnd());
    }
    //TODO
    public void vincula(Indexacion exp) {
        vincula(exp.opnd0());
        if (exp.opnd0().
        vincula(exp.opnd1());
    }
    public void vincula(Acceso exp) {
        vincula(exp.opnd());
        
    }
    public void vincula(Indireccion exp) {
        vincula(exp.opnd());
    }
    public void vincula(Lit_ent exp) {
    }
    public void vincula(Lit_real exp) {
    }
    public void vincula(True exp) {
    }
    public void vincula(False exp) {
    }
    public void vincula(Lit_cadena exp) {
    }
    public void vincula(Iden exp) {
        if (infoVinculo(ts, exp.iden()) == null) {
            errorNoDeclarado(exp.iden(), exp.leeFila(), exp.leeCol());
        } else {
            exp.ponVinculo(infoVinculo(ts, exp.iden()));
        }
    }
    public void vincula(Null exp) {
    }
    public void vincula(String str) {
    }

}

