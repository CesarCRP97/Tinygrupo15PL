package asint;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

public class AnalizadorSintacticoTinyDJ extends AnalizadorSintacticoTiny {

    public AnalizadorSintacticoTinyDJ(Scanner al) {
        super(al);
    }

    public void debug_message(String message) {}

    public void debug_shift(Symbol token) {
        System.out.println(token.value);
    }
}
