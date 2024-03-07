import implementacion_javacc.asint.Launcher_javacc;
import implementacion_cup.asint.Launcher_cup;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("No hay suficientes argumentos");
            return;
        } 
        String archivo = args[0];
        String op = args[1];
        try{
            if (op.equals("asc")) {
                implementacion_javacc.asint.Launcher_javacc.launch(archivo);
            } else if (op.equals("desc")) {
                implementacion_cup.asint.Launcher_cup.launch(archivo);
            } else {
                System.out.println("Operación no válida");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
