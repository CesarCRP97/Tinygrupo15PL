package implementacion_javacc.asint;
import java.io.FileReader;
public class Launcher_javacc{
   public static void launch(String file) throws Exception {
      AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(new FileReader(file));
	  asint.analiza();
      System.out.println("OK");
   }

   public static void main(String[] args) throws Exception {
      if (args.length != 1) {
         System.err.println("Uso: java Launcher_javacc <fichero>");
         System.exit(1);
      }
      launch(args[0]);
   }
}
