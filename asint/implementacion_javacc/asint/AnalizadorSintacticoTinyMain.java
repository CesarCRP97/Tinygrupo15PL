package implementacion_javacc.asint;

public class AnalizadorSintacticoTinyMain extends AnalizadorSintacticoTiny {

    public AnalizadorSintacticoTinyMain(java.io.InputStream stream) {
        super(stream);
        disable_tracing();
    }

    public AnalizadorSintacticoTinyMain(java.io.Reader stream) {
        super(stream);
        disable_tracing();
    }

}
