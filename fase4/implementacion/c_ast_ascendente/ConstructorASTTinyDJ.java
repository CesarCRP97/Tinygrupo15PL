package c_ast_ascendente;

import java_cup.runtime.*;
import c_ast_ascendente.UnidadLexica.StringLocalizado;

public class ConstructorASTTinyDJ extends ConstructorASTTiny {
    public ConstructorASTTinyDJ(Scanner al) {
        super(al);
    }

    public void debug_message(String message) {
    }

    public void debug_shift(Symbol token) {
        System.out.println(((StringLocalizado)token.value).str());

    }
}
