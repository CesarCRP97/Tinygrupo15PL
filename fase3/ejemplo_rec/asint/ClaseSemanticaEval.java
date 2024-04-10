package asint;



public class ClaseSemanticaEval extends SintaxisAbstractaEval {
    public ClaseSemanticaEval() {
        super();
    }
    public Exp mkop(String op, Exp opnd1, Exp opnd2) {
        switch(op) {
            case "+": return suma(opnd1,opnd2);
            case "-": return resta(opnd1,opnd2);
            case "*": return mul(opnd1,opnd2);
            case "/": return div(opnd1,opnd2);
            default: throw new UnsupportedOperationException("Bad op");
        }
    }
}
