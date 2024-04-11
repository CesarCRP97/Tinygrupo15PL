package c_ast_descendente;

public class ConstructorASTsTinyDJ extends ConstructorASTsTiny {
    public ConstructorASTsTinyDJ(java.io.InputStream is) {
        super(is);
        disable_tracing();
    }

    public ConstructorASTsTinyDJ(java.io.Reader r) {
        super(r);
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
