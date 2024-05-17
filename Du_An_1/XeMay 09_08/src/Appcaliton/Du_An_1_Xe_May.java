/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Appcaliton;

import View.View_DangNhap;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author VietTien
 */
public class Du_An_1_Xe_May {
    public static void main(String[] args) {
      
          try {  
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new View_DangNhap().setVisible(true);
            }
        });
    }
}
