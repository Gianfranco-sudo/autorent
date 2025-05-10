
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistrarCliente {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("Clientes.txt", true))) {
            pw.println("Juan Pérez:35:S:45678901");
            pw.println("María Rojas:29:C:78945612");
            System.out.println("Clientes registrados.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

