package Controller;

import BaseData.ConnectionBaseData;
import Model.Product;
import View.GuiShop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controller implements ActionListener {

    GuiShop gui;
    Product product;
    ConnectionBaseData data;

    public Controller(GuiShop gui, Product product) {
        this.gui = gui;
        this.product = product;
        this.gui.añadir.addActionListener(this);
        this.gui.actualizar.addActionListener(this);
        this.gui.mostrar.addActionListener(this);
        this.gui.eliminar.addActionListener(this);
        data = new ConnectionBaseData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String id = gui.campoID.getText();
        String nombre = gui.campoNombre.getText();
        int precio = 0;
        int cantidad = 0;
        
        if (e.getSource() == gui.añadir) {
            try {
                precio = Integer.parseInt(gui.campoPrecio.getText());
                cantidad = Integer.parseInt(gui.campoCantidad.getText());

            } catch (Exception ex) {
                System.err.println("Error Inesperado: "+ex);
            }
            if (precio > 0 && cantidad > 0) {
                System.out.println("Datos: " + id + " " + nombre + " " + cantidad + " " + precio);
                product.setId(id);
                product.setNombre(nombre);
                product.setPrecio(precio);
                product.setCantidad(cantidad);
                data.createProduct(product);
            }
                data.closeConnection();
        }

        if (e.getSource() == gui.eliminar) {
           String idEliminar = JOptionPane.showInputDialog(null, "Ingrese el id del producto: ");
           data.delete(idEliminar);
           data.closeConnection();
        }
        
        if(e.getSource() == gui.mostrar){
           //String idBuscar = JOptionPane.showInputDialog(null, "Ingrese el id del producto: ");
           data.show();
           data.closeConnection();
        }
        
        if(e.getSource() == gui.actualizar){
            try {
                precio = Integer.parseInt(gui.campoPrecio.getText());
                cantidad = Integer.parseInt(gui.campoCantidad.getText());

            } catch (Exception ex) {
                System.err.println("Error Inesperado: "+ex);
            }
            Product productUpdate = new Product(id,nombre,precio,cantidad);
            data.update(productUpdate);
            data.closeConnection();
        }

    }

}
