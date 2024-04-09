package procesamientos;

import asint.SintaxisAbstractaTiny;
import java.util.HashMap;
import java.util.Map;

public class Procesamiento_rec extends SintaxisAbstractaTiny {
    public class ECteNoDefinida extends RuntimeException {
        public ECteNoDefinida(String msg) {
            super(msg);
        }
    }
    public class ECteDuplicada extends RuntimeException {
        public ECteDuplicada(String msg) {
            super(msg);
        }
    }
    private Map<String,Float> env;
    public Procesamiento_rec() {
        this.env = new HashMap<>();
    }

    public String imprime(Prog p) {
        return imprime(p.bloque()) + "<EOF>\n";
    }

    public String imprime(Bloque b) {
        return "{\n" + imprime(b.decs()) + imprime(b.insts()) + "}\n";
    }

    public String imprime(Decs d) {
        String result = "";
        if(claseDe(d, Si_decs.class)) {
            result += imprime(d.ldecs());
        }
        result += "&&\n";
        return result;
    }

    public String imprime(LDecs ldecs) {
        String result = "";
        if (claseDe(ldecs, Muchas_decs.class)) {
            result += imprime(ldecs.ldecs()) + ";\n";
        }
        result += imprime(ldecs.dec());
        return result;
    }

    public String imprime(Dec dec) {
        String result = "";
        if(claseDe(dec, Dec_variable.class)) {
            result += imprime((Dec_variable)dec);
        } else if(claseDe(dec, Dec_tipo.class)) {
            result += imprime((Dec_tipo)dec);
        } else if (claseDe(dec, Dec_proc.class)) {
            result += imprime((Dec_proc)dec);
        }
        return result;
    }

    public String imprime(Dec_variable dec) {
        return imprime(dec.tipo()) + imprime(dec.iden());
    }

    public String imprime(Dec_tipo dec) {
        return "<type>\n" + imprime(dec.tipo()) + imprime(dec.iden());
    }

    public String imprime(Dec_proc dec) {
        return "<proc>\n" + imprime(dec.iden())  + imprime(dec.params_form())  + imprime(dec.bloque()) ;
    }

    public String imprime(Params_form pf) {
        String result = "(\n";
        if(claseDe(pf, Si_params_form.class)) {
            result += imprime(pf.lparams_form()) ;
        }
        result += ")\n";
        return result;
    }

    public String imprime(LParams_form lpf) {
        String result = "";
        if(claseDe(lpf, Muchos_params_form.class)) {
            result += imprime(lpf.lparams_form()) + ",\n";
        }
        result += imprime(lpf.param_form());
        return result;
    }

    public String imprime(Param_form pf) {
        return imprime(pf.tipo()) + (claseDe(pf, Param_form_ref.class) ? "&\n" : "")  + imprime(pf.iden());
    }

    public String imprime(Tipo t) {
        String result = "";
        if(claseDe(t, Tipo_int.class)) {
            result += "<int>\n";
        } else if(claseDe(t, Tipo_real.class)) {
            result += "<real>\n";
        } else if(claseDe(t, Tipo_bool.class)) {
            result += "<bool>\n";
        } else if(claseDe(t, Tipo_string.class)) {
            result += "<string>\n";
        } else if(claseDe(t, Tipo_array.class)) {
            result += "[\n" + imprime(t.num()) + "]\n" + imprime(t.tipo());
        } else if(claseDe(t, Tipo_puntero.class)) {
            result += "^\n" + imprime(t.tipo());
        } else if(claseDe(t, Tipo_struct.class)) {
            result += "<struct>\n" + imprime(t.campos())  + imprime(t.iden());
        } else if(claseDe(t, Tipo_iden.class)) {
            result += imprime(t.iden());
        }
        return result;
    }

    public String imprime(Campos c) {
        String result = "{\n";
        result += imprime(c.lcampos());
        result += "}\n";
        return result;
    }

    public String imprime(LCampos lc) {
        String result = "";
        if(claseDe(lc, Muchos_campos.class)) {
            result += imprime(lc.lcampos()) + ",\n";
        }
        result += imprime(lc.campo());
        return result;
    }
    
    public String imprime(Campo c) {
        return imprime(c.tipo())  + imprime(c.iden());
    }

    public String imprime(Instrs i) {
        String result = "";
        if(claseDe(i, Si_instrs.class)) {
            result += imprime(i.linstrs());
        }
        return result;
    }

    public String imprime(LInstrs li) {
        String result = "";
        if(claseDe(li, Muchas_instrs.class)) {
            result += imprime(li.linstrs()) + ";\n";
        }
        result += imprime(li.instr());
        return result;
    }

    public String imprime(Instr i) {
        String result = "";
        if(claseDe(i, Eval.class)) {
            result += imprime((Eval)i);
        } else if(claseDe(i, If.class)) {
            result += imprime((If)i);
        } else if(claseDe(i, IfElse.class)) {
            result += imprime((IfElse)i);
        } else if(claseDe(i, While.class)) {
            result += imprime((While)i);
        } else if(claseDe(i, Write.class)) {
            result += imprime((Write)i);
        } else if(claseDe(i, Read.class)) {
            result += imprime((Read)i);
        } else if(claseDe(i, NL.class)) {
            result += imprime((NL)i);
        } else if(claseDe(i, New.class)) {
            result += imprime((New)i);
        } else if(claseDe(i, Delete.class)) {
            result += imprime((Delete)i);
        } else if(claseDe(i, Invoc.class)) {
            result += imprime((Invoc)i);
        } else if(claseDe(i, Instr_compuesta.class)) {
            result += imprime((Instr_compuesta)i);
        }
        return result;
    }

    public String imprime(Eval e) {
        return "@\n" + imprime(e.exp());
    }

    public String imprime(If i) {
        return "<if>\n" + imprime(i.exp())  + imprime(i.bloque());
    }

    public String imprime(IfElse ie) {
        return "<if>\n" + imprime(ie.exp())  + imprime(ie.bloque1()) + "<else>\n" + imprime(ie.bloque2());
    }

    public String imprime(While w) {
        return "<while>\n" + imprime(w.exp())  + imprime(w.bloque());
    }

    public String imprime(Write w) {
        return "<write>\n" + imprime(w.exp());
    }

    public String imprime(Read r) {
        return "<read>\n" + imprime(r.exp());
    }

    public String imprime(NL nl) {
        return "<nl>\n";
    }

    public String imprime(New n) {
        return "<new>\n" + imprime(n.exp());
    }

    public String imprime(Delete d) {
        return "<delete>\n" + imprime(d.exp());
    }

    public String imprime(Invoc i) {
        return "<invoc>\n" + imprime(i.iden())  + imprime(i.params_reales());
    }

    public String imprime(Instr_compuesta ic) {
        return imprime(ic.bloque());
    }

    public String imprime(Params_reales pr) {
        String result = "(\n";
        if(claseDe(pr, Si_params_reales.class)) {
            result += imprime(pr.lparams_reales());
        }
        result += ")\n";
        return result;
    }

    public String imprime(LParams_reales lpr) {
        String result = "";
        if(claseDe(lpr, Muchos_params_reales.class)) {
            result += imprime(lpr.lparams_reales()) + ",\n";
        }
        result += imprime(lpr.exp());
        return result;
    }

    public String imprime(Exp e) {
        String result = "";
        if(claseDe(e, ExpBin.class)) {
            result += imprime((ExpBin)e);
        } else if(claseDe(e, Menos_unario.class)) {
            result += imprime((Menos_unario)e);
        } else if(claseDe(e, Not.class)) {
            result += imprime((Not)e);
        } else if(claseDe(e, Indexacion.class)) {
            result += imprime((Indexacion)e);
        } else if(claseDe(e, Acceso.class)) {
            result += imprime((Acceso)e);
        } else if(claseDe(e, Indireccion.class)) {
            result += imprime((Indireccion)e);
        } else if(claseDe(e, Lit_ent.class)) {
            result += imprime((Lit_ent)e);
        } else if(claseDe(e, Lit_real.class)) {
            result += imprime((Lit_real)e);
        } else if(claseDe(e, True.class)) {
            result += imprime((True)e);
        } else if(claseDe(e, False.class)) {
            result += imprime((False)e);
        } else if(claseDe(e, Lit_cadena.class)) {
            result += imprime((Lit_cadena)e);
        } else if(claseDe(e, Iden.class)) {
            result += imprime((Iden)e);
        } else if(claseDe(e, Null.class)) {
            result += imprime((Null)e);
        }
        return result;
    }

    public String imprime(ExpBin exp) {
        return imprime(exp.opnd0())  + opToString(exp)  + imprime(exp.opnd1());
    }

    public String imprime(Menos_unario mu) {
        return "-\n" + imprime(mu.opnd());
    }

    public String imprime(Not n) {
        return "!\n" + imprime(n.opnd());
    }

    public String imprime(Indexacion i) {
        return imprime(i.opnd0())  + "[\n"  + imprime(i.opnd1())  + "]\n";
    }

    public String imprime(Acceso a) {
        return imprime(a.opnd())  + ".\n"  + imprime(a.iden());
    }

    public String imprime(Indireccion i) {
        return imprime(i.opnd())  + "^\n";
    }

    public String imprime(Lit_ent le) {
        return le.valor() + "\n";
    }

    public String imprime(Lit_real lr) {
        return lr.valor() + "\n";
    }

    public String imprime(True t) {
        return "<true>\n";
    }

    public String imprime(False f) {
        return "<false>\n";
    }

    public String imprime(Lit_cadena lc) {
        return lc.valor() + "\n";
    }

    public String imprime(Iden i) {
        return i.iden() + "\n";
    }

    public String imprime(String iden) {
        return iden + "\n";
    }

    private String opToString(ExpBin e) {
        if(claseDe(e, Asignacion.class)) {
            return "=\n";
        } else if(claseDe(e, Suma.class)) {
            return "+\n";
        } else if(claseDe(e, Resta.class)) {
            return "-\n";
        } else if(claseDe(e, Mul.class)) {
            return "*\n";
        } else if(claseDe(e, Div.class)) {
            return "/\n";
        } else if(claseDe(e, Mod.class)) {
            return "%\n";
        } else if(claseDe(e, Menor_que.class)) {
            return "<\n";
        } else if(claseDe(e, Mayor_que.class)) {
            return ">\n";
        } else if(claseDe(e, Menor_igual.class)) {
            return "<=\n";
        } else if(claseDe(e, Mayor_igual.class)) {
            return ">=\n";
        } else if(claseDe(e, Igual_comp.class)) {
            return "==\n";
        } else if(claseDe(e, Distinto_comp.class)) {
            return "!=\n";
        } else if(claseDe(e, And.class)) {
            return "<and>\n";
        } else if(claseDe(e, Or.class)) {
            return "<or>\n";
        }
        throw new RuntimeException("Operador no reconocido");
    }
    
    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    }    
}

