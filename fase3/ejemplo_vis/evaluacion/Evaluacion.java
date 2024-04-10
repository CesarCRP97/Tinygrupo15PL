package evaluacion;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaEval.Exp;
import asint.SintaxisAbstractaEval.Dec;
import asint.SintaxisAbstractaEval.Muchas_decs;
import asint.SintaxisAbstractaEval.Una_dec;
import asint.SintaxisAbstractaEval.Si_decs;
import asint.SintaxisAbstractaEval.Suma;
import asint.SintaxisAbstractaEval.Resta;
import asint.SintaxisAbstractaEval.Mul;
import asint.SintaxisAbstractaEval.Div;
import asint.SintaxisAbstractaEval.Lit_ent;
import asint.SintaxisAbstractaEval.Lit_real;
import asint.SintaxisAbstractaEval.Iden;
import asint.SintaxisAbstractaEval.Prog;
import java.util.HashMap;
import java.util.Map;

public class Evaluacion extends ProcesamientoDef {
    private Map<String,Float> env;
     // para almacenar el resultado de la funcion
     // de procesamiento 'eval'
    private float resul; 
    public Evaluacion() {
        env = new HashMap<>();
    }
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
    public float leeResul() {return resul;} 
    public void procesa(Prog prog) { 
        prog.decs().procesa(this);
        prog.exp().procesa(this);
    }
    public void procesa(Si_decs decs) {
        decs.decs().procesa(this);
    }
    public void procesa(Muchas_decs decs) {
        decs.ldecs().procesa(this);
        decs.dec().procesa(this);
    }
    public void procesa(Una_dec decs) {
        decs.dec().procesa(this);
    }
    public void procesa(Dec dec) {
        if(env.containsKey(dec.iden())) {
            throw new ECteDuplicada("Cte duplicada: "+dec.iden()+
                                     " fila:"+dec.leeFila()+" col:"+dec.leeCol()); 
        }
        else {
            dec.exp().procesa(this);
            env.put(dec.iden(),resul);
        }
    }
    public void procesa(Iden id) {
       if(! env.containsKey(id.iden())) {
                throw new ECteNoDefinida("Cte indefinida: "+id.iden()+
                                        " fila:"+id.leeFila()+" col:"+id.leeCol()); 
       }
       else {
           resul = env.get(id.iden());
       }
    }
    
    public void procesa(Lit_ent num) {
         resul = Float.valueOf(num.num()).floatValue();
    }    
    public void procesa(Lit_real num) {
         resul = Float.valueOf(num.num()).floatValue();
    }    
    public void procesa(Suma exp) {
        exp.opnd0().procesa(this);
        float val_opnd0 = resul;
        exp.opnd1().procesa(this);
        resul =  val_opnd0 + resul;
    }    
    public void procesa(Resta exp) {
        exp.opnd0().procesa(this);
        float val_opnd0 = resul;
        exp.opnd1().procesa(this);
        resul =  val_opnd0 - resul;
    }    
    public void procesa(Mul exp) {
        exp.opnd0().procesa(this);
        float val_opnd0 = resul;
        exp.opnd1().procesa(this);
        resul =  val_opnd0 * resul;
    }    
    public void procesa(Div exp) {
        exp.opnd0().procesa(this);
        float val_opnd0 = resul;
        exp.opnd1().procesa(this);
        resul =  val_opnd0 / resul;
    }    
}
