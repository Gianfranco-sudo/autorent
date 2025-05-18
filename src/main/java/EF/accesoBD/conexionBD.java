
package EF.accesoBD;
   import java.sql.Connection;
   import java.sql.DriverManager;
   import java.sql.SQLException;

public class conexionBD {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USUARIO = "root";
    private static final String CLAVE = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/autorent";
    
    private Connection cnn;
    public conexionBD(){
        cnn = null;
    
    }
    public Connection Conectar(){
        try{
            Class.forName(DRIVER);
            cnn = (Connection)DriverManager.getConnection(URL,USUARIO,CLAVE);
        }catch(Exception e){
            System.out.println("Error:" + e.getMessage());
            System.exit(0);
        }
        return cnn;
    }
    public void Desconectar(){
        try{
            cnn.close();
        }catch(SQLException e){
            System.out.println("Error al cerrar conexion: " + e.getMessage());
        }
    }
    
}
