package Controller;

import Controller.Controller;
import Model.Product;
import View.GuiShop;

public class ShopMain {

    public static void main(String[] args) {
        GuiShop gui = new GuiShop();
        Product product = new Product();
        Controller c = new Controller(gui,product);
        gui.setVisible(true);
        
    }

    

}
