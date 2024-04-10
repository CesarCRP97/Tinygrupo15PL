package procesamientos;

import asint.SintaxisAbstractaTiny;
import java.util.HashMap;
import java.util.Map;

public class Procesamiento_rec extends SintaxisAbstractaTiny {

    private String imprimeExpBin(Exp opnd0, Exp opnd1, String op, int np0, int np1) {
        return imprimeOpnd(opnd0, np0) + op + "\n" + imprimeOpnd(opnd1, np1);
    }

    private String imprimeExpUn(Exp opnd, String op, int np) {
        return op + "\n" + imprimeOpnd(opnd, np);
    }

    private String imprimeOpnd(Exp opnd, int np) {
        if(prioridad(opnd) < np) {
            return "(\n" + imprime(opnd) + ")\n";
        } else {
            return imprime(opnd);
        }
    }

    public Procesamiento_rec() {}

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
        if (claseDe(pf, Param_form_normal.class)) {
            return imprime(pf.tipo()) + imprime(pf.iden());
        } else if (claseDe(pf, Param_form_ref.class)) {
            return imprime(pf.tipo()) + "&\n" + imprime(pf.iden());
        } else {
            throw new RuntimeException("Parametro no reconocido");
        }
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
            result += imprime(t.tipo()) + "[\n" + imprime(t.num()) + "]\n";
        } else if(claseDe(t, Tipo_puntero.class)) {
            result += "^\n" + imprime(t.tipo());
        } else if(claseDe(t, Tipo_struct.class)) {
            result += "<struct>\n" + imprime(t.campos());
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
        if(claseDe(e, Asignacion.class)) {
            result += imprime((Asignacion)e);
        } else if(claseDe(e, Igual_comp.class)) {
            result += imprime((Igual_comp)e);
        } else if(claseDe(e, Distinto_comp.class)) {
            result += imprime((Distinto_comp)e);
        } else if(claseDe(e, Menor_que.class)) {
            result += imprime((Menor_que)e);
        } else if(claseDe(e, Mayor_que.class)) {
            result += imprime((Mayor_que)e);
        } else if(claseDe(e, Menor_igual.class)) {
            result += imprime((Menor_igual)e);
        } else if(claseDe(e, Mayor_igual.class)) {
            result += imprime((Mayor_igual)e);
        } else if(claseDe(e, Suma.class)) {
            result += imprime((Suma)e);
        } else if(claseDe(e, Resta.class)) {
            result += imprime((Resta)e);
        } else if(claseDe(e, Mul.class)) {
            result += imprime((Mul)e);
        } else if(claseDe(e, Div.class)) {
            result += imprime((Div)e);
        } else if(claseDe(e, Mod.class)) {
            result += imprime((Mod)e);
        } else if(claseDe(e, And.class)) {
            result += imprime((And)e);
        } else if(claseDe(e, Or.class)) {
            result += imprime((Or)e);
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

    public int prioridad(Exp e) {
        if(claseDe(e, Asignacion.class)) {
            return 0;
        } else if(claseDe(e, Igual_comp.class) || claseDe(e, Distinto_comp.class) || claseDe(e, Menor_que.class) || claseDe(e, Mayor_que.class) || claseDe(e, Menor_igual.class) || claseDe(e, Mayor_igual.class)) {
            return 1;
        } else if(claseDe(e, Suma.class) || claseDe(e, Resta.class)) {
            return 2;
        } else if(claseDe(e, And.class) || claseDe(e, Or.class)) {
            return 3;
        } else if(claseDe(e, Mul.class) || claseDe(e, Div.class) || claseDe(e, Mod.class)) {
            return 4;
        } else if(claseDe(e, Menos_unario.class) || claseDe(e, Not.class)) {
            return 5;
        } else if(claseDe(e, Indexacion.class) || claseDe(e, Acceso.class) || claseDe(e, Indireccion.class)) {
            return 6;
        } else if(claseDe(e, Lit_ent.class) || claseDe(e, Lit_real.class) || claseDe(e, True.class) || claseDe(e, False.class) || claseDe(e, Lit_cadena.class) || claseDe(e, Iden.class) || claseDe(e, Null.class)) {
            return 7;
        } else {
            throw new RuntimeException("Expresion no reconocida");
        }
    }

    public String imprime(Asignacion a) {
        return imprimeExpBin(a.opnd0(), a.opnd1(), "=", 1, 0);
    }

    public String imprime(Igual_comp ic) {
        return imprimeExpBin(ic.opnd0(), ic.opnd1(), "==", 0, 1);
    }

    public String imprime(Distinto_comp dc) {
        return imprimeExpBin(dc.opnd0(), dc.opnd1(), "!=", 0, 1);
    }

    public String imprime(Menor_que mq) {
        return imprimeExpBin(mq.opnd0(), mq.opnd1(), "<", 0, 1);
    }

    public String imprime(Mayor_que mg) {
        return imprimeExpBin(mg.opnd0(), mg.opnd1(), ">", 0, 1);
    }

    public String imprime(Menor_igual mi) {
        return imprimeExpBin(mi.opnd0(), mi.opnd1(), "<=", 0, 1);
    }

    public String imprime(Mayor_igual mg) {
        return imprimeExpBin(mg.opnd0(), mg.opnd1(), ">=", 0, 1);
    }

    public String imprime(Suma s) {
        return imprimeExpBin(s.opnd0(), s.opnd1(), "+", 3, 2);
    }

    public String imprime(Resta r) {
        return imprimeExpBin(r.opnd0(), r.opnd1(), "-", 3, 3);
    }

    public String imprime(And a) {
        return imprimeExpBin(a.opnd0(), a.opnd1(), "<and>", 4, 3);
    }

    public String imprime(Or o) {
        return imprimeExpBin(o.opnd0(), o.opnd1(), "<or>", 4, 4);
    }

    public String imprime(Mul m) {
        return imprimeExpBin(m.opnd0(), m.opnd1(), "*", 4, 5);
    }

    public String imprime(Div d) {
        return imprimeExpBin(d.opnd0(), d.opnd1(), "/", 4, 5);
    }

    public String imprime(Mod m) {
        return imprimeExpBin(m.opnd0(), m.opnd1(), "%", 4, 5);
    }

    public String imprime(Menos_unario mu) {
        return imprimeExpUn(mu.opnd(), "-", 5);
    }

    public String imprime(Not n) {
        return imprimeExpUn(n.opnd(), "<not>", 5);
    }

    public String imprime(Indexacion i) {
        return imprimeOpnd(i.opnd0(), 6)  + "[\n"  + imprime(i.opnd1())  + "]\n";
    }

    public String imprime(Acceso a) {
        return imprimeOpnd(a.opnd(), 6)  + ".\n"  + imprime(a.iden());
    }

    public String imprime(Indireccion i) {
        return imprimeOpnd(i.opnd(), 6)  + "^\n";
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

    public String imprime(Null n) {
        return "<null>\n";
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

