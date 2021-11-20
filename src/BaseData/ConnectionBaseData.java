package BaseData;

import Model.Product;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConnectionBaseData {

    Connection conex;
     //Product product;
    public void connection() {
        try {
            conex = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TallerMVC", "postgres", "admin");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBaseData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            conex.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBaseData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createProduct(Product product) {
        connection();
        try {
            Statement st = conex.createStatement();
            String createProduct = "INSERT INTO public.tienda(\n"
                    + "	id, nombre, precio, cantidad)\n"
                    + "	VALUES ('" + product.getId() + "','" + product.getNombre() + "'," + product.getPrecio() + "," + product.getCantidad() + ");";
            st.executeUpdate(createProduct);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBaseData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void show() {
        connection();
        try {
            Statement st = conex.createStatement();
            String search = "SELECT id, nombre, precio, cantidad\n"
                    + "	FROM public.tienda;";
            ResultSet r = st.executeQuery(search);
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Presio");
            modeloTabla.addColumn("Cantidad");
            while (r.next()) {
                Object fila[] = new Object[4];
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = r.getObject(i + 1);
                }
                modeloTabla.addRow(fila);
            }
            JTable tabla = new JTable(modeloTabla);
            JScrollPane scroll = new JScrollPane(tabla);
            JOptionPane.showMessageDialog(null, scroll);

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBaseData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(String id) {
        connection();
        try {
            Statement st = conex.createStatement();
            String delete = "DELETE FROM public.tienda\n"
                    + "	WHERE id = '" + id + "';";
            st.executeUpdate(delete);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBaseData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void update(Product product){
       
        connection();
        try {
            Statement st = conex.createStatement();
            String update = "UPDATE public.tienda\n" +
"	SET nombre='"+product.getNombre()+"', precio='"+product.getPrecio()+"', cantidad='"+product.getCantidad()+"'\n" +
"	WHERE id = '"+product.getId()+"'";
            st.executeUpdate(update);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBaseData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
