package procesamientos;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;

import maquinap.MaquinaP.*;

public class GeneracionCod_vis extends ProcesamientoDef {
    
    private MaquinaP m;

    public GeneracionCod_vis() {
        this.maquinaP = new MaquinaP();
    }

    public void procesa(Asig exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        //TODO
    }

    public void procesa(Indexacion exp) {
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        m.emit(m.apila_int(exp.opnd0().getTipo().tipo().getTam()));
        m.emit(m.mul());
        m.emit(m.suma());
    }

    public void procesa(Acceso exp) {
        exp.opnd().procesa(this);
        //TODO
    }
    
    public void procesa(Suma exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        m.emit(m.suma());
    }

    public void procesa(Resta exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        m.emit(m.resta());
    }

    public void procesa(Mul exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        m.emit(m.mul());
    }

    public void procesa(Div exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        m.emit(m.div());
    }

    public void procesa(And exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        m.emit(m.and());
    }

    public void procesa(Or exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        m.emit(m.or());
    }

    public void procesa(Menor exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        m.emit(m.menor());
    }

    public void procesa(Menor_igual exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        m.emit(m.menor_igual());
    }

    public void procesa(Mayor exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        m.emit(m.mayor());
    }

    public void procesa(Mayor_igual exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        m.emit(m.mayor_igual());
    }

    public void procesa(Igual_comp exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        m.emit(m.igualdad());
    }

    public void procesa(Distinto_comp exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        m.emit(m.desigualdad());
    }

    public void procesa(Menos_unario exp) {
        exp.arg().procesa(this);
        m.emit(m.menos_unario());
    }

    public void procesa(Not exp) {
        exp.arg().procesa(this);
        m.emit(m.not());
    }

    public void procesa(Lit_ent exp) {
        m.emit(m.apila_int(exp.valor()));
    }

    public void procesa(Lit_real exp) {
        m.emit(m.apila_real(exp.valor()));
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

}
