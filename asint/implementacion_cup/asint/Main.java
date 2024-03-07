package implementacion_cup.asint;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import implementacion_cup.alex.AnalizadorLexicoTiny;

//String[] asd= { "-destdir", "src/c_ast_ascendente","-parser" ,"ContructorAst" ,"-symbols", "ClaseLexica","ConstructorAST.cup"};
//java_cup.Main.main(asd);
//java -jar cup.jar -parser AnalizadorSintacticoTiny -symbols ClaseLexica Tiny.cup
public class Main {

	public static void main(String[] args)throws Exception  {
		// TODO Auto-generated method stub
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		AnalizadorLexicoTiny al=new AnalizadorLexicoTiny(input);
		AnalizadorSintacticoTiny aS=new AnalizadorSintacticoTiny(al);
		aS.parse();
	}

}  
