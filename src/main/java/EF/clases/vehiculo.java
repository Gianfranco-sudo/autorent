
package EF.clases;
import EF.accesoBD.conexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class vehiculo {
    private int chasis;
    private String descripcion;
    private String placa;
     private String marca;
    private String modelo;
    private String color;

    public int getChasis() {
        return chasis;
    }

    public void setChasis(int chasis) {
        this.chasis = chasis;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    //Constructor
    
    public vehiculo(){
        cnn = new conexionBD();
        sentencia = null;
        
        
    }
    //Insertar Resitros
    
   private conexionBD cnn = null;
   private PreparedStatement sentencia;
   
   public void InsertarDatos(String descripcion,String placa,String marca,String modelo){
       int resp = 0;
       String SQL_INSERT = "INSERT INTO vehiculo(chasis,descripcion,placa,marca,modelo) VALUES(?,?,?,?,?)";
        try{
            sentencia = cnn.Conectar().prepareStatement(SQL_INSERT);
            sentencia.setString(2, descripcion);
            sentencia.setString(3,placa);
            sentencia.setString(4, marca);
            sentencia.setString(5, modelo);
            
     }catch(SQLException e){
         JOptionPane.showMessageDialog(null,"Error al Grabar en la Base de Datos" + e.getMessage());
     }
         


 } finally {
            sentencia = null;
            cnn.Desconectar();
        }
        
        return resp;
    }
    
    //Lista los productos
    private DefaultTableModel modelo;
    private ResultSet registros;
    
    private DefaultTableModel CrearTitulos() {
        modelo = new DefaultTableModel();
        modelo.addColumn("chasis");
        modelo.addColumn("Descripción");
        modelo.addColumn("placa");
        modelo.addColumn("marca");
        modelo.addColumn("modelo");
        return modelo;
    }
    
    public DefaultTableModel obtenerDatos() {
        String SQL_SELECT ="Select * from productos";
                
        try{
            CrearTitulos();
            sentencia = cnn.Conectar().prepareStatement(SQL_SELECT);
            registros = sentencia.executeQuery();
            Object[] fila = new Object[5];
            while(registros.next()) {
                fila[0] = registros.getInt(1);
                fila[1] = registros.getString(2);
                fila[2] = registros.getString(3);
                fila[3] = registros.getString(4);   
                fila[4] = registros.getString(5);

                modelo.addRow(fila);
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar la BD:" + e.getMessage());
        } finally {
            sentencia = null;
            registros = null;
            cnn.Desconectar();
        }
        return modelo;
    }
    
    //Actualizar productos
    public int ActualizarDatos(int idprod, String desc, String categ, int exist) {
        int resp = 0;
        /*String SQL_UPDATE = "UPDATE productos SET descripcion = '"+ desc +"',categoria= '"+ categ +"',existencias= "+ exist +" WHERE idproducto = " + idprod;*/
        String SQL_UPDATE = "UPDATE productos SET descripcion = '"+ desc +"', "
                + "categoria= '"+ categ +"',existencias= "+ exist +" "
                + " WHERE idproducto = " + idprod;
        
        try{
            sentencia = cnn.Conectar().prepareStatement(SQL_UPDATE);
            resp = sentencia.executeUpdate();
            if (resp>0)
                JOptionPane.showMessageDialog(null, "Registro actualizado");
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar la BD " + e.getMessage());
        } finally {
            sentencia = null;
            cnn.Desconectar();
        }
        
        return resp;
    }
    
        //Actualizar productos
    public int EliminarDatos(int idprod) {
        int resp = 0;
        String SQL_BORRAR = "DELETE from productos WHERE idproducto = " + idprod;
        
        try{
            sentencia = cnn.Conectar().prepareStatement(SQL_BORRAR);
            resp = sentencia.executeUpdate();
            if (resp>0)
                JOptionPane.showMessageDialog(null, "Registro eliminado");
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar en la BD " + e.getMessage());
        } finally {
            sentencia = null;
            cnn.Desconectar();
        }
        
        return resp;
    }
    

