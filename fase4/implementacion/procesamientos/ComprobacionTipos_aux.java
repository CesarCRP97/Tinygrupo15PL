package procesamientos;

import asint.SintaxisAbstractaTiny.*;

public static class ComprobacionTipos_aux {

    public class Tipo_OK extends Tipo {}
    public class Tipo_ERROR extends Tipo {}

    public static Tipo_OK tipoOK;
    public static Tipo_ERROR tipoERROR;

    public static Tipo_OK getTipoOK() {
        if (tipoOK == null) {
            tipoOK = new Tipo_OK();
        }
        return tipoOK;
    }
    public static Tipo_ERROR getTipoERROR() {
        if (tipoERROR == null) {
            tipoERROR = new Tipo_ERROR();
        }
        return tipoERROR;
    }
    
    public void avisoError(Nodo n) {
        System.out.println("Error de tipos en la línea " + n.leeFila() + " y columna " + n.leeCol() + " en nodo " + n.toString());
        errores = true;
    }

    public String ambosOK(Tipo tipo1, Tipo tipo2) {
        if (tipo1 instanceof Tipo_OK && tipo2 instanceof Tipo_OK) {
            return getTipoOK();
        } else {
            return getTipoERROR();
        }
    }
    private void avisoError(Nodo n, String mensaje) {
        System.out.println("Error de tipos en la línea " + n.leeFila() + " y columna " + n.leeCol() + " en nodo " + n.toString() + ": " + mensaje);
    }

    public Tipo ref(Tipo nodo) {
        if(nodo instanceof Param_form_normal) {
            return ref(((Param_form_normal)nodo).tipo());
        } else if(nodo instanceof Param_form_ref) {
            return ref(((Param_form_ref)nodo).tipo());
        } else if(nodo instanceof Tipo_iden && ((Tipo_iden)nodo).vinculo() instanceof Dec_tipo) {
            return ref(((Dec_tipo)((Tipo_iden)nodo).vinculo()).tipo());
        } else if (nodo instanceof Dec_variable) {
            return ref(((Dec_variable)nodo).tipo());
        } else if (nodo instanceof Campo) {
            return ref(((Campo)nodo).tipo());
        } else {
            return nodo;
        }
    }
    public String tipoDeDec(Nodo n) {
        if (n instanceof Tipo_int) {
            return "int";
        } else if (n instanceof Tipo_real) {
            return "real";
        } else if (n instanceof Tipo_bool) {
            return "bool";
        } else if (n instanceof Tipo_string) {
            return "string";
        } else if (n instanceof Tipo_array) {
            return "array";
        } else if (n instanceof Tipo_struct) {
            return "struct";
        } else if (n instanceof Tipo_puntero) {
            return "puntero";
        } else if (n instanceof Null) {
            return "null";
        } else {
            return "error";
        }
    }

    public boolean compatAsig(Tipo n1, Tipo n2) {
        String tipo1 = tipoDeDec(ref(n1));
        String tipo2 = tipoDeDec(ref(n2));
        if (tipo1 == "error" || tipo2 == "error") {
            return false;
        } else if (tipo1 == "int" && tipo2 == "int") {
            return true;
        } else if (tipo1 == "real" && tipo2 == "real") {
            return true;
        } else if (tipo1 == "bool" && tipo2 == "bool") {
            return true;
        } else if (tipo1 == "string" && tipo2 == "string") {
            return true;
        } else if (tipo1 == "array" && tipo2 == "array") {
            if (compatAsig(n1.tipo(), n2.tipo()) && n1.tam() == n2.tam()) {
                return true;
            } else {
                return false;
            }
        } else if (tipo1 == "struct" && tipo2 == "struct") {
            if (n1.numCampos() == n2.numCampos()) {
                for (int i = 0; i < n1.numCampos(); i++) {
                    if (!compatAsig(n1.campoPorIndex(i).tipo(), n2.campoPorIndex(i).tipo())) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else if (tipo1 == "puntero") {
            if (tipo2 == "null") {
                return true;
            } else if (tipo2 == "puntero") {
                return compatAsig(n1.tipo(), n2.tipo());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean compatAsig(String tipo1, String tipo2) {
        if (tipo1 == "error" || tipo2 == "error") {
            return false;
        } else if (tipo1 == "int" && tipo2 == "int") {
            return true;
        } else if (tipo1 == "real") {
            if (tipo2 == "int" || tipo2 == "real") {
                return true;
            } else {
                return false;
            }
        } else if (tipo1 == "bool" && tipo2 == "bool") {
            return true;
        } else if (tipo1 == "string" && tipo2 == "string") {
            return true;
        } else if (tipo1 == "puntero") {
            if (tipo2 == "null" || tipo2 == "puntero") {
                return true;
            } else {
                return false;
            }
        } else if (tipo1 == "array" && tipo2 == "array") {
            return true;
        } else if (tipo1 == "struct" && tipo2 == "struct") {
            return true;
        } else {
            return false;
        }
    }

    public boolean designador(Nodo n) {
        if (n instanceof Iden || n instanceof Indexacion || n instanceof Acceso || n instanceof Indireccion) {
            return true;
        } else {
            return false;
        }
    }

    public void chequeaTipoExpBinArit(Exp n) {
        String tipo1 = n.opnd0().getTipo();
        String tipo2 = n.opnd1().getTipo();
        if (tipo1 == "error" || tipo2 == "error") {
            n.putTipo("error");
        } else if (tipo1 == "int" && tipo2 == "int") {
            n.putTipo("int");
        } else if (tipo1 == "real" && tipo2 == "real") {
            n.putTipo("real");
        } else if (tipo1 == "int" && tipo2 == "real") {
            n.putTipo("real");
        } else if (tipo1 == "real" && tipo2 == "int") {
            n.putTipo("real");
        } else {
            avisoError(n, "Tipos no compatibles para expresion aritmetica");
            n.putTipo("error");
        }
    }

    public void chequeaTipoExpBinLog(Exp n) {
        String tipo1 = n.opnd0().getTipo();
        String tipo2 = n.opnd1().getTipo();
        if (tipo1 == "error" || tipo2 == "error") {
            n.putTipo("error");
        } else if (tipo1 == "bool" && tipo2 == "bool") {
            n.putTipo("bool");
        } else {
            avisoError(n, "Tipos no compatibles para expresion logica");
            n.putTipo("error");
        }
    }

    public void chequeaTipoExpBinRel(Exp n) {
        String tipo1 = n.opnd0().getTipo();
        String tipo2 = n.opnd1().getTipo();
        if (tipo1 == "error" || tipo2 == "error") {
            n.putTipo("error");
        } else if (tipo1 == "int" && tipo2 == "int") {
            n.putTipo("bool");
        } else if (tipo1 == "real" && tipo2 == "real") {
            n.putTipo("bool");
        } else if (tipo1 == "int" && tipo2 == "real") {
            n.putTipo("bool");
        } else if (tipo1 == "real" && tipo2 == "int") {
            n.putTipo("bool");
        } else if (tipo1 == "bool" && tipo2 == "bool") {
            n.putTipo("bool");
        } else if (tipo1 == "string" && tipo2 == "string") {
            n.putTipo("bool");
        } else {
            avisoError(n, "Tipos no compatibles para expresion relacional");
            n.putTipo("error");
        }
    }

    public void chequeaTipoExpBinRelIgual(Exp n) {
        String tipo1 = n.opnd0().getTipo();
        String tipo2 = n.opnd1().getTipo();
        if (tipo1 == "error" || tipo2 == "error") {
            n.putTipo("error");
        } else if (tipo1 == "int" && tipo2 == "int") {
            n.putTipo("bool");
        } else if (tipo1 == "real" && tipo2 == "real") {
            n.putTipo("bool");
        } else if (tipo1 == "int" && tipo2 == "real") {
            n.putTipo("bool");
        } else if (tipo1 == "real" && tipo2 == "int") {
            n.putTipo("bool");
        } else if (tipo1 == "bool" && tipo2 == "bool") {
            n.putTipo("bool");
        } else if (tipo1 == "string" && tipo2 == "string") {
            n.putTipo("bool");
        } else if (tipo1 == "null" && tipo2 == "null") {
            n.putTipo("bool");
        } else if (tipo1 == "puntero" && tipo2 == "puntero") {
            n.putTipo("bool");
        } else if (tipo1 == "puntero" && tipo2 == "null") {
            n.putTipo("bool");
        } else if (tipo1 == "null" && tipo2 == "puntero") {
            n.putTipo("bool");
        } else {
            avisoError(n, "Tipos no compatibles para expresion relacional(== o !=)");
            n.putTipo("error");
        }
    }

    public boolean compParams(Params_reales params_reales, Params_form params_form) {
        for(int i = 0; i < params_form.numParams(); i++) {
            if (!compatAsig(params_form.paramFormPorIndex(i).getTipo(), params_reales.paramRealPorIndex(i).getTipo())) {
                System.out.println("Tipos no compatibles: " + params_reales.paramRealPorIndex(i).getTipo() + " y " + params_form.paramFormPorIndex(i).getTipo());
                return false;
            }
        }
        return true;
    }

}
