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
        while ((nodo instanceof Tipo_iden) && (((Tipo_iden) nodo).getVinculo() instanceof Dec_tipo)) {
            nodo = ((Tipo_iden) nodo).getVinculo();
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
        } else if (nodo instanceof Tipo_pointer) {
            return "puntero";
        } else if (nodo instanceof Tipo_struct) {
            return "struct";
        } else if (nodo instanceof Null) {
            return "null";
        } else {
            return "error";
        }
    }

    public boolean compatAsig(Nodo tipo1, Nodo tipo2) {
        String tipo1Str = ref(tipo1);
        String tipo2Str = ref(tipo2);
        if (tipo1Str == "error" || tipo2Str == "error") {
            return false;
        } else if (tipo1Str == "int" && tipo2Str == "int") {
            return true;
        } else if (tipo1Str == "real" && tipo2Str == "real") {
            return true;
        } else if (tipo1Str == "bool" && tipo2Str == "bool") {
            return true;
        } else if (tipo1Str == "string" && tipo2Str == "string") {
            return true;
        } else if (tipo1Str == "array" && tipo2Str == "array") {
            if (compat(tipo1.tipo(), tipo2.tipo()) && tipo1.tam() == tipo2.tam()) {
                return true;
            } else {
                return false;
            }
        } else if (tipo1Str == "struct" && tipo2Str == "struct") {
            if (tipo1.numCampos() == tipo2.numCampos()) {
                for (int i = 0; i < tipo1.numCampos(); i++) {
                    if (!compat(tipo1.campo(i).tipo(), tipo2.campo(i).tipo())) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else if (tipo1Str == "puntero") {
            if (tipo2Str == "null") {
                return true;
            } else if (tipo2Str == "puntero") {
                return compat(tipo1.tipo(), tipo2.tipo());
            } else {
                return false;
            }
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
    public void procesa(Dec_var dec) {
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
    public void procesa(Tipo_pointer tipo) {
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
        if (tipo.getVinculo() instanceof Dec_tipo) {
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
        } else {
            String tipo = ref(instr.exp().getTipo());
        }
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
        instr.bloque().procesa(this);
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
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "=", 1, 0, exp);
    }
    public void procesa(Igual_comp exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "==", 0, 1, exp);
    }
    public void procesa(Distinto_comp exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "!=", 0, 1, exp);
    }
    public void procesa(Menor_que exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "<", 0, 1, exp);
    }
    public void procesa(Mayor_que exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), ">", 0, 1, exp);
    }
    public void procesa(Menor_igual exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "<=", 0, 1, exp);
    }
    public void procesa(Mayor_igual exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), ">=", 0, 1, exp);
    }
    public void procesa(Suma exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "+", 3, 2, exp);
    }
    public void procesa(Resta exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "-", 3, 3, exp);
    }
    public void procesa(And exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "<and>", 4, 3, exp);
    }
    public void procesa(Or exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "<or>", 4, 4, exp);
    }
    public void procesa(Mul exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "*", 4, 5, exp);
    }
    public void procesa(Div exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "/", 4, 5, exp);
    }
    public void procesa(Mod exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "%", 4, 5, exp);
    }
    public void procesa(Menos_unario exp) {
        imprimeExpUn(exp.opnd(), "-", 5, exp);
    }
    public void procesa(Not exp) {
        imprimeExpUn(exp.opnd(), "<not>", 5, exp);
    }
    public void procesa(Indexacion exp) {
        imprimeOpnd(exp.opnd0(), 6);
        exp.opnd1().procesa(this);
    }
    public void procesa(Acceso exp) {
        imprimeOpnd(exp.opnd(), 6);
    }
    public void procesa(Indireccion exp) {
        imprimeOpnd(exp.opnd(), 6);
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
    }
    public void procesa(Null exp) {
    }
    public void procesa(String str) {
    }

}
