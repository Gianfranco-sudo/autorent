  import java.io.FileWriter;
  import java.io.IOException;
  import java.io.PrintWriter;

public class GuardarVehiculos {
     public static void main(String[]args){
        try(PrintWriter pw = new PrintWriter(new FileWriter("Vehiculos.txt", true))){
          pw.println("B2C-458:Yaris:Toyota:2019:true");
          pw.println("AZO-123:Ford Territory:2023:false");
          System.out.println("Vehiculos guardados exitosamente");
        }catch(IOException e){
          e.printStackTrace();
          
          
        }
          
          
  }
    
    
    
}
