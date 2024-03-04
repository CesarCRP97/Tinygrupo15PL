package asint;

public class AnalizadorSintacticoTinyDJ extends AnalizadorSintacticoTiny {

    public AnalizadorSintacticoTinyDJ(java.io.InputStream stream) {
        super(stream);
        disable_tracing();
    }

    @Override
    protected void trace_token(Token t, String where) {
        if((t.kind > 10 && t.kind < 33)) {
            System.out.println("<" + t.image + ">");
        } else if (t.kind == 0) {
            System.out.println("<EOF>");
        }
        else {
            System.out.println(t.image);
        }
    } 

}
