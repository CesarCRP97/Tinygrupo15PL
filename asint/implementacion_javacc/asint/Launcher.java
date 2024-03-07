package implementacion_javacc.asint;
import java.io.FileReader;
public class Launcher{
   public static void launch(String file) throws Exception {
      AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(new FileReader(file));
	  asint.analiza();
      System.out.println("OK");
   }
}
