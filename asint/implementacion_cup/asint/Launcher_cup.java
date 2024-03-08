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
        try {
            aS.parse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("OK");

	}

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Uso: java implementacion_cup.asint.Launcher_cup <fichero>");
            return;
        }
        launch(args[0]);
    }
}  
