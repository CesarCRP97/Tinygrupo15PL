package alex;

public enum ClaseLexica {
    INT("<int>"),
    REAL("<real>"),
    BOOL("<bool>"),
    STRING("<string>"),
    AND("<and>"),
    OR("<or>"),
    NOT("<not>"),
    NULL("<null>"),
    TRUE("<true>"),
    FALSE("<false>"),
    PROC("<proc>"),
    IF("<if>"),
    ELSE("<else>"),
    WHILE("<while>"),
    STRUCT("<struct>"),
    NEW("<new>"),
    DELETE("<delete>"),
    READ("<read>"),
    WRITE("<write>"),
    NL("<nl>"),
    TYPE("<type>"),
    CALL("<call>"),
    SUMA("+"),
    RESTA("-"),
    MULT("*"),
    DIV("/"),
    PORCENTAJE("%"),
    MENORQUE("<"),
    MAYORQUE(">"),
    MENORIGUAL("<="),
    MAYORIGUAL(">="),
    IGUAL_COMP("=="),
    NOIGUAL_COMP("!="),
    PAR_A("("),
    PAR_C(")"),
    PUNTOYCOMA(";"),
    IGUAL_OP("="),
    CORCH_A("["),
    CORCH_C("]"),
    PUNTO("."),
    CAPIROTE("^"),
    COMA(","),
    LLAVE_A("{"),
    LLAVE_C("}"),
    AMPERSAND("&"),
    DOBLEAMPERSAND("&&"),
    ARROBA("@"),
    LITENTERO,
    IDEN,
    LITCADENA,
    LITREAL,
    EOF("EOF");
    private String image;
    public String getImage() {
        return image;
    }
    private ClaseLexica() {
        image = toString();
    }
    private ClaseLexica(String image) {
        this.image = image;
    }
}
