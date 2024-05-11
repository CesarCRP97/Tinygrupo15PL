package procesamientos;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny;
import asint.SintaxisAbstractaTiny.*;

import maquinap.MaquinaP;

import java.util.Stack;

public class GeneracionCod_vis extends ProcesamientoDef {
    
    private MaquinaP m;
    private Stack<Dec> procs;

    public GeneracionCod_vis(MaquinaP m) {
        this.m = m;
        this.procs = new Stack<Dec>();
    }

    public void procesa(Prog prog) {
        prog.bloque().procesa(this);
        while (!procs.empty()) {
            Dec_proc proc = (Dec_proc) procs.pop();
            genProc(proc);
        }
    }

    public void procesa(Bloque bloque) {
        bloque.decs().procesa(this);
        bloque.insts().procesa(this);
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
        m.emit(m.desecha());
    }

    public void procesa(If instr) {
        instr.exp().procesa(this);
        genAccesoValor(instr.exp());
        m.emit(m.ir_f(instr.getSigInstr()));
        instr.bloque().procesa(this);
    }

    public void procesa(IfElse instr) {
        instr.exp().procesa(this);
        genAccesoValor(instr.exp());
        m.emit(m.ir_f(instr.bloque2().getPrimInstr()));
        instr.bloque1().procesa(this);
        m.emit(m.ir_a(instr.getSigInstr()));
        instr.bloque2().procesa(this);
    }

    public void procesa(While instr) {
        instr.exp().procesa(this);
        genAccesoValor(instr.exp());
        m.emit(m.ir_f(instr.getSigInstr()));
        instr.bloque().procesa(this);
        m.emit(m.ir_a(instr.getPrimInstr()));
    }

    public void procesa(Read instr) {
        instr.exp().procesa(this);
        if(instr.exp().getTipo() instanceof Tipo_int) {
            m.emit(m.read_int());
        } else if(instr.exp().getTipo() instanceof Tipo_real) {
            m.emit(m.read_real());
        } else if(instr.exp().getTipo() instanceof Tipo_string) {
            m.emit(m.read_string());
        }
        m.emit(m.desapila_ind());
    }

    public void procesa(Write instr) {
        instr.exp().procesa(this);
        genAccesoValor(instr.exp());
        m.emit(m.write());
    }

    public void procesa(NL instr) {
        m.emit(m.apila_string("\n"));
        m.emit(m.write());
    }

    public void procesa(New instr) {
        instr.exp().procesa(this);
        Tipo_puntero tp = (Tipo_puntero) instr.exp().getTipo();
        m.emit(m.alloc(tp.getTipo().getTam()));
        m.emit(m.desapila_ind());
    }

    public void procesa(Delete instr) {
        instr.exp().procesa(this);
        genAccesoValor(instr.exp());
        Tipo_puntero tp = (Tipo_puntero) instr.exp().getTipo();
        m.emit(m.dealloc(tp.getTipo().getTam()));
    }

    public void procesa(Invoc instr) {
        m.emit(m.activa(instr.vinculo().getNivel(), instr.vinculo().getTam(), instr.getSigInstr()));
        genPasoParams(((Dec_proc)instr.vinculo()).params_form(), instr.params_reales());
        m.emit(m.ir_a(instr.vinculo().getPrimInstr()));
    }

    public void procesa(Asignacion exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        if(exp.opnd0().getTipo() instanceof Tipo_int && exp.opnd1().getTipo() instanceof Tipo_real) {
            genAccesoValor(exp.opnd1());
            m.emit(m.convierte_a_int());
            m.emit(m.desapila_ind());
        } else {
            genAsig(exp.opnd1());
        }
        exp.opnd0().procesa(this);
    }

    public void procesa(Indexacion exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
        m.emit(m.apila_int(exp.opnd0().getTipo().tipo().getTam()));
        m.emit(m.mul());
        m.emit(m.suma());
    }

    public void procesa(Acceso exp) {
        exp.opnd().procesa(this);
        m.emit(m.apila_int(exp.opnd().getTipo().campoPorIden(exp.iden()).getDesp()));
        m.emit(m.suma());
    }

    public void procesa(Indireccion exp) {
        exp.opnd().procesa(this);
        m.emit(m.apila_ind());
    }
    
    public void procesa(Suma exp) {
        exp.opnd0().procesa(this);
        genAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
		m.emit(m.suma());
    }

    public void procesa(Resta exp) {
        exp.opnd0().procesa(this);
        genAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
		m.emit(m.resta());
    }

    public void procesa(Mul exp) {
        exp.opnd0().procesa(this);
        genAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
		m.emit(m.mul());
    }

    public void procesa(Div exp) {
        exp.opnd0().procesa(this);
        genAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
		m.emit(m.div());
    }

    public void procesa(And exp) {
        exp.opnd0().procesa(this);
        genAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
		m.emit(m.and());
    }

    public void procesa(Or exp) {
        exp.opnd0().procesa(this);
        genAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
		m.emit(m.or());
    }

    public void procesa(Menor_que exp) {
        exp.opnd0().procesa(this);
        genAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
		m.emit(m.menor());
    }

    public void procesa(Menor_igual exp) {
        exp.opnd0().procesa(this);
        genAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
		m.emit(m.menor_igual());
    }

    public void procesa(Mayor_que exp) {
        exp.opnd0().procesa(this);
        genAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
		m.emit(m.mayor());
    }

    public void procesa(Mayor_igual exp) {
        exp.opnd0().procesa(this);
        genAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
		m.emit(m.mayor_igual());
    }

    public void procesa(Igual_comp exp) {
        exp.opnd0().procesa(this);
        genAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
		m.emit(m.igualdad());
    }

    public void procesa(Distinto_comp exp) {
        exp.opnd0().procesa(this);
        genAccesoValor(exp.opnd0());
		exp.opnd1().procesa(this);
        genAccesoValor(exp.opnd1());
		m.emit(m.desigualdad());
    }

    public void procesa(Menos_unario exp) {
        exp.opnd().procesa(this);
        genAccesoValor(exp.opnd());
		m.emit(m.menos_unario());
    }

    public void procesa(Not exp) {
        exp.opnd().procesa(this);
        genAccesoValor(exp.opnd());
		m.emit(m.not());
    }

    public void procesa(Lit_ent exp) {
        m.emit(m.apila_int(Integer.parseInt(exp.valor())));
    }

    public void procesa(Lit_real exp) {
        m.emit(m.apila_real(Double.parseDouble(exp.valor())));
    }

    public void procesa(True exp) {
        m.emit(m.apila_bool(true));
    }

    public void procesa(False exp) {
        m.emit(m.apila_bool(false));
    }

    public void procesa(Lit_cadena exp) {
        m.emit(m.apila_string(exp.valor()));
    }

    public void procesa(Iden exp) {
        if(exp.vinculo().getNivel() == 0) {
            m.emit(m.apila_int(exp.vinculo().getDir()));
        } else {
            m.emit(m.apilad(exp.vinculo().getNivel()));
            m.emit(m.apila_int(exp.vinculo().getDir()));
            m.emit(m.suma());
            if(exp.vinculo() instanceof Param_form_ref) {
                m.emit(m.apila_ind());
            }
        }
    }

    public void procesa(Null exp) {
        m.emit(m.apila_null());
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

    public void genAccesoValor(Nodo n1) {
        if(SintaxisAbstractaTiny.designador(n1)) {
            m.emit(m.apila_ind());
        }
    }

    public void genAsig(Nodo n1) {
        if(SintaxisAbstractaTiny.designador(n1)) {
            m.emit(m.copia(n1.getTipo().getTam()));
        } else {
            m.emit(m.desapila_ind());
        }
    }

    public void genPasoParams(Params_form pf, Params_reales pr) {
        for(int i = 0; i < pf.numParams(); i++) {
            genPasoParams(pf.paramFormPorIndex(i), pr.paramRealPorIndex(i));
        }
    }

    public void genPasoParams(Param_form pf, Exp exp) {
        m.emit(m.dup());
        m.emit(m.apila_int(pf.getDir()));
        m.emit(m.suma());
        exp.procesa(this);
        if(pf instanceof Param_form_normal && (SintaxisAbstractaTiny.designador(exp))) {
            m.emit(m.copia(pf.getTipo().getTam()));
        } else {
            m.emit(m.desapila_ind());
        }
    }

    public void genProc(Dec_proc proc) {
            m.emit(m.desapilad(proc.getNivel()));
            proc.bloque().procesa(this);
            m.emit(m.desactiva(proc.getNivel(), proc.getTam()));
            m.emit(m.ir_ind());
    }

    public void recolectaProcs(Dec dec) {
        if(dec instanceof Dec_proc) {
            procs.push(dec);
        }
    }


}
