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

public interface Procesamiento {
    void procesa(Dec dec);
    void procesa(Muchas_decs decs);
    void procesa(Una_dec decs);
    void procesa(Si_decs decs);
    void procesa(No_decs decs);
    void procesa(Suma exp);
    void procesa(Resta exp);
    void procesa(Mul exp);
    void procesa(Div exp);
    void procesa(Lit_ent exp);
    void procesa(Lit_real exp);
    void procesa(Iden exp);
    void procesa(Prog prog);    
}
