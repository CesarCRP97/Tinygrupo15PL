package procesamientos;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;

public class ComprobacionTipos extends ProcesamientoDef {

    public String ambosOK(String tipo1, String tipo2) {
        if (tipo1 == "ok" && tipo2 == "ok") {
            return "ok";
        } else {
            return "error";
        }
    }

    public void procesa(Prog prog) {
        prog.bloque().procesa(this);
        prog.putTipo(prog.bloque().getTipo());
    }

    public void procesa(Bloque bloque) {
        bloque.instrs().procesa(this);
        bloque.putTipo(bloque.instrs().getTipo());
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
        instr.putTipo(instr.exp().getTipo());
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
