package procesamientos;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny;
import asint.SintaxisAbstractaTiny.*;

import maquinap.MaquinaP;

public class GeneracionCod_vis extends ProcesamientoDef {
    
    private MaquinaP m;

    public GeneracionCod_vis(int tamdatos, int tampila, int tamheap, int ndisplays) {
        this.m = new MaquinaP(tamdatos, tampila, tamheap, ndisplays);
    }

    public void procesa(Prog prog) {
        prog.bloque().procesa(this);
    }

    public void procesa(Bloque bloque) {
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
        gen_acc_val(instr.exp());
    }

    public void procesa(If instr) {
        instr.exp().procesa(this);
        gen_acc_val(instr.exp());
        m.emit(m.ir_f(instr.getSig()));
        instr.bloque().procesa(this);
    }

    public void procesa(IfElse instr) {
        instr.exp().procesa(this);
        gen_acc_val(instr.exp());
        m.emit(m.ir_f(instr.bloque0().getSig()));//A checkear
        instr.bloque0().procesa(this);
        m.emit(m.ir_a(instr.getSig()));
        instr.bloque1().procesa(this);
    }

    public void procesa(While instr) {
        instr.exp().procesa(this);
        gen_acc_val(instr.exp());
        m.emit(m.ir_f(instr.getSig()));
        instr.bloque().procesa(this);
        m.emit(m.ir_a(instr.getPrim()));
    }

    public void procesa(Read instr) {
        instr.exp().procesa(this);
        gen_acc_val(instr.exp());
        //TODO
    }

    public void procesa(Write instr) {
        instr.exp().procesa(this);
        gen_acc_val(instr.exp());
        //TODO
    }

    public void procesa(NL instr) {
        //TODO
    }

    public void procesa(New instr) {
        instr.exp().procesa(this);
        gen_acc_val(instr.exp());
        Tipo_puntero tp = (Tipo_puntero) instr.getTipo();
        m.emit(m.alloc(tp.getTipo().getTam()));
        m.emit(m.desapila_ind());
    }

    public void procesa(Delete instr) {
        instr.exp().procesa(this);
        gen_acc_val(instr.exp());
        Tipo_puntero tp = (Tipo_puntero) instr.getTipo();
        m.emit(m.dealloc(tp.getTipo().getTam()));
    }

    public void procesa(Invoc instr) {
        instr.exp().procesa(this);
        gen_acc_val(instr.exp());
        m.emit(m.ir_a(instr.getEtiqueta()));
    }

    public void procesa(Asignacion exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
        exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
        gen_asignacion(exp.opnd1());
    }

    public void procesa(Indexacion exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
        exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
        m.emit(m.apila_int(exp.opnd0().getTipo().tipo().getTam()));
        m.emit(m.mul());
        m.emit(m.suma());
    }

    public void procesa(Acceso exp) {
        exp.opnd().procesa(this);
        gen_acc_val(exp.opnd());
        m.emit(m.apila_int(exp.opnd().getTipo().campoPorIden(exp.iden()).getDesp()));
        m.emit(m.suma());
    }

    public void procesa(Indireccion exp) {
        exp.opnd().procesa(this);
        gen_acc_val(exp.opnd());
        m.emit(m.apila_ind());
    }
    
    public void procesa(Suma exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
		exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
		m.emit(m.suma());
    }

    public void procesa(Resta exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
		exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
		m.emit(m.resta());
    }

    public void procesa(Mul exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
		exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
		m.emit(m.mul());
    }

    public void procesa(Div exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
		exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
		m.emit(m.div());
    }

    public void procesa(And exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
		exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
		m.emit(m.and());
    }

    public void procesa(Or exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
		exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
		m.emit(m.or());
    }

    public void procesa(Menor_que exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
		exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
		m.emit(m.menor());
    }

    public void procesa(Menor_igual exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
		exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
		m.emit(m.menor_igual());
    }

    public void procesa(Mayor_que exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
		exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
		m.emit(m.mayor());
    }

    public void procesa(Mayor_igual exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
		exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
		m.emit(m.mayor_igual());
    }

    public void procesa(Igual_comp exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
		exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
		m.emit(m.igualdad());
    }

    public void procesa(Distinto_comp exp) {
        exp.opnd0().procesa(this);
        gen_acc_val(exp.opnd0());
		exp.opnd1().procesa(this);
        gen_acc_val(exp.opnd1());
		m.emit(m.desigualdad());
    }

    public void procesa(Menos_unario exp) {
        exp.opnd().procesa(this);
        gen_acc_val(exp.opnd());
		m.emit(m.menos_unario());
    }

    public void procesa(Not exp) {
        exp.opnd().procesa(this);
        gen_acc_val(exp.opnd());
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

    public void procesa(Null exp) {
        m.emit(m.apila_null());
    }


    public void gen_acc_val(Nodo n1) {
        if(SintaxisAbstractaTiny.designador(SintaxisAbstractaTiny.ref(n1))) {
            m.emit(m.apila_ind());
        }
    }

    public void gen_asignacion(Nodo n1) {
        if(SintaxisAbstractaTiny.designador(SintaxisAbstractaTiny.ref(n1))) {
            m.emit(m.copia(n1.getTipo().getTam()));
        } else {
            m.emit(m.desapila_ind());
        }
    }
}
