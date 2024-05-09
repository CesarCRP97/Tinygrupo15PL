package procesamientos;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny;
import asint.SintaxisAbstractaTiny.*;

import maquinap.MaquinaP;

import java.util.Stack;

public class Etiquetado_vis extends ProcesamientoDef {

    private int etiqueta;
    private Stack<Dec> procs = new Stack<Dec>();
    
    public Etiquetado_vis() {
        this.etiqueta = 0;
        this.procs = new Stack<Dec>();
    }

    public void procesa(Prog prog) {
        prog.bloque().procesa(this);
        while (!procs.empty()) {
            Dec_proc proc = (Dec_proc) procs.pop();
            etiqueta++;
            proc.bloque().procesa(this);
            etiqueta += 2;
        }
    }

    public void procesa(Bloque bloque) {
        bloque.setPrimInstr(etiqueta);
        bloque.decs().procesa(this);
        bloque.insts().procesa(this);
        bloque.setSigInstr(etiqueta);
    }

    public void procesa(Si_instrs instrs) {
        instrs.linstrs().procesa(this);
    }

    public void procesa(Muchas_instrs instrs) {

        instrs.linstrs().procesa(this);
        instrs.instr().procesa(this);
    }

    public void procesa(Una_instr instr) {
        instr.instr().procesa(this);
    }

    public void procesa(Eval instr) {
        instr.exp().procesa(this);
        etiqAccesoValor(instr.exp());
    }

    public void procesa(If instr) {
        instr.setPrimInstr(etiqueta);
        instr.exp().procesa(this);
        etiqAccesoValor(instr.exp());
        etiqueta++;
        instr.bloque().procesa(this);
        instr.setSigInstr(etiqueta);
    }

    public void procesa(IfElse instr) {
        instr.setPrimInstr(etiqueta);
        instr.exp().procesa(this);
        etiqAccesoValor(instr.exp());
        instr.bloque1().procesa(this);
        etiqueta++;
        instr.bloque2().procesa(this);
        instr.setSigInstr(etiqueta);
    }

    public void procesa(While instr) {
        instr.setPrimInstr(etiqueta);
        instr.exp().procesa(this);
        etiqAccesoValor(instr.exp());
        etiqueta++;
        instr.bloque().procesa(this);
        etiqueta++;
        instr.setSigInstr(etiqueta);
    }

    public void procesa(Read instr) {
        instr.exp().procesa(this);
        etiqueta += 2;
    }

    public void procesa(Write instr) {
        instr.exp().procesa(this);
        etiqueta += 2;
    }

    public void procesa(NL instr) {
        etiqueta += 2;
    }

    public void procesa(New instr) {
        instr.exp().procesa(this);
        etiqAccesoValor(instr.exp());
        Tipo_puntero tp = (Tipo_puntero) instr.getTipo();
        etiqueta += 2;
    }

    public void procesa(Delete instr) {
        instr.exp().procesa(this);
        etiqAccesoValor(instr.exp());
        Tipo_puntero tp = (Tipo_puntero) instr.getTipo();
        etiqueta++;
    }

    public void procesa(Invoc instr) {
        etiqueta++;
        etiqPasoParams(((Dec_proc)instr.vinculo()).params_form(), instr.params_reales());
        etiqueta++;

    }

    public void procesa(Asignacion exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
        exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
        etiqAsig(exp.opnd1());
    }

    public void procesa(Indexacion exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
        exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
        etiqueta += 3;
    }

    public void procesa(Acceso exp) {
        exp.opnd().procesa(this);
        etiqAccesoValor(exp.opnd());
        etiqueta += 2;
    }

    public void procesa(Indireccion exp) {
        exp.opnd().procesa(this);
        etiqAccesoValor(exp.opnd());
        etiqueta++;
    }
    
    public void procesa(Suma exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
        etiqueta++;
    }

    public void procesa(Resta exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
		etiqueta++;
    }

    public void procesa(Mul exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
		etiqueta++;
    }

    public void procesa(Div exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
		etiqueta++;
    }

    public void procesa(And exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
		etiqueta++;
    }

    public void procesa(Or exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
		etiqueta++;
    }

    public void procesa(Menor_que exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
		etiqueta++;
    }

    public void procesa(Menor_igual exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
		etiqueta++;
    }

    public void procesa(Mayor_que exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
		etiqueta++;
    }

    public void procesa(Mayor_igual exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
		etiqueta++;
    }

    public void procesa(Igual_comp exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
		etiqueta++;
    }

    public void procesa(Distinto_comp exp) {
        exp.opnd0().procesa(this);
        etiqAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        etiqAccesoValor(exp.opnd1());
		etiqueta++;
    }

    public void procesa(Menos_unario exp) {
        exp.opnd().procesa(this);
        etiqAccesoValor(exp.opnd());
		etiqueta++;
    }

    public void procesa(Not exp) {
        exp.opnd().procesa(this);
        etiqAccesoValor(exp.opnd());
		etiqueta++;
    }

    public void procesa(Lit_ent exp) {
        etiqueta++;
    }

    public void procesa(Lit_real exp) {
        etiqueta++;
    }

    public void procesa(True exp) {
        etiqueta++;
    }

    public void procesa(False exp) {
        etiqueta++;
    }

    public void procesa(Lit_cadena exp) {
        etiqueta++;
    }

    public void procesa(Iden exp) {
        etiqAccesoIden(exp.vinculo());
    }

    public void procesa(Null exp) {
        etiqueta++;
    }

    public void procesa(Si_decs decs) {
        decs.ldecs().procesa(this);
    }

    public void procesa(Muchas_decs decs) {
        decs.ldecs().procesa(this);
        recolectaProcs(decs.dec());
    }

    public void procesa(Una_dec dec) {
        recolectaProcs(dec.dec());
    }

    //METODOS AUXILIARES

    public void etiqAccesoValor(Nodo n1) {
        if(SintaxisAbstractaTiny.designador(SintaxisAbstractaTiny.ref(n1))) {
            etiqueta++;
        }
    }

    public void etiqAsig(Nodo n1) {
        etiqueta++;
    }

    public void etiqAccesoIden(Nodo n) {
        if (n instanceof Dec_variable) {
            if( n.getNivel() == 0 ) {
                etiqueta++;
            } else {
                etiqAccesoVariable(n);
            }
        } else if (n instanceof Param_form_normal) {
            etiqAccesoVariable(n);
        } else if (n instanceof Param_form_ref) {
            etiqueta++;
        } else if (n instanceof Dec_proc) {
            etiqAccesoVariable(n);
            etiqueta++;
        }
    }

    public void etiqAccesoVariable(Nodo n) {
        etiqueta += 3;
    }

    public void etiqPasoParams(Params_form pf, Params_reales pr) {
        for(int i = 0; i < pf.numParams(); i++) {
            etiqPasoParams(pf.paramFormPorIndex(i), pr.paramRealPorIndex(i));
        }
    }

    public void etiqPasoParams(Param_form pf, Exp exp) {
        etiqueta += 3;
        exp.procesa(this);
        etiqueta++; 
    }

    public void recolectaProcs(Dec dec) {
        if(dec instanceof Dec_proc) {
            procs.push(dec);
        }
    }


}
