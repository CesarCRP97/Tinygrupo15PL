package asint;



public class ClaseSemanticaTiny extends SintaxisAbstractaTiny {
    public ClaseSemanticaTiny() {
        super();
    }
    public Exp mkop(String op, Exp opnd1, Exp opnd2) {
        switch(op) {
            case "=": return asignacion(opnd1,opnd2);
            case "==": return igual_comp(opnd1,opnd2);
            case "!=": return distinto_comp(opnd1,opnd2);
            case "<": return menor_que(opnd1,opnd2);
            case ">": return mayor_que(opnd1,opnd2);
            case "<=": return menor_igual(opnd1,opnd2);
            case ">=": return mayor_igual(opnd1,opnd2);
            case "+": return suma(opnd1,opnd2);
            case "-": return resta(opnd1,opnd2);
            case "and": return and(opnd1,opnd2);
            case "or": return or(opnd1,opnd2);
            case "*": return mul(opnd1,opnd2);
            case "/": return div(opnd1,opnd2);
            case "%": return mod(opnd1,opnd2);
            default: throw new UnsupportedOperationException("Bad op");
        }
    }
}
