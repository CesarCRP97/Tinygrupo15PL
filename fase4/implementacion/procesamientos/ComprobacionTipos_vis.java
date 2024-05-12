package procesamientos;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;
import asint.SintaxisAbstractaTiny;

import java.util.ArrayList;

public class ComprobacionTipos_vis extends ProcesamientoDef {

    public Tipo_OK tipoOK;
    public Tipo_ERROR tipoERROR;
    public Tipo_null tipoNULL;
    public Tipo_bool tipoBOOL;
    public Tipo_int tipoINT;
    public Tipo_real tipoREAL;
    public Tipo_string tipoSTRING;

    private boolean errores;

    public boolean hayErrores() {
        return errores;
    }

    public void avisoError(Nodo n) {
        System.out.println("Error de tipos en la línea " + n.leeFila() + " y columna " + n.leeCol() + " en nodo " + n.toString());
        errores = true;
    }

    public void avisoError(Nodo n, String mensaje) {
        System.out.println("Error de tipos en la línea " + n.leeFila() + " y columna " + n.leeCol() + " en nodo " + n.toString() + ": " + mensaje);
        errores = true;
    }

    public ComprobacionTipos_vis() {
        errores = false;
        tipoERROR = new Tipo_ERROR();
        tipoOK = new Tipo_OK();
        tipoNULL = new Tipo_null();
        tipoBOOL = new Tipo_bool();
        tipoINT = new Tipo_int();
        tipoREAL = new Tipo_real();
        tipoSTRING = new Tipo_string();
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
        decs.putTipo(getTipoOK());
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
        params.putTipo(getTipoOK());
    }
    public void procesa(Muchos_params_form params) {
        params.lparams_form().procesa(this);
        params.param_form().procesa(this);
        params.putTipo(ambosOK(params.lparams_form().getTipo(), params.param_form().getTipo()));
    }
    public void procesa(Un_param_form params) {
        params.param_form().procesa(this);
        params.putTipo(params.param_form().getTipo());
    }
    public void procesa(Param_form_normal param) {
        param.tipo().procesa(this);
        param.putTipo(SintaxisAbstractaTiny.ref(param.tipo()));
    }
    public void procesa(Param_form_ref param) {
        param.tipo().procesa(this);
        param.putTipo(SintaxisAbstractaTiny.ref(param.tipo()));
    }

    public void procesa(Tipo_int tipo) {
        tipo.putTipo(getTipoOK());
    }
    public void procesa(Tipo_real tipo) {
        tipo.putTipo(getTipoOK());
    }
    public void procesa(Tipo_bool tipo) {
        tipo.putTipo(getTipoOK());
    }
    public void procesa(Tipo_string tipo) {
        tipo.putTipo(getTipoOK());
    }
    public void procesa(Tipo_array tipo) {
        tipo.tipo().procesa(this);
        try {
            int dim = Integer.parseInt(tipo.num());
            if (dim <= 0) {
                avisoError(tipo, "Tamaño de array menor o igual a 0");
                tipo.putTipo(getTipoERROR());
            } else {
                tipo.putTipo(tipo.tipo().getTipo());
                tipo.ponDim(dim);
            }
        } catch (NumberFormatException e) {
            avisoError(tipo, "Tamaño de array no es un entero");
            tipo.putTipo(getTipoERROR());
        }
        tipo.putTipo(tipo.tipo().getTipo());
    }
    public void procesa(Tipo_puntero tipo) {
        tipo.tipo().procesa(this);
        tipo.putTipo(SintaxisAbstractaTiny.ref(tipo.tipo()));
    }
    public void procesa(Tipo_struct tipo) {
        tipo.campos().procesa(this);
        if(!(tipo.camposDuplicados())) {
            tipo.ponNumCampos();
            tipo.putTipo(getTipoOK());
        } else {
            avisoError(tipo, "Campos duplicados en structura");
            tipo.putTipo(getTipoERROR());
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
            tipo.putTipo(getTipoOK());
        } else {
            avisoError(tipo, "Tipo no definido");
            tipo.putTipo(getTipoERROR());
        }
    }
    public void procesa(Si_instrs instrs) {
        instrs.linstrs().procesa(this);
        instrs.putTipo(instrs.linstrs().getTipo());
    }
    public void procesa(No_instrs instrs) {
        instrs.putTipo(getTipoOK());
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
        if(instr.exp().getTipo() instanceof Tipo_ERROR) {
            instr.putTipo(getTipoERROR());
        } else {
            instr.putTipo(getTipoOK());
        }
    }
    public void procesa(If instr) {
        instr.exp().procesa(this);
        instr.bloque().procesa(this);
        if(instr.exp().getTipo() instanceof Tipo_ERROR) {
            instr.putTipo(getTipoERROR());
        } else if (instr.exp().getTipo() instanceof Tipo_bool) {
            instr.putTipo(instr.bloque().getTipo());
        } else {
            avisoError(instr, "Expresion no booleana en condicion de if");
            instr.putTipo(getTipoERROR());
        }
    }
    public void procesa(IfElse instr) {
        instr.exp().procesa(this);
        instr.bloque1().procesa(this);
        instr.bloque2().procesa(this);
        if(instr.exp().getTipo() instanceof Tipo_ERROR) {
            instr.putTipo(getTipoERROR());
        } else if (instr.exp().getTipo() instanceof Tipo_bool) {
            instr.putTipo(ambosOK(instr.bloque1().getTipo(), instr.bloque2().getTipo()));
        } else {
            instr.putTipo(getTipoERROR());
            avisoError(instr, "Expresion no booleana en condicion de if-else");
        }
    }
    public void procesa(While instr) {
        instr.exp().procesa(this);
        instr.bloque().procesa(this);
        if(instr.exp().getTipo() instanceof Tipo_ERROR) {
            instr.putTipo(getTipoERROR());
        } else if (instr.exp().getTipo() instanceof Tipo_bool) {
            instr.putTipo(instr.bloque().getTipo());
        } else {
            instr.putTipo(getTipoERROR());
            avisoError(instr, "Expresion no booleana en condicion de while");
        }
    }
    public void procesa(Read instr) {
        instr.exp().procesa(this);
        if(instr.exp().getTipo() instanceof Tipo_ERROR) {
            instr.putTipo(getTipoERROR());
        } else if (SintaxisAbstractaTiny.designador(instr.exp()) && (instr.exp().getTipo() instanceof Tipo_int || instr.exp().getTipo() instanceof Tipo_real || instr.exp().getTipo() instanceof Tipo_string)) {
            instr.putTipo(getTipoOK());
        } else {
            instr.putTipo(getTipoERROR());
            avisoError(instr, "Expresion no designador o no entero, real o string");
        }
    }
    public void procesa(Write instr) {
        instr.exp().procesa(this);
        if(instr.exp().getTipo() instanceof Tipo_ERROR) {
            instr.putTipo(getTipoERROR());
        } else if (instr.exp().getTipo() instanceof Tipo_int || instr.exp().getTipo() instanceof Tipo_real || instr.exp().getTipo() instanceof Tipo_string || instr.exp().getTipo() instanceof Tipo_bool) {
            instr.putTipo(getTipoOK());
        } else {
            instr.putTipo(getTipoERROR());
            avisoError(instr, "Expresion no entero, real o string, es de tipo: " + instr.exp().getTipo());
        }
    }
    public void procesa(NL instr) {
    }
    public void procesa(New instr) {
        instr.exp().procesa(this);
        if (instr.exp().getTipo() instanceof Tipo_ERROR) {
            instr.putTipo(getTipoERROR());
        } else if (instr.exp().getTipo() instanceof Tipo_puntero) {
            instr.putTipo(getTipoOK());
        } else {
            instr.putTipo(getTipoERROR());
            avisoError(instr, "Expresion no puntero");
        }
    }
    public void procesa(Delete instr) {
        instr.exp().procesa(this);
        if (instr.exp().getTipo() instanceof Tipo_ERROR) {
            instr.putTipo(getTipoERROR());
        } else if (instr.exp().getTipo() instanceof Tipo_puntero) {
            instr.putTipo(getTipoOK());
        } else {
            instr.putTipo(getTipoERROR());
            avisoError(instr, "Expresion no puntero");
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
            if (instr.params_reales().getTipo() instanceof Tipo_ERROR) {
                instr.putTipo(getTipoERROR());
            } else if(instr.params_reales().numParams() == dec.params_form().numParams()) {
                if (compParams(instr.params_reales(), dec.params_form())) {
                    instr.putTipo(getTipoOK());
                } else {
                    instr.putTipo(getTipoERROR());
                    avisoError(instr, "Parametros no compatibles");
                }
            } else {
                instr.putTipo(getTipoERROR());
                avisoError(instr, "Numero de parametros incorrecto");
            }
        } else {
            instr.putTipo(getTipoERROR());
            avisoError(instr, "Identificador no es un procedimiento");
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
        if (SintaxisAbstractaTiny.designador(exp.opnd0())){
            if(compatibles(exp.opnd0().getTipo(), exp.opnd1().getTipo())) {
                exp.putTipo(exp.opnd0().getTipo());
            } else {
                avisoError(exp, "Tipos no compatibles para asignacion:" + exp.opnd0().getTipo() + " y " + exp.opnd1().getTipo());
                exp.putTipo(getTipoERROR());
            }
        } else {
            avisoError(exp, "Expresion no designador");
            exp.putTipo(getTipoERROR());
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
        Tipo tipo1 = exp.opnd0().getTipo();
        Tipo tipo2 = exp.opnd1().getTipo();
        if (tipo1 instanceof Tipo_ERROR || tipo2 instanceof Tipo_ERROR) {
            exp.putTipo(getTipoERROR());
        } else if (tipo1 instanceof Tipo_int && tipo2 instanceof Tipo_int) {
            exp.putTipo(getTipoInt());
        } else {
            avisoError(exp, "Tipos no compatibles para operacion modulo");
            exp.putTipo(getTipoERROR());
        }
    }
    public void procesa(Menos_unario exp) {
        exp.opnd().procesa(this);
        Tipo tipo = exp.opnd().getTipo();
        if (tipo instanceof Tipo_ERROR) {
            exp.putTipo(getTipoERROR());
        } else if (tipo instanceof Tipo_int || tipo instanceof Tipo_real) {
            exp.putTipo(tipo);
        } else {
            avisoError(exp, "Tipo no compatible para menos unario");
            exp.putTipo(getTipoERROR());
        }
    }
    public void procesa(Not exp) {
        exp.opnd().procesa(this);
        Tipo tipo = exp.opnd().getTipo();
        if (tipo instanceof Tipo_ERROR) {
            exp.putTipo(getTipoERROR());
        } else if (tipo instanceof Tipo_bool) {
            exp.putTipo(getTipoBool());
        } else {
            avisoError(exp, "Tipo no compatible para not");
            exp.putTipo(getTipoERROR());
        }
    }
    public void procesa(Indexacion exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        if (exp.opnd0().getTipo() instanceof Tipo_array && exp.opnd1().getTipo() instanceof Tipo_int) {
            Tipo_array tipo_array = (Tipo_array) exp.opnd0().getTipo();
            exp.putTipo(SintaxisAbstractaTiny.ref(tipo_array.tipo()));
        } else {
            avisoError(exp, "Tipos no compatibles para indexacion");
            exp.putTipo(getTipoERROR());
        }
    }
    public void procesa(Acceso exp) {
        exp.opnd().procesa(this);
        if (exp.opnd().getTipo() instanceof Tipo_struct) {
            Tipo_struct tipo = (Tipo_struct) exp.opnd().getTipo();
            Campo campo = tipo.campoPorIden(exp.iden());
            if (campo != null) {
                exp.putTipo(SintaxisAbstractaTiny.ref(campo.tipo()));
            } else {
                avisoError(exp, "Campo no encontrado");
                exp.putTipo(getTipoERROR());
            }
        } else {
            avisoError(exp, "Primer operando no es una estructura");
            exp.putTipo(getTipoERROR());
        }
    }
    public void procesa(Indireccion exp) {
        exp.opnd().procesa(this);
        if (exp.opnd().getTipo() instanceof Tipo_puntero) {
            Tipo_puntero puntero = (Tipo_puntero) exp.opnd().getTipo();
            exp.putTipo(SintaxisAbstractaTiny.ref(puntero.tipo()));
        } else {
            avisoError(exp, "Primer operando no es un puntero");
            exp.putTipo(getTipoERROR());
        }
    }
    public void procesa(Lit_ent exp) {
        exp.putTipo(getTipoInt());
    }
    public void procesa(Lit_real exp) {
        exp.putTipo(getTipoReal());
    }
    public void procesa(True exp) {
        exp.putTipo(getTipoBool());
    }
    public void procesa(False exp) {
        exp.putTipo(getTipoBool());
    }
    public void procesa(Lit_cadena exp) {
        exp.putTipo(getTipoString());
    }
    public void procesa(Iden exp) {
        exp.putTipo(SintaxisAbstractaTiny.ref(exp.vinculo()));
    }
    public void procesa(Null exp) {
        exp.putTipo(getTipoNull());
    }

    public Tipo_OK getTipoOK() {
        return tipoOK;
    }
    public Tipo_ERROR getTipoERROR() {
        return tipoERROR;
    }
    public Tipo_null getTipoNull() {
        return tipoNULL;
    }
    public Tipo_bool getTipoBool() {
        return tipoBOOL;
    }
    public Tipo_int getTipoInt() {
        return tipoINT;
    }
    public Tipo_real getTipoReal() {
        return tipoREAL;
    }
    public Tipo_string getTipoString() {
        return tipoSTRING;
    }
    

    public Tipo ambosOK(Tipo tipo1, Tipo tipo2) {
        if (tipo1 instanceof Tipo_OK && tipo2 instanceof Tipo_OK) {
            return getTipoOK();
        } else {
            return getTipoERROR();
        }
    }
    public class Ecuacion {
        public Tipo n1;
        public Tipo n2;

        public Ecuacion(Tipo n1, Tipo n2) {
            this.n1 = n1;
            this.n2 = n2;
        }

        public boolean equals(Object o) {
            if (o instanceof Ecuacion) {
                Ecuacion e = (Ecuacion) o;
                return (this.n1.getClass() == e.n1.getClass() && this.n2.getClass() == e.n2.getClass());
            } else {
                return false;
            }
        }
    }

    public boolean compatibles(Tipo n1, Tipo n2) {
        ArrayList<Ecuacion> ecuaciones = new ArrayList<Ecuacion>();
        ecuaciones.add(new Ecuacion(n1, n2));
        return unificables(n1, n2, ecuaciones);
    }

    public boolean unificables(Tipo n1, Tipo n2, ArrayList<Ecuacion> ecuaciones) {
        Tipo tipo1 = SintaxisAbstractaTiny.ref(n1);
        Tipo tipo2 = SintaxisAbstractaTiny.ref(n2);
        if (tipo1 instanceof Tipo_ERROR || tipo2 instanceof Tipo_ERROR) {
            return false;
        } else if (tipo1 instanceof Tipo_int && tipo2 instanceof Tipo_int) {
            return true;
        } else if (tipo1 instanceof Tipo_real && tipo2 instanceof Tipo_real) {
            return true;
        } else if (tipo1 instanceof Tipo_bool && tipo2 instanceof Tipo_bool) {
            return true;
        } else if (tipo1 instanceof Tipo_string && tipo2 instanceof Tipo_string) {
            return true;
        } else if (tipo1 instanceof Tipo_array && tipo2 instanceof Tipo_array) {
            if (son_unificables(tipo1.tipo(), tipo2.tipo(), ecuaciones) && ((Tipo_array) tipo1).dim() == ((Tipo_array)tipo2).dim()) {
                return true;
            } else {
                return false;
            }
        } else if (tipo1 instanceof Tipo_struct && tipo2 instanceof Tipo_struct) {
            if (tipo1.numCampos() == tipo2.numCampos()) {
                for (int i = 0; i < tipo1.numCampos(); i++) {
                    if (!son_unificables(tipo1.campoPorIndex(i).tipo(), tipo2.campoPorIndex(i).tipo(), ecuaciones)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else if (tipo1 instanceof Tipo_puntero) {
            if (tipo2 instanceof Tipo_null) {
                return true;
            } else if (tipo2 instanceof Tipo_puntero) {
                return son_unificables(tipo1.tipo(), tipo2.tipo(), ecuaciones);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean son_unificables(Tipo tipo1, Tipo tipo2, ArrayList<Ecuacion> ecuaciones) {
        if (ecuaciones.contains(new Ecuacion(tipo1, tipo2))) {
            return true;
        } else {
            ecuaciones.add(new Ecuacion(tipo1, tipo2));
            return unificables(tipo1, tipo2, ecuaciones);
        }
    }

    public void chequeaTipoExpBinArit(Exp n) {
        Tipo tipo1 = n.opnd0().getTipo();
        Tipo tipo2 = n.opnd1().getTipo();
        if (tipo1 instanceof Tipo_ERROR || tipo2 instanceof Tipo_ERROR) {
            n.putTipo(tipo1);
        } else if (tipo1 instanceof Tipo_int && tipo2 instanceof Tipo_int) {
            n.putTipo(tipo1);
        } else if (tipo1 instanceof Tipo_real && tipo2 instanceof Tipo_real) {
            n.putTipo(tipo1);
        } else if (tipo1 instanceof Tipo_int && tipo2 instanceof Tipo_real) {
            n.putTipo(tipo2);
        } else if (tipo1 instanceof Tipo_real && tipo2 instanceof Tipo_int) {
            n.putTipo(tipo1);
        } else {
            avisoError(n, "Tipos no compatibles para expresion aritmetica");
            n.putTipo(getTipoERROR());
        }
    }

    public void chequeaTipoExpBinLog(Exp n) {
        Tipo tipo1 = n.opnd0().getTipo();
        Tipo tipo2 = n.opnd1().getTipo();
        if (tipo1 instanceof Tipo_ERROR || tipo2 instanceof Tipo_ERROR) {
            n.putTipo(getTipoERROR());
        } else if (tipo1 instanceof Tipo_bool && tipo2 instanceof Tipo_bool) {
            n.putTipo(getTipoBool());
        } else {
            avisoError(n, "Tipos no compatibles para expresion logica");
            n.putTipo(getTipoERROR());
        }
    }

    public void chequeaTipoExpBinRel(Exp n) {
        Tipo tipo1 = n.opnd0().getTipo();
        Tipo tipo2 = n.opnd1().getTipo();
        if (tipo1 instanceof Tipo_ERROR || tipo2 instanceof Tipo_ERROR) {
            n.putTipo(tipo1);
        } else if (tipo1 instanceof Tipo_int && tipo2 instanceof Tipo_int) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_real && tipo2 instanceof Tipo_real) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_int && tipo2 instanceof Tipo_real) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_real && tipo2 instanceof Tipo_int) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_bool && tipo2 instanceof Tipo_bool) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_string && tipo2 instanceof Tipo_string) {
            n.putTipo(getTipoBool());
        } else {
            avisoError(n, "Tipos no compatibles para expresion relacional");
            n.putTipo(getTipoERROR());
        }
    }

    public void chequeaTipoExpBinRelIgual(Exp n) {
        Tipo tipo1 = n.opnd0().getTipo();
        Tipo tipo2 = n.opnd1().getTipo();
        if (tipo1 instanceof Tipo_ERROR || tipo2 instanceof Tipo_ERROR) {
            n.putTipo(getTipoERROR());
        } else if (tipo1 instanceof Tipo_int && tipo2 instanceof Tipo_int) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_real && tipo2 instanceof Tipo_real) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_int && tipo2 instanceof Tipo_real) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_real && tipo2 instanceof Tipo_int) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_bool && tipo2 instanceof Tipo_bool) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_string && tipo2 instanceof Tipo_string) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_null && tipo2 instanceof Tipo_null) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_puntero && tipo2 instanceof Tipo_puntero) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_puntero && tipo2 instanceof Tipo_null) {
            n.putTipo(getTipoBool());
        } else if (tipo1 instanceof Tipo_null && tipo2 instanceof Tipo_puntero) {
            n.putTipo(getTipoBool());
        } else {
            avisoError(n, "Tipos no compatibles para expresion relacional(== o !=)");
            n.putTipo(getTipoERROR());
        }
    }

    public boolean compParams(Params_reales params_reales, Params_form params_form) {
        for(int i = 0; i < params_form.numParams(); i++) {
            if (!compatibles(params_form.paramFormPorIndex(i).getTipo(), params_reales.paramRealPorIndex(i).getTipo())) {
                System.out.println("Tipos no compatibles: " + params_reales.paramRealPorIndex(i).getTipo() + " y " + params_form.paramFormPorIndex(i).getTipo());
                return false;
            }
        }
        return true;
    }
}
