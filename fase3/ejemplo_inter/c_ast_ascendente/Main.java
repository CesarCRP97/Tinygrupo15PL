package c_ast_ascendente;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
   public static void main(String[] args) throws Exception {
         Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoEval alex = new AnalizadorLexicoEval(input);
	 ConstructorASTEval asint = new ConstructorASTEval(alex);
	 //asint.setScanner(alex);
	 System.out.println(asint.parse().value);
 }
}   
   
