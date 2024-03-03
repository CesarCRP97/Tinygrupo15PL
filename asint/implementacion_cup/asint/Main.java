package asint;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
//String[] asd= { "-destdir", "src/c_ast_ascendente","-parser" ,"ContructorAst" ,"-symbols", "ClaseLexica","ConstructorAST.cup"};
//java_cup.Main.main(asd);
//java -jar cup.jar -parser AnalizadorSintacticoTiny -symbols ClaseLexica Tiny.cup
public class Main {

	public static void main(String[] args)throws Exception  {
		// TODO Auto-generated method stub
		Reader input = new InputStreamReader(new FileInputStream("input.txt"));
		AnalizadorLexicoTiny al=new AnalizadorLexicoTiny(input);
		AnalizadorSintacticoTiny aS=new AnalizadorSintacticoTiny(al);
		aS.parse();
	}

}  
