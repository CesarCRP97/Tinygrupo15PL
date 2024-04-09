package evaluador;

import asint.SintaxisAbstractaTiny;
import java.util.HashMap;
import java.util.Map;

public class Evaluador extends SintaxisAbstractaTiny {
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
    public Evaluador() {
        this.env = new HashMap<>();
    }

    public void imprime(Prog p) {
        imprime(p.bloque());
        System.out.println("<EOF>");
    }

    public void imprime(Bloque b) {
        System.out.println("{");
        imprime(b.decs());
        imprime(b.insts());
        System.out.println("}");
    }

    public void imprime(Decs d) {
        if(claseDe(d, Si_decs.class)) {
            imprime(d.ldecs());
        }
        System.out.println("&&");
    }

    public void imprime(LDecs ldecs) {
        if claseDe(ldecs, Muchas_decs.class) {
            imprime(ldecs.ldecs());
            System.out.println(";");
        }
        imprime(ldecs.dec());
    }

    public void imprime(Dec dec) {
        if(claseDe(dec, Dec_variable.class)) {
            imprime((Dec_variable)dec);
        } else if(claseDe(dec, Dec_tipo.class)) {
            imprime((Dec_tipo)dec);
        } else if (claseDe(dec, Dec_proc.class)) {
            imprime((Dec_proc)dec);
        }
    }

    public void imprime(Dec_variable dec) {
        imprime(dec.tipo());
        imprime(dec.iden());
    }

    public void imprime(Dec_tipo dec) {
        System.out.println("<type>");
        imprime(dec.tipo());
        imprime(dec.iden());
    }

    public void imprime(Dec_proc dec) {
        System.out.println("<proc>");
        imprime(dec.iden());
        imprime(dec.params_form();
        imprime(dec.bloque());
    }

    public void imprime(Params_form pf) {
        System.out.println("(");
        if(claseDe(pf, Si_params_form.class)) {
            imprime(pf.lparams_form());
        }
        System.out.println(")");
    }

    public void imprime(LParams_form lpf) {
        if claseDe(lpf, Muchos_params_form.class) {
            imprime(lpf.lparams_form());
            System.out.println(",");
        }
        imprime(lpf.param_form());
    }

    public void imprime(Param_form pf) {
        imprime(pf.tipo());
        if(claseDe(pf, Param_form_ref.class)) {
            System.out.println("&");
        }
        imprime(pf.iden());
    }

    public void imprime(Tipo t) {
        if(claseDe(t, Tipo_int.class)) {
            System.out.println("<int>");
        } else if(claseDe(t, Tipo_real.class)) {
            System.out.println("<real>");
        } else if(claseDe(t, Tipo_bool.class)) {
            System.out.println("<bool>");
        } else if(claseDe(t, Tipo_string.class)) {
            System.out.println("<string>");
        } else if(claseDe(t, Tipo_array.class)) {
            System.out.println("[");
            imprime(t.num());
            System.out.println("]");
            imprime(t.tipo());
        } else if(claseDe(t, Tipo_puntero.class)) {
            System.out.println("^");
            imprime((t.tipo());
        } else if(claseDe(t, Tipo_struct.class)) {
            System.out.println("<struct>");
            imprime(t.campos());
            imprime(t.iden());
        } else if(claseDe(t, Tipo_iden.class)) {
            imprime(t.iden());
        }
    }

    public void imprime(Campos c) {
        System.out.println("{");
        imprime(c.lcampos());
        System.out.println("}");
    }

    public void imprime(LCampos lc) {
        if claseDe(lc, Muchos_campos.class) {
            imprime(lc.lcampos());
            System.out.println(",");
        }
        imprime(lc.campo());
    }
    
    public void imprime(Campo c) {
        imprime(c.tipo());
        imprime(c.iden());
    }

    public void imprime(Instrs i) {
        if(claseDe(i, Si_instrs.class)) {
            imprime(i.linstrs());
        }
    }

    public void imprime(LInstrs li) {
        if claseDe(li, Muchas_instrs.class) {
            imprime(li.linstrs());
            System.out.println(";");
        }
        imprime(li.instr());
    }

    public void imprime(Instr i) {
        if(claseDe(i, Eval.class)) {
            imprime((Eval)i);
        } else if(claseDe(i, If.class)) {
            imprime((If)i);
        } else if(claseDe(i, IfElse.class)) {
            imprime((IfElse)i);
        } else if(claseDe(i, While.class)) {
            imprime((While)i);
        } else if(claseDe(i, Write.class)) {
            imprime((Write)i);
        } else if(claseDe(i, Read.class)) {
            imprime((Read)i);
        } else if(claseDe(i, NL.class)) {
            imprime((NL)i);
        } else if(claseDe(i, New.class)) {
            imprime((New)i);
        } else if(claseDe(i, Delete.class)) {
            imprime((Delete)i);
        } else if(claseDe(i, Invoc.class)) {
            imprime((Invoc)i);
        } else if(claseDe(i, Instr_compuesta.class)) {
            imprime((Instr_compuesta)i);
        }
    }

    public void imprime(Eval e) {
        System.out.println("@");
        imprime(e.exp());
    }

    public void imprime(If i) {
        System.out.println("<if>");
        imprime(i.exp());
        imprime(i.bloque());
    }

    public void imprime(IfElse ie) {
        System.out.println("<if>");
        imprime(ie.exp());
        imprime(ie.bloque1());
        System.out.println("<else>");
        imprime(ie.bloque2());
    }

    public void imprime(While w) {
        System.out.println("<while>");
        imprime(w.exp());
        imprime(w.bloque());
    }

    public void imprime(Write w) {
        System.out.println("<write>");
        imprime(w.exp());
    }

    public void imprime(Read r) {
        System.out.println("<read>");
        imprime(r.exp());
    }

    public void imprime(NL nl) {
        System.out.println("<nl>");
    }

    public void imprime(New n) {
        System.out.println("<new>");
        imprime(n.exp());
    }

    public void imprime(Delete d) {
        System.out.println("<delete>");
        imprime(d.exp());
    }

    public void imprime(Invoc i) {
        System.out.println("<invoc>");
        imprime(i.iden());
        imprime(i.params_reales());
    }

    public void imprime(Inst_compuesta ic) {
        imprime(ic.bloque());
    }

    public void imprime(Params_reales pr) {
        System.out.println("(");
        if(claseDe(pr, Si_params_reales.class)) {
            imprime(pr.lparams_reales());
        }
        System.out.println(")");
    }

    public void imprime(LParams_reales lpr) {
        if claseDe(lpr, Muchos_params_reales.class) {
            imprime(lpr.lparams_reales());
            System.out.println(",");
        }
        imprime(lpr.exp());
    }

    public void imprime(Exp e) {
        if(claseDe(e, ExpBin.class) {
            imprime((ExpBin)e);
        } else if(claseDe(e, Menos_unario.class) {
            imprime((Menos_unario)e);
        } else if(claseDe(e, Not.class) {
            imprime((Not)e);
        } else if(claseDe(e, Indexacion.class) {
            imprime((Indexacion)e);
        } else if(claseDe(e, Acceso.class) {
            imprime((Acceso)e);
        } else if(claseDe(e, Indireccion.class) {
            imprime((Indireccion)e);
        } else if(claseDe(e, Lit_ent.class) {
            imprime((Lit_ent)e);
        } else if(claseDe(e, Lit_real.class) {
            imprime((Lit_real)e);
        } else if(claseDe(e, True.class) {
            imprime((True)e);
        } else if(claseDe(e, False.class) {
            imprime((False)e);
        } else if(claseDe(e, Lit_cadena.class) {
            imprime((Lit_cadena)e);
        } else if(claseDe(e, Iden.class) {
            imprime((Iden)e);
        } else if(claseDe(e, Null.class) {
            imprime((Null)e);
        }
    }

    public void imprime(ExpBin exp) {
        imprime(exp.opnd1());
        System.out.println(opToString(exp)); 
        imprime(exp.opnd2());
    }

    public void imprime(Menos_unario mu) {
        System.out.println("-");
        imprime(mu.opnd());
    }

    public void imprime(Not n) {
        System.out.println("!");
        imprime(n.opnd());
    }

    public void imprime(Indexacion i) {
        imprime(i.opnd1());
        System.out.println("[");
        imprime(i.opnd2());
        System.out.println("]");
    }

    public void imprime(Acceso a) {
        imprime(a.opnd());
        System.out.println(".");
        imprime(a.iden());
    }

    public void imprime(Indireccion i) {
        imprime(i.opnd());
        System.out.println("^");
    }


    public void imprime(Lit_ent le) {
        System.out.println(le.valor());
    }

    public void imprime(Lit_real lr) {
        System.out.println(lr.valor());
    }

    public void imprime(True t) {
        System.out.println("<true>");
    }

    public void imprime(False f) {
        System.out.println("<false>");
    }

    public void imprime(Lit_cadena lc) {
        System.out.println(lc.valor());
    }

    public void imprime(Iden i) {
        System.out.println(i.iden());
    }

    private String opToString(ExpBin e)
        switch (e.getClass()) {
            case Asignacion.class:
                return "=";
            case Suma.class:
                return "+";
            case Resta.class:
                return "-";
            case Mul.class:
                return "*";
            case Div.class:
                return "/";
            case Mod.class:
                return "%";
            case Menor_que.class:
                return "<";
            case Mayor_que.class:
                return ">";
            case Menor_igual.class:
                return "<=";
            case Mayor_igual.class:
                return ">=";
            case Igual_comp.class:
                return "==";
            case Distinto_comp.class:
                return "!=";
            case And.class:
                return "<and>";
            case Or.class:
                return "<or>";
            default:
                throw new RuntimeException("Operador desconocido");
        }
    
    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    }    
}
