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
    private int stock;

    private conexionBD cnn;
    private PreparedStatement sentencia;
    private ResultSet registros;
    private DefaultTableModel modeloTabla;

    // Constructor
    public vehiculo() {
        cnn = new conexionBD();
        sentencia = null;
    }

    // Getters y setters
    public int getChasis() { return chasis; }
    public void setChasis(int chasis) { this.chasis = chasis; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }



    // Insertar registro
    public int insertarDatos(String descripcion, String placa, String marca, String modelo, int Stock) {
        int resp = 0;
        String SQL_INSERT = "INSERT INTO vehiculo (descripcion, placa, marca, modelo, ) VALUES (?, ?, ?, ?, ?)";

        try {
            sentencia = cnn.Conectar().prepareStatement(SQL_INSERT);
            sentencia.setString(1, descripcion);
            sentencia.setString(2, placa);
            sentencia.setString(3, marca);
            sentencia.setString(4, modelo);
            sentencia.setInt(5, stock);

            resp = sentencia.executeUpdate();
            if (resp > 0) {
                JOptionPane.showMessageDialog(null, "Vehículo registrado exitosamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al grabar en la base de datos: " + e.getMessage());
        } finally {
            sentencia = null;
            cnn.Desconectar();
        }

        return resp;
    }

    // Crear modelo de tabla
    private DefaultTableModel crearTitulos() {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Chasis");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Placa");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Modelo");
        modeloTabla.addColumn("Stock");
        return modeloTabla;
    }

    // Listar registros
    public DefaultTableModel obtenerDatos() {
        String SQL_SELECT = "SELECT * FROM vehiculo";

        try {
            crearTitulos();
            sentencia = cnn.Conectar().prepareStatement(SQL_SELECT);
            registros = sentencia.executeQuery();

            Object[] fila = new Object[5];
            while (registros.next()) {
                fila[0] = registros.getInt("chasis");
                fila[1] = registros.getString("descripcion");
                fila[2] = registros.getString("placa");
                fila[3] = registros.getString("marca");
                fila[4] = registros.getString("modelo");
                modeloTabla.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar vehículos: " + e.getMessage());
        } finally {
            sentencia = null;
            registros = null;
            cnn.Desconectar();
        }

        return modeloTabla;
    }

    // Actualizar registro
    public int actualizarDatos(int chasis, String descripcion, String placa, String marca, String modelo, int stock) {
        int resp = 0;
        String SQL_UPDATE = "UPDATE vehiculo SET descripcion = ?, placa = ?, marca = ?, modelo = ? WHERE chasis = ?";

        try {
            sentencia = cnn.Conectar().prepareStatement(SQL_UPDATE);
            sentencia.setInt(1, chasis);
            sentencia.setString(2, descripcion);
            sentencia.setString(3, placa);
            sentencia.setString(4, marca);
            sentencia.setString(5, modelo);
            sentencia.setInt(6, stock);
            

            resp = sentencia.executeUpdate();
            if (resp > 0) {
                JOptionPane.showMessageDialog(null, "Vehículo actualizado correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar vehículo: " + e.getMessage());
        } finally {
            sentencia = null;
            cnn.Desconectar();
        }

        return resp;
    }

    // Eliminar registro
    public int eliminarDatos(int chasis) {
        int resp = 0;
        String SQL_DELETE = "DELETE FROM vehiculo WHERE chasis = ?";

        try {
            sentencia = cnn.Conectar().prepareStatement(SQL_DELETE);
            sentencia.setInt(1, chasis);

            resp = sentencia.executeUpdate();
            if (resp > 0) {
                JOptionPane.showMessageDialog(null, "Vehículo eliminado correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar vehículo: " + e.getMessage());
        } finally {
            sentencia = null;
            cnn.Desconectar();
        }

        return resp;
    }

    public int ActualizarDatos(int chasis, String descripcion, String marca, int stock) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int InsertarDatos(String descripcion, String marca, int stock) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

