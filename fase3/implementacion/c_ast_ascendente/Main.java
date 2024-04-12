package c_ast_ascendente;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
   public static void main(String[] args) throws Exception {
         Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	 ConstructorASTTiny asint = new ConstructorASTTiny(alex);
	 //asint.setScanner(alex);
	 System.out.println(asint.parse().value);
 }
}   
   
