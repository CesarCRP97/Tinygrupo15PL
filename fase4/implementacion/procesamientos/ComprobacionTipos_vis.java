package procesamientos;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;

public class ComprobacionTipos_vis extends ProcesamientoDef {

    private void avisoError(Nodo n) {
        System.out.println("Error de tipos en la línea " + n.leeFila() + " y columna " + n.leeCol());
    }

    public String ambosOK(String tipo1, String tipo2) {
        if (tipo1 == "ok" && tipo2 == "ok") {
            return "ok";
        } else {
            return "error";
        }
    }

    public String ref(Nodo nodo) {
        while ((nodo instanceof Tipo_iden) && (((Tipo_iden) nodo).vinculo() instanceof Dec_tipo)) {
            nodo = ((Tipo_iden) nodo).vinculo();
        }
        if (nodo instanceof Tipo_int) {
            return "int";
        } else if (nodo instanceof Tipo_real) {
            return "real";
        } else if (nodo instanceof Tipo_bool) {
            return "bool";
        } else if (nodo instanceof Tipo_string) {
            return "string";
        } else if (nodo instanceof Tipo_array) {
            return "array";
        } else if (nodo instanceof Tipo_puntero) {
            return "puntero";
        } else if (nodo instanceof Tipo_struct) {
            return "struct";
        } else if (nodo instanceof Null) {
            return "null";
        } else {
            return "error";
        }
    }

    public boolean compatAsig(Tipo n1, Tipo n2) {
        String tipo1 = ref(n1);
        String tipo2 = ref(n2);
        if (tipo1 == "error" || tipo2 == "error") {
            return false;
        } else if (tipo1 == "int" && tipo2 == "int") {
            return true;
        } else if (tipo1 == "real" && tipo2 == "real") {
            return true;
        } else if (tipo1 == "bool" && tipo2 == "bool") {
            return true;
        } else if (tipo1 == "string" && tipo2 == "string") {
            return true;
        } else if (tipo1 == "array" && tipo2 == "array") {
            if (compatAsig(n1.tipo(), n2.tipo()) && n1.tam() == n2.tam()) {
                return true;
            } else {
                return false;
            }
        } else if (tipo1 == "struct" && tipo2 == "struct") {
            if (n1.numCampos() == n2.numCampos()) {
                for (int i = 0; i < n1.numCampos(); i++) {
                    if (!compatAsig(n1.campoPorIndex(i).tipo(), n2.campoPorIndex(i).tipo())) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else if (tipo1 == "puntero") {
            if (tipo2 == "null") {
                return true;
            } else if (tipo2 == "puntero") {
                return compatAsig(n1.tipo(), n2.tipo());
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public boolean designador(Nodo n) {
        if (n instanceof Iden || n instanceof Indexacion || n instanceof Acceso || n instanceof Indireccion) {
            return true;
        } else {
            return false;
        }
    }

    public void chequeaTipoExpBinArit(Exp n) {
        String tipo1 = n.opnd0().getTipo();
        String tipo2 = n.opnd1().getTipo();
        if (tipo1 == "error" || tipo2 == "error") {
            n.putTipo("error");
        } else if (tipo1 == "int" && tipo2 == "int") {
            n.putTipo("int");
        } else if (tipo1 == "real" && tipo2 == "real") {
            n.putTipo("real");
        } else if (tipo1 == "int" && tipo2 == "real") {
            n.putTipo("real");
        } else if (tipo1 == "real" && tipo2 == "int") {
            n.putTipo("real");
        } else {
            avisoError(n);
            n.putTipo("error");
        }
    }

    public void chequeaTipoExpBinLog(Exp n) {
        String tipo1 = n.opnd0().getTipo();
        String tipo2 = n.opnd1().getTipo();
        if (tipo1 == "error" || tipo2 == "error") {
            n.putTipo("error");
        } else if (tipo1 == "bool" && tipo2 == "bool") {
            n.putTipo("bool");
        } else {
            avisoError(n);
            n.putTipo("error");
        }
    }

    public void chequeaTipoExpBinRel(Exp n) {
        String tipo1 = n.opnd0().getTipo();
        String tipo2 = n.opnd1().getTipo();
        if (tipo1 == "error" || tipo2 == "error") {
            n.putTipo("error");
        } else if (tipo1 == "int" && tipo2 == "int") {
            n.putTipo("bool");
        } else if (tipo1 == "real" && tipo2 == "real") {
            n.putTipo("bool");
        } else if (tipo1 == "int" && tipo2 == "real") {
            n.putTipo("bool");
        } else if (tipo1 == "real" && tipo2 == "int") {
            n.putTipo("bool");
        } else if (tipo1 == "bool" && tipo2 == "bool") {
            n.putTipo("bool");
        } else if (tipo1 == "string" && tipo2 == "string") {
            n.putTipo("bool");
        } else {
            avisoError(n);
            n.putTipo("error");
        }
    }

    public void chequeaTipoExpBinRelIgual(Exp n) {
        String tipo1 = n.opnd0().getTipo();
        String tipo2 = n.opnd1().getTipo();
        if (tipo1 == "error" || tipo2 == "error") {
            n.putTipo("error");
        } else if (tipo1 == "int" && tipo2 == "int") {
            n.putTipo("bool");
        } else if (tipo1 == "real" && tipo2 == "real") {
            n.putTipo("bool");
        } else if (tipo1 == "int" && tipo2 == "real") {
            n.putTipo("bool");
        } else if (tipo1 == "real" && tipo2 == "int") {
            n.putTipo("bool");
        } else if (tipo1 == "bool" && tipo2 == "bool") {
            n.putTipo("bool");
        } else if (tipo1 == "string" && tipo2 == "string") {
            n.putTipo("bool");
        } else if (tipo1 == "null" && tipo2 == "null") {
            n.putTipo("bool");
        } else if (tipo1 == "puntero" && tipo2 == "puntero") {
            n.putTipo("bool");
        } else if (tipo1 == "puntero" && tipo2 == "null") {
            n.putTipo("bool");
        } else if (tipo1 == "null" && tipo2 == "puntero") {
            n.putTipo("bool");
        } else {
            avisoError(n);
            n.putTipo("error");
        }
    }

    public boolean compParams(Si_params_reales params, Si_params_form params_form) {
        return compParams(params.lparams_reales(), params_form.lparams_form());
    }
    public boolean compParams(No_params_reales params, No_params_form params_form) {
        return true;
    }
    public boolean compParams(Muchos_params_reales params, Muchos_params_form params_form) {
        return compParams(params.lparams_reales(), params_form.lparams_form()) && compParams(params.exp(), params_form.param());
    }
    public boolean compParams(Un_param_real params, Un_param_form params_form) {
        return compParams(params.exp(), params_form.param());
    }
    public boolean compParams(Exp exp, Param_form_normal param) {
        if (exp.getTipo() == "error") {
            return false;
        } else if (exp.getTipo() == param.tipo().getTipo()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean compParams(Exp exp, Param_form_ref param) {
        if (exp.getTipo() == "error") {
            return false;
        } else if (designador(exp) && exp.getTipo() == param.tipo().getTipo()) {
            return true;
        } else {
            return false;
        }
    }

    public void procesa(Prog prog) {
        prog.bloque().procesa(this);
        prog.putTipo(prog.bloque().getTipo());
    }

    public void procesa(Bloque bloque) {
        bloque.decs().procesa(this);
        bloque.insts().procesa(this);
        bloque.putTipo(ambosOK(bloque.decs().getTipo(), bloque.insts().getTipo()));
    }
    
    public void procesa(Si_decs decs) {
        decs.ldecs().procesa(this);
    }
    public void procesa(No_decs decs) {
        decs.putTipo("ok");
    }
    public void procesa(Muchas_decs decs) {
        decs.ldecs().procesa(this);
        decs.dec().procesa(this);
        decs.putTipo(ambosOK(decs.ldecs().getTipo(), decs.dec().getTipo()));
    }
    public void procesa(Una_dec dec) {
        dec.dec().procesa(this);
        dec.putTipo(dec.dec().getTipo());
    }
    public void procesa(Dec_variable dec) {
        dec.tipo().procesa(this);
        dec.putTipo(dec.tipo().getTipo());
    }
    public void procesa(Dec_tipo dec) {
        dec.tipo().procesa(this);
        dec.putTipo(dec.tipo().getTipo());
    }
    public void procesa(Dec_proc dec) {
        dec.params_form().procesa(this);
        dec.bloque().procesa(this);
        dec.putTipo(ambosOK(dec.params_form().getTipo(), dec.bloque().getTipo()));
    }
    public void procesa(Si_params_form params) {
        params.lparams_form().procesa(this);
    }
    public void procesa(No_params_form params) {
        params.putTipo("ok");
    }
    public void procesa(Muchos_params_form params) {
        params.lparams_form().procesa(this);
        params.param().procesa(this);
        params.putTipo(ambosOK(params.lparams_form().getTipo(), params.param().getTipo()));
    }
    public void procesa(Un_param_form params) {
        params.param().procesa(this);
        params.putTipo(params.param().getTipo());
    }
    public void procesa(Param_form_normal param) {
        param.tipo().procesa(this);
        param.putTipo(param.tipo().getTipo());
    }
    public void procesa(Param_form_ref param) {
        param.tipo().procesa(this);
        param.putTipo(param.tipo().getTipo());
    }

    public void procesa(Tipo_int tipo) {
        tipo.putTipo("ok");
    }
    public void procesa(Tipo_real tipo) {
        tipo.putTipo("ok");
    }
    public void procesa(Tipo_bool tipo) {
        tipo.putTipo("ok");
    }
    public void procesa(Tipo_string tipo) {
        tipo.putTipo("ok");
    }
    public void procesa(Tipo_array tipo) {
        tipo.tipo().procesa(this);
        try {
            int tam = Integer.parseInt(tipo.tam().toString());
            if (tam <= 0) {
                avisoError(tipo);
                tipo.putTipo("error");
            } else {
                tipo.putTipo(tipo.tipo().getTipo());
                tipo.putTam(tam);
            }
        } catch (NumberFormatException e) {
            avisoError(tipo);
            tipo.putTipo("error");
        }
        tipo.putTipo(tipo.tipo().getTipo());
    }
    public void procesa(Tipo_puntero tipo) {
        tipo.tipo().procesa(this);
        tipo.putTipo(tipo.tipo().getTipo());
    }
    public void procesa(Tipo_struct tipo) {
        tipo.campos().procesa(this);
        if(!(tipo.camposDuplicados())) {
            tipo.ponNumCampos();
            tipo.putTipo("ok");
        } else {
            avisoError(tipo);
            tipo.putTipo("error");
        }
    }
    public void procesa(Campos campos) {
        campos.lcampos().procesa(this);
        campos.putTipo(campos.lcampos().getTipo());
    }
    public void procesa(Muchos_campos campos) {
        campos.lcampos().procesa(this);
        campos.campo().procesa(this);
        campos.putTipo(ambosOK(campos.lcampos().getTipo(), campos.campo().getTipo()));
    }
    public void procesa(Un_campo campo) {
        campo.campo().procesa(this);
        campo.putTipo(campo.campo().getTipo());
    }
    public void procesa(Campo campo) {
        campo.tipo().procesa(this);
        campo.putTipo(campo.tipo().getTipo());
    }
    public void procesa(Tipo_iden tipo) {
        if (tipo.vinculo() instanceof Dec_tipo) {
            tipo.putTipo("ok");
        } else {
            avisoError(tipo);
            tipo.putTipo("error");
        }
    }
    public void procesa(Si_instrs instrs) {
        instrs.linstrs().procesa(this);
        instrs.putTipo(instrs.linstrs().getTipo());
    }
    public void procesa(No_instrs instrs) {
        instrs.putTipo("ok");
    }
    public void procesa(Muchas_instrs instrs) {
        instrs.linstrs().procesa(this);
        instrs.instr().procesa(this);
        instrs.putTipo(ambosOK(instrs.linstrs().getTipo(), instrs.instr().getTipo()));
    }
    public void procesa(Una_instr instr) {
        instr.instr().procesa(this);
        instr.putTipo(instr.instr().getTipo());
    }
    public void procesa(Eval instr) {
        instr.exp().procesa(this);
        if(instr.exp().getTipo() == "error") {
            instr.putTipo("error");
        } else {
            instr.putTipo("ok");
        }
    }
    public void procesa(If instr) {
        instr.exp().procesa(this);
        instr.bloque().procesa(this);
        if(instr.exp().getTipo() == "error") {
            instr.putTipo("error");
        } else if (instr.exp().getTipo() == "bool") {
            instr.putTipo(instr.bloque().getTipo());
        } else {
            avisoError(instr);
            instr.putTipo("error");
        }
    }
    public void procesa(IfElse instr) {
        instr.exp().procesa(this);
        instr.bloque1().procesa(this);
        instr.bloque2().procesa(this);
        if(instr.exp().getTipo() == "error") {
            instr.putTipo("error");
        } else if (instr.exp().getTipo() == "bool") {
            instr.putTipo(ambosOK(instr.bloque1().getTipo(), instr.bloque2().getTipo()));
        } else {
            instr.putTipo("error");
            avisoError(instr);
        }
    }
    public void procesa(While instr) {
        instr.exp().procesa(this);
        instr.bloque().procesa(this);
        if(instr.exp().getTipo() == "error") {
            instr.putTipo("error");
        } else if (instr.exp().getTipo() == "bool") {
            instr.putTipo(instr.bloque().getTipo());
        } else {
            instr.putTipo("error");
            avisoError(instr);
        }
    }
    public void procesa(Read instr) {
        instr.exp().procesa(this);
        if(instr.exp().getTipo() == "error") {
            instr.putTipo("error");
        } else if (designador(instr.exp()) && (instr.exp().getTipo() == "int" || instr.exp().getTipo() == "real" || instr.exp().getTipo() == "string")) {
            instr.putTipo("ok");
        } else {
            instr.putTipo("error");
            avisoError(instr);
        }
    }
    public void procesa(Write instr) {
        instr.exp().procesa(this);
        if(instr.exp().getTipo() == "error") {
            instr.putTipo("error");
        } else if (instr.exp().getTipo() == "int" || instr.exp().getTipo() == "real" || instr.exp().getTipo() == "string") {
            instr.putTipo("ok");
        } else {
            instr.putTipo("error");
            avisoError(instr);
        }
    }
    public void procesa(NL instr) {
    }
    public void procesa(New instr) {
        instr.exp().procesa(this);
        if (instr.exp().getTipo() == "error") {
            instr.putTipo("error");
        } else if (instr.exp().getTipo() == "puntero") {
            instr.putTipo("ok");
        } else {
            instr.putTipo("error");
            avisoError(instr);
        }
    }
    public void procesa(Delete instr) {
        instr.exp().procesa(this);
        if (instr.exp().getTipo() == "error") {
            instr.putTipo("error");
        } else if (instr.exp().getTipo() == "puntero") {
            instr.putTipo("ok");
        } else {
            instr.putTipo("error");
            avisoError(instr);
        }
    }
    public void procesa(Instr_compuesta instr) {
        instr.bloque().procesa(this);
        instr.putTipo(instr.bloque().getTipo());
    }
    public void procesa(Invoc instr) {
        instr.params_reales().procesa(this);
        if (instr.vinculo() instanceof Dec_proc) {
            Dec_proc dec = (Dec_proc) instr.vinculo();
            if (instr.params_reales().getTipo() == "error") {
                instr.putTipo("error");
            } else if (instr.params_reales().getTipo() == "ok") {
                if(instr.params_reales().numParams() == dec.params_form().numParams()) {
                    if (compParams(instr.params_reales(), dec.params_form())) {
                        instr.putTipo("ok");
                    } else {
                        instr.putTipo("error");
                        avisoError(instr);
                    }
                } else {
                    instr.putTipo("error");
                    avisoError(instr);
                }
            } else {
                instr.putTipo("error");
                avisoError(instr);
            }
        } else {
            instr.putTipo("error");
            avisoError(instr);
        }
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
        if (designador(exp.opnd0()) && compatAsig(exp.opnd0().getTipo(), exp.opnd1().getTipo())) {
            exp.putTipo("ok");
        } else {
            avisoError(exp);
            exp.putTipo("error");
        }
    }
    public void procesa(Igual_comp exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        chequeaTipoExpBinRelIgual(exp);
    }
    public void procesa(Distinto_comp exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        chequeaTipoExpBinRelIgual(exp);
    }
    public void procesa(Menor_que exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        chequeaTipoExpBinRel(exp);
    }
    public void procesa(Mayor_que exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        chequeaTipoExpBinRel(exp);
    }
    public void procesa(Menor_igual exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        chequeaTipoExpBinRel(exp);
    }
    public void procesa(Mayor_igual exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        chequeaTipoExpBinRel(exp);
    }
    public void procesa(Suma exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        chequeaTipoExpBinArit(exp);
    }
    public void procesa(Resta exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        chequeaTipoExpBinArit(exp);
    }
    public void procesa(And exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        chequeaTipoExpBinLog(exp);
    }
    public void procesa(Or exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        chequeaTipoExpBinLog(exp);
    }
    public void procesa(Mul exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        chequeaTipoExpBinArit(exp);
    }
    public void procesa(Div exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        chequeaTipoExpBinArit(exp);
    }
    public void procesa(Mod exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        tipo1 = exp.opnd0().getTipo();
        tipo2 = exp.opnd1().getTipo();
        if (tipo1 == "error" || tipo2 == "error") {
            exp.putTipo("error");
        } else if (tipo1 == "int" && tipo2 == "int") {
            exp.putTipo("int");
        } else {
            avisoError(exp);
            exp.putTipo("error");
        }
    }
    public void procesa(Menos_unario exp) {
        exp.opnd().procesa(this);
        tipo = exp.opnd().getTipo();
        if (tipo == "error") {
            exp.putTipo("error");
        } else if (tipo == "int" || tipo == "real") {
            exp.putTipo(tipo);
        } else {
            avisoError(exp);
            exp.putTipo("error");
        }
    }
    public void procesa(Not exp) {
        exp.opnd().procesa(this);
        tipo = exp.opnd().getTipo();
        if (tipo == "error") {
            exp.putTipo("error");
        } else if (tipo == "bool") {
            exp.putTipo("bool");
        } else {
            avisoError(exp);
            exp.putTipo("error");
        }
    }
    public void procesa(Indexacion exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        if (exp.opnd0().getTipo() == "array" && exp.opnd1().getTipo() == "int") {
            exp.putTipo(exp.opnd0().tipo().getTipo());
        } else {
            avisoError(exp);
            exp.putTipo("error");
        }
    }
    public void procesa(Acceso exp) {
        exp.opnd().procesa(this);
        System.out.println("Tipo del operando: " + exp.opnd().getTipo());
        System.out.println("Identificador del campo: " + exp.iden());
        if (exp.opnd().getTipo() == "struct") {
            Campo campo = ((Tipo_struct) ((Iden) exp.opnd()).vinculo()).campoPorIden(exp.iden());
            if (campo != null) {
                exp.putTipo(ref(campo.tipo()));
            } else {
                avisoError(exp);
                exp.putTipo("error");
            }
        } else {
            avisoError(exp);
            exp.putTipo("error");
        }
    }
    public void procesa(Indireccion exp) {
        exp.opnd().procesa(this);
        if (exp.opnd().getTipo() == "puntero") {
            exp.putTipo(exp.opnd().tipo().getTipo());
        } else {
            avisoError(exp);
            exp.putTipo("error");
        }
    }
    public void procesa(Lit_ent exp) {
        exp.putTipo("int");
    }
    public void procesa(Lit_real exp) {
        exp.putTipo("real");
    }
    public void procesa(True exp) {
        exp.putTipo("bool");
    }
    public void procesa(False exp) {
        exp.putTipo("bool");
    }
    public void procesa(Lit_cadena exp) {
        exp.putTipo("string");
    }
    public void procesa(Iden exp) {
        exp.putTipo(ref(exp.vinculo()));
    }
    public void procesa(Null exp) {
        exp.putTipo("null");
    }
    public void procesa(String str) {
        exp.putTipo("string");
    }

}
