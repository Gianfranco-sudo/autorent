import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistrarAlquiler {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("Alquileres.txt", true))) {
            pw.println("45678901:B2C-458:2025-05-09:2025-05-18");
            pw.println("78945612:AZO-123:2025-05-10:2025-05-15");
            System.out.println("Alquileres registrados.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
