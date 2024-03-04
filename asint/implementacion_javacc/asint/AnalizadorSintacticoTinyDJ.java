package asint;

public class AnalizadorSintacticoTinyDJ extends AnalizadorSintacticoTiny {

    public AnalizadorSintacticoTinyDJ(java.io.InputStream stream) {
        super(stream);
        disable_tracing();
    }

    @Override
    private void trace_token(Token t, String where) {
        if((t.kind > 11 && t.kind < 33) || t.kind == 0) {
            System.out.println("<" + t.image + ">");
        }
        else {
            System.out.println(t.image);
        }
    } 

}
