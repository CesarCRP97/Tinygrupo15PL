package procesamientos;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.*;

import maquinap.MaquinaP.*;

public class GeneracionCod_vis extends ProcesamientoDef {
    
    private MaquinaP maquinaP;

    public GeneracionCod_vis() {
        this.maquinaP = new MaquinaP();
    }

    public void emit(Instruccion ins) {
        maquinaP.emit(ins);
    }


    public void procesa(Asig exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        emit(new DesapilaDir(exp.arg1().vinculo().dir()));
    }
    
    public void procesa(Suma exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        if(exp instanceof Tipo_int) {
            emit(new ISumaInt());
        } else if (exp instanceof Tipo_real){
            emit(new ISumaReal());
        }
    }

    public void procesa(Resta exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        if(exp instanceof Tipo_int) {
            emit(new IRestaInt());
        } else {
            emit(new IRestaReal());
        }
    }

    public void procesa(Mul exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        if(exp instanceof Tipo_int) {
            emit(new IMulInt());
        } else {
            emit(new IMulReal());
        }
    }

    public void procesa(Div exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        if(exp instanceof Tipo_int) {
            emit(new IDivInt());
        } else {
            emit(new IDivReal());
        }
    }

    public void procesa(And exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        emit(new IAnd());
    }

    public void procesa(Or exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        emit(new IOr());
    }

    public void procesa(Menor exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        if(exp instanceof Tipo_int) {
            emit(new IMenor());
        }
    }

    public void procesa(Menor_igual exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        if(exp instanceof Tipo_int) {
            emit(new IMenorIgual());
        }
    }

    public void procesa(Mayor exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        if(exp instanceof Tipo_int) {
            emit(new IMayor());
        }
    }

    public void procesa(Mayor_igual exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        if(exp instanceof Tipo_int) {
            emit(new IMayorIgual());
        }
    }

    public void procesa(Igual_comp exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        emit(new IIgualdad());
    }

    public void procesa(Distinto_comp exp) {
        exp.arg1().procesa(this);
        exp.arg2().procesa(this);
        emit(new IDesigualdad());
    }

    public void procesa(Menos_unario exp) {
        exp.arg().procesa(this);
        emit(new IMenosUnario());
    }

    public void procesa(Not exp) {
        exp.arg().procesa(this);
        emit(new INot());
    }

    public void procesa(Lit_ent exp) {
        emit(new IApilaInt(exp.valor()));
    }

    public void procesa(Lit_real exp) {
        emit(new IApilaReal(exp.valor()));
    }

    public void procesa(True exp) {
        emit(new IApilaBool(true));
    }

    public void procesa(False exp) {
        emit(new IApilaBool(false));
    }

    public void procesa(Lit_cadena exp) {
        emit(new IApilaString(exp.valor()));
    }

    public void procesa(Null exp) {
        emit(new IApilaNull());
    }

}
