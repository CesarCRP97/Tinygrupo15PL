package impresion;

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

public class Impresion extends ProcesamientoDef {
    private void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {System.out.print("(");};
        opnd.procesa(this);
        if(opnd.prioridad() < np) {System.out.print(")");};        
    }
    private void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1) {
        imprimeOpnd(opnd0,np0);
        System.out.print(" "+op+" ");
        imprimeOpnd(opnd1,np1);
    }
    public void procesa(Prog prog) {
         System.out.println("evalua");
         System.out.print("  ");
         prog.exp().procesa(this);
         System.out.println();
         prog.decs().procesa(this);
         System.out.println();
    }   
    public void procesa(Iden exp) {
         System.out.print(exp.iden());
    }    
    public void procesa(Lit_ent exp) {
         System.out.print(exp.num());
    }    
    public void procesa(Lit_real exp) {
         System.out.print(exp.num());
    }    
    public void procesa(Suma exp) {
        imprimeExpBin(exp.opnd0(),"+", exp.opnd1(),0,1);
    }    
    public void procesa(Resta exp) {
        imprimeExpBin(exp.opnd0(),"-", exp.opnd1(),0,1);
    }    
    public void procesa(Mul exp) {
        imprimeExpBin(exp.opnd0(),"*", exp.opnd1(),0,1);
    }    
    public void procesa(Div exp) {
        imprimeExpBin(exp.opnd0(),"/", exp.opnd1(),0,1);
    }    
    public void procesa(Si_decs decs) {
        System.out.print("donde");
        decs.decs().procesa(this);
    }    
    public void procesa(Muchas_decs decs) {
        decs.ldecs().procesa(this);
        System.out.print(",");
        decs.dec().procesa(this);
    }    
    public void procesa(Una_dec decs) {
        decs.dec().procesa(this);
    }    
    public void procesa(Dec dec) {

        System.out.println();
         System.out.print("  "+dec.iden()+"=");
         dec.exp().procesa(this);
    }
}
