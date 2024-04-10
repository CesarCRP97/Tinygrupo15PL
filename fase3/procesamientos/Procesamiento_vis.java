package procesamientos;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;

public class Procesamiento_vis extends ProcesamientoDef {
    private void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {
            System.out.println("(");
            opnd.procesa(this);
            System.out.println(")");
        } else {
            opnd.procesa(this);
        }
    }

    private void imprimeExpBin(Exp opnd0, Exp opnd1, String op, int np1, int np2) {
        imprimeOpnd(opnd0, np1);
        System.out.println(op);
        imprimeOpnd(opnd1, np2);
    }

    private void imprimeExpUn(Exp opnd, String op, int np) {
        System.out.println(op);
        imprimeOpnd(opnd, np);
    }

    public void procesa(Prog prog) {
        prog.bloque().procesa(this);
        System.out.println("<EOF>");
    }

    public void procesa(Bloque bloque) {
        System.out.println("{");
        bloque.decs().procesa(this);
        bloque.insts().procesa(this);
        System.out.println("}");
    }
    public void procesa(Si_decs decs) {
        decs.ldecs().procesa(this);
        System.out.println("&&");
    }
    public void procesa(No_decs decs) {
        System.out.println("&&");
    }
    public void procesa(Muchas_decs decs) {
        decs.dec().procesa(this);
        System.out.println(";");
        decs.ldecs().procesa(this);
    }
    public void procesa(Una_dec dec) {
        dec.dec().procesa(this);
    }
    public void procesa(Dec_variable dec) {
        dec.tipo().procesa(this);
        System.out.println(dec.iden());
    }
    public void procesa(Dec_tipo dec) {
        System.out.println("<type>");
        dec.tipo().procesa(this);
        System.out.println(dec.iden());
    }
    public void procesa(Dec_proc dec) {
        System.out.println("<proc>");
        System.out.println(dec.iden());
        dec.params_form().procesa(this);
        dec.bloque().procesa(this);
    }
    public void procesa(Si_params_form params) {
        System.out.println("(");
        params.lparams_form().procesa(this);
        System.out.println(")");
    }
    public void procesa(No_params_form params) {
        System.out.println("(");
        System.out.println(")");
    }
    public void procesa(Muchos_params_form params) {
        params.param_form().procesa(this);
        System.out.println(",");
        params.lparams_form().procesa(this);
    }
    public void procesa(Un_param_form params) {
        params.param_form().procesa(this);
    }
    public void procesa(Param_form_normal param) {
        param.tipo().procesa(this);
        System.out.println(param.iden());
    }
    public void procesa(Param_form_ref param) {
        param.tipo().procesa(this);
        System.out.println("&");
        System.out.println(param.iden());
    }
    public void procesa(Tipo_int tipo) {
        System.out.println("<int>");
    }
    public void procesa(Tipo_bool tipo) {
        System.out.println("<bool>");
    }
    public void procesa(Tipo_real tipo) {
        System.out.println("<real>");
    }
    public void procesa(Tipo_array tipo) {
        tipo.tipo().procesa(this);
        System.out.println("[");
        System.out.println(tipo.num());
        System.out.println("]");
    }
    public void procesa(Tipo_string tipo) {
        System.out.println("<string>");
        tipo.tipo().procesa(this);
    }
    public void procesa(Tipo_puntero tipo) {
        System.out.println("^");
        tipo.tipo().procesa(this);
    }
    public void procesa(Tipo_struct tipo) {
        System.out.println("<struct>");
        tipo.campos().procesa(this);
    }
    public void procesa(Tipo_iden tipo) {
        System.out.println(tipo.iden());
    }
    public void procesa(Campos campos) {
        System.out.println("{");
        campos.lcampos().procesa(this);
        System.out.println("}");
    }
    public void procesa(Muchos_campos campos) {
        campos.campo().procesa(this);
        System.out.println(",");
        campos.lcampos().procesa(this);
    }
    public void procesa(Un_campo campo) {
        campo.campo().procesa(this);
    }
    public void procesa(Campo campo) {
        campo.tipo().procesa(this);
        System.out.println(campo.iden());
    }
    public void procesa(Si_instrs instrs) {
        instrs.linstrs().procesa(this);
    }
    public void procesa(No_instrs instrs) {
    }
    public void procesa(Muchas_instrs instrs) {
        instrs.instr().procesa(this);
        System.out.println(";");
        instrs.linstrs().procesa(this);
    }
    public void procesa(Una_instr instr) {
        instr.instr().procesa(this);
    }
    public void procesa(Eval instr) {
        System.out.println("@");
        instr.exp().procesa(this);
    }
    public void procesa(If instr) {
        System.out.println("<if>");
        instr.exp().procesa(this);
        instr.bloque().procesa(this);
    }
    public void procesa(IfElse instr) {
        System.out.println("<if>");
        instr.exp().procesa(this);
        instr.bloque1().procesa(this);
        System.out.println("<else>");
        instr.bloque2().procesa(this);
    }
    public void procesa(While instr) {
        System.out.println("<while>");
        instr.exp().procesa(this);
        instr.bloque().procesa(this);
    }
    public void procesa(Read instr) {
        System.out.println("<read>");
        instr.exp().procesa(this);
    }
    public void procesa(Write instr) {
        System.out.println("<write>");
        instr.exp().procesa(this);
    }
    public void procesa(NL instr) {
        System.out.println("<nl>");
    }
    public void procesa(New instr) {
        System.out.println("<new>");
        instr.exp().procesa(this);
    }
    public void procesa(Delete instr) {
        System.out.println("<delete>");
        instr.exp().procesa(this);
    }
    public void procesa(Instr_compuesta instr) {
        instr.bloque().procesa(this);
    }
    public void procesa(Invoc instr) {
        System.out.println("<call>");
        System.out.println(instr.iden());
        instr.params_reales().procesa(this);
    }
    public void procesa(Si_params_reales params) {
        System.out.println("(");
        params.lparams_reales().procesa(this);
        System.out.println(")");
    }
    public void procesa(No_params_reales params) {
        System.out.println("(");
        System.out.println(")");
    }
    public void procesa(Muchos_params_reales params) {
        params.exp().procesa(this);
        System.out.println(",");
        params.lparams_reales().procesa(this);
    }
    public void procesa(Un_param_real params) {
        params.exp().procesa(this);
    }
    public void procesa(Asignacion exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "=", 1, 0);
    }
    public void procesa(Igual_comp exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "==", 0, 1);
    }
    public void procesa(Distinto_comp exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "!=", 0, 1);
    }
    public void procesa(Menor_que exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "<", 0, 1);
    }
    public void procesa(Mayor_que exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), ">", 0, 1);
    }
    public void procesa(Menor_igual exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "<=", 0, 1);
    }
    public void procesa(Mayor_igual exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), ">=", 0, 1);
    }
    public void procesa(Suma exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "+", 3, 2);
    }
    public void procesa(Resta exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "-", 3, 3);
    }
    public void procesa(And exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "<and>", 4, 3);
    }
    public void procesa(Or exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "<or>", 4, 4);
    }
    public void procesa(Mul exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "*", 4, 5);
    }
    public void procesa(Div exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "/", 4, 5);
    }
    public void procesa(Mod exp) {
        imprimeExpBin(exp.opnd0(), exp.opnd1(), "%", 4, 5);
    }
    public void procesa(Menos_unario exp) {
        imprimeExpUn(exp.opnd(), "-", 5);
    }
    public void procesa(Not exp) {
        imprimeExpUn(exp.opnd(), "<not>", 5);
    }
    public void procesa(Indexacion exp) {
        imprimeOpnd(exp.opnd0(), 6);
        System.out.println("[");
        exp.opnd1().procesa(this);
        System.out.println("]");
    }
    public void procesa(Acceso exp) {
        imprimeOpnd(exp.opnd(), 6);
        System.out.println(".");
        System.out.println(exp.iden());
    }
    public void procesa(Indireccion exp) {
        System.out.println("^");
        imprimeOpnd(exp.opnd(), 6);
    }
    public void procesa(Lit_ent exp) {
        System.out.println(exp.valor());
    }
    public void procesa(Lit_real exp) {
        System.out.println(exp.valor());
    }
    public void procesa(True exp) {
        System.out.println("<true>");
    }
    public void procesa(False exp) {
        System.out.println("<false>");
    }
    public void procesa(Lit_cadena exp) {
        System.out.println(exp.valor());
    }
    public void procesa(Iden exp) {
        System.out.println(exp.iden());
    }
    public void procesa(Null exp) {
        System.out.println("<null>");
    }
    public void procesa(String str) {
        System.out.println(str);
    }

}
