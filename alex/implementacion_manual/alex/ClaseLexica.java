package alex;

public enum ClaseLexica {
    INT("<int>"),
    REAL("<real>"),
	BOOL("<bool>"),
	AND("<and>"),
	OR("<or>"),
	NOT("<not>"),
	TRUE("<true>"),
	FALSE("<false>"),
    IDEN,
	ENT,
	NUM_REAL,
	IGUAL("="),
    MAS("+"),
	MENOS("-"),
	POR("*"),
	DIV("/"),
	EOF("<EOF>"),
    MENORQUE("<"),
	MAYORQUE(">"),
	MENORIGUAL("<="),
	MAYORIGUAL(">="),
    IGUAL_COMP("=="),
	NOIGUAL_COMP("!="),
	PAR_APERTURA("("),
	PAR_CIERRE(")"),
	PUNTOYCOMA(";"),
    LLAVE_APERTURA("{"),
	LLAVE_CIERRE("}"),
	DOBLEAMPERSAND("&&"),
	ARROBA("@");
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
