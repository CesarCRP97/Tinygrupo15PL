package asint;

import asint.SintaxisAbstractaEval.Dec;
import asint.SintaxisAbstractaEval.Muchas_decs;
import asint.SintaxisAbstractaEval.Una_dec;
import asint.SintaxisAbstractaEval.Si_decs;
import asint.SintaxisAbstractaEval.No_decs;
import asint.SintaxisAbstractaEval.Suma;
import asint.SintaxisAbstractaEval.Resta;
import asint.SintaxisAbstractaEval.Mul;
import asint.SintaxisAbstractaEval.Div;
import asint.SintaxisAbstractaEval.Lit_ent;
import asint.SintaxisAbstractaEval.Lit_real;
import asint.SintaxisAbstractaEval.Iden;
import asint.SintaxisAbstractaEval.Prog;


public class ProcesamientoDef implements Procesamiento {
    public void procesa(Dec dec) {}
    public void procesa(Muchas_decs decs) {}
    public void procesa(Una_dec decs) {}
    public void procesa(Si_decs decs) {}
    public void procesa(No_decs decs) {}
    public void procesa(Suma exp) {}
    public void procesa(Resta exp) {}
    public void procesa(Mul exp) {}
    public void procesa(Div exp) {}
    public void procesa(Lit_ent exp) {}
    public void procesa(Lit_real exp) {}
    public void procesa(Iden exp) {}
    public void procesa(Prog prog) {}
}
