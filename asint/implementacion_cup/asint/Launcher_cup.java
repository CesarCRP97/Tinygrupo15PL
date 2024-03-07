package implementacion_cup.asint;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import implementacion_cup.alex.AnalizadorLexicoTiny;

public class Launcher_cup {

	public static void launch(String file)throws Exception  {
		Reader input = new InputStreamReader(new FileInputStream(file));
		AnalizadorLexicoTiny al=new AnalizadorLexicoTiny(input);
		AnalizadorSintacticoTiny aS=new AnalizadorSintacticoTiny(al);
		aS.parse();
	}

}  
