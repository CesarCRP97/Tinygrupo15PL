package evaluador;

import asint.SintaxisAbstractaEval;
import java.util.HashMap;
import java.util.Map;

public class Evaluador extends SintaxisAbstractaEval {
    public class ECteNoDefinida extends RuntimeException {
        public ECteNoDefinida(String msg) {
            super(msg);
        }
    }
    public class ECteDuplicada extends RuntimeException {
        public ECteDuplicada(String msg) {
            super(msg);
        }
    }
    private Map<String,Float> env;
    public Evaluador() {
        this.env = new HashMap<>();
    }
    public float evalua(Prog n) {
        consEnv(n.decs());
        return eval(n.exp());        
    }
    private void consEnv(Decs decs) {
        if(claseDe(decs,Si_decs.class)) {
           consEnv(decs.ldecs());        
        }
    }
    private void consEnv(LDecs decs) {
        if(claseDe(decs,Muchas_decs.class)) {
            consEnv(decs.ldecs());
        }
        consEnv(decs.dec());
    }
    private void consEnv(Dec dec) {
        if(env.containsKey(dec.iden())) {
            throw new ECteDuplicada("Cte duplicada: "+dec.iden()+
                                     " fila:"+dec.leeFila()+" col:"+dec.leeCol()); 
        }
        else {
            env.put(dec.iden(),eval(dec.exp()));
        }
    }
    private float eval(Exp exp) {
        if(claseDe(exp,Lit_ent.class) || claseDe(exp,Lit_real.class)) {
            return Float.valueOf(exp.valor()).floatValue();
        }
        else if(claseDe(exp,Iden.class)) {
            if(! env.containsKey(exp.iden())) {
                throw new ECteNoDefinida("Cte indefinida: "+exp.iden()+
                                        " fila:"+exp.leeFila()+" col:"+exp.leeCol()); 
            }
            else {
                return env.get(exp.iden());
            }
        }
        else {
            float v1 = eval(exp.opnd0());
            float v2 = eval(exp.opnd1());
            if(claseDe(exp,Suma.class)) {
                return v1+v2;
            }
            else if(claseDe(exp,Resta.class)) {
                return v1-v2;
            }
            else if(claseDe(exp,Mul.class)) {
                return v1*v2;
            }
            else {
                return v1/v2;
            }
        }
    } 
    
    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    }    
}
