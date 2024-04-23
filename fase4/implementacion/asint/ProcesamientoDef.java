package asint;

import asint.SintaxisAbstractaTiny.*;

public class ProcesamientoDef implements Procesamiento {
    public void procesa(Prog prog) {};
    public void procesa(Bloque bloque) {};
    public void procesa(Si_decs decs) {};
    public void procesa(No_decs decs) {};
    public void procesa(Muchas_decs decs) {};
    public void procesa(Una_dec dec) {};
    public void procesa(Dec_variable dec) {};
    public void procesa(Dec_tipo dec) {};
    public void procesa(Dec_proc dec) {};
    public void procesa(Si_params_form params) {};
    public void procesa(No_params_form params) {};
    public void procesa(Muchos_params_form params) {};
    public void procesa(Un_param_form params) {};
    public void procesa(Param_form_normal param) {};
    public void procesa(Param_form_ref param) {};
    public void procesa(Tipo_int tipo) {};
    public void procesa(Tipo_bool tipo) {};
    public void procesa(Tipo_real tipo) {};
    public void procesa(Tipo_array tipo) {};
    public void procesa(Tipo_string tipo) {};
    public void procesa(Tipo_puntero tipo) {};
    public void procesa(Tipo_struct tipo) {};
    public void procesa(Tipo_iden tipo) {};
    public void procesa(Campos campos) {};
    public void procesa(Muchos_campos campos) {};
    public void procesa(Un_campo campo) {};
    public void procesa(Campo campo) {};
    public void procesa(Si_instrs instrs) {};
    public void procesa(No_instrs instrs) {};
    public void procesa(Muchas_instrs instrs) {};
    public void procesa(Una_instr instr) {};
    public void procesa(Eval instr) {};
    public void procesa(If instr) {};
    public void procesa(IfElse instr) {};
    public void procesa(While instr) {};
    public void procesa(Read instr) {};
    public void procesa(Write instr) {};
    public void procesa(NL instr) {};
    public void procesa(New instr) {};
    public void procesa(Delete instr) {};
    public void procesa(Instr_compuesta instr) {};
    public void procesa(Invoc instr) {};
    public void procesa(Si_params_reales params) {};
    public void procesa(No_params_reales params) {};
    public void procesa(Muchos_params_reales params) {};
    public void procesa(Un_param_real params) {};
    public void procesa(Asignacion exp) {};
    public void procesa(Igual_comp exp) {};
    public void procesa(Distinto_comp exp) {};
    public void procesa(Menor_que exp) {};
    public void procesa(Mayor_que exp) {};
    public void procesa(Menor_igual exp) {};
    public void procesa(Mayor_igual exp) {};
    public void procesa(Suma exp) {};
    public void procesa(Resta exp) {};
    public void procesa(And exp) {};
    public void procesa(Or exp) {};
    public void procesa(Mul exp) {};
    public void procesa(Div exp) {};
    public void procesa(Mod exp) {};
    public void procesa(Menos_unario exp) {};
    public void procesa(Not exp) {};
    public void procesa(Indexacion exp) {};
    public void procesa(Acceso exp) {};
    public void procesa(Indireccion exp) {};
    public void procesa(Lit_ent exp) {};
    public void procesa(Lit_real exp) {};
    public void procesa(True exp) {};
    public void procesa(False exp) {};
    public void procesa(Lit_cadena exp) {};
    public void procesa(Iden exp) {};
    public void procesa(Null exp) {};
    public void procesa(String str) {};
}