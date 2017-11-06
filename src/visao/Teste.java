/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;
import controlo.Controle;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Controle c = new Controle();
       ArrayList<ArrayList<Double>> matriz = new ArrayList();
        
       ArrayList<Double> a = new ArrayList();
       
       a.add(1.0);
       a.add(-1.0);
       a.add(2.0);
       a.add(2.0);
       matriz.add(a);
       a = new ArrayList();
       a.add(2.0);
       a.add(2.0);
       a.add(-1.0);
       a.add(0.0);
       matriz.add(a);
       a = new ArrayList();
       a.add(-2.0);
       a.add(-5.0);
       a.add(3.0);
       a.add(3.0);
       
       matriz.add(a);
          
        ArrayList<Double> results = new ArrayList();
       results.add(0.0);
       results.add(2.0);
       results.add(3.0);
       c.receberMatriz(matriz, results);
      
    }
    
}
