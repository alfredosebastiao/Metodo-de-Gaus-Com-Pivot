/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModeloDaTabela extends AbstractTableModel {
    private ArrayList cadaLinha = new ArrayList();
    private ArrayList  matriz = new ArrayList();
    String[] colunas =  {"X1" ,"X2","X3","Bi","equacao","Pivot",};
    public ModeloDaTabela( ArrayList matriz) {
        this.setMatriz(matriz);
    }

    public ModeloDaTabela() {
    }

    

    
 
    @Override
    public boolean isCellEditable(int i, int i1) {
       
        
        return false;
    }

    @Override
    public String getColumnName(int i) {
        return colunas[i]; //To change body of generated methods, choose Tools | Templates.
    }
    
   
    
   

    @Override
    public int getRowCount() {
        return matriz.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         
    
        
        cadaLinha = (ArrayList) matriz.get(rowIndex);
      
       
       
        
        switch(columnIndex){
            case 0: return cadaLinha.get(0);
            case 1: return cadaLinha.get(1);
            case 2: return cadaLinha.get(2);
            case 3: return cadaLinha.get(3);
            case 4: return cadaLinha.size();
            case 5: return "----------";
        }
        
       
        return null;
        
        
    }
    

    @Override
    public void setValueAt(Object value, int linha, int coluna) {
        
    }
    
    
    
    public List getMembro() {
        return matriz;
    }

    public void setMatriz( ArrayList matriz) {
        this.matriz = matriz;
        
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
}
