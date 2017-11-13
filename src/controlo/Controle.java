package controlo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import modelo.ModeloDaTabela;


/**
 *
 * @author Alfredo Sebastiao
 * @author Ercilio Marques
 * @version 1.0
 */
public class Controle {
    private ArrayList<ArrayList<Double>> matriz;
    private ArrayList<ArrayList<Double>> matrizEscalonada;
    private ArrayList<ArrayList<Double>> matrizParaTabela;
    int numeroDeVariaveis = 3;
    String textoAImprimir = "X1 \t| X2 \t| X3 \t| BI \t| Equacao\t|\n";
   
    
    
    
    public Map<String,Double> receberMatriz(ArrayList<ArrayList<Double>> matriz){
        this.matriz = new ArrayList<>();
        this.matrizParaTabela = new ArrayList<>();
        this.matriz = matriz;
        this.numeroDeVariaveis = this.matriz.size();
   //     this.preencherTabela(matriz, table);
//        for(int j =0; j < this.numeroDeVariaveis; j++){
//            this.matrizParaTabela.add(matriz.get(j));
//        }
//        this.matrizParaTabela.addAll(matriz);
        this.imprimirMatriz(matriz);
        
        for(int i = 0; i < this.numeroDeVariaveis; i++){
            this.matriz = retornarMatrizComPivot(this.matriz, i);
        }

       // this.preencherTabela(this.matrizParaTabela, table);
       
        
        return  this.retornarResultados(matriz);
        
        
    }
    
    public ArrayList<ArrayList<Double>> retornarMatrizComPivot(ArrayList<ArrayList<Double>> matriz,int coluna){
        int linhaDoPivot = this.retornarLinhaDoPivot(matriz, coluna);
        
        ArrayList<Double> auxParaTroca = new ArrayList();
        auxParaTroca = matriz.get(coluna);
        matriz.set(coluna, matriz.get(linhaDoPivot));
        matriz.set(linhaDoPivot, auxParaTroca);
        
        return retornarZeroAbaixo(matriz, coluna);
    
    }
    
    
    public int retornarLinhaDoPivot(ArrayList<ArrayList<Double>> matriz,int coluna){
        int linha = 0;
        double maior = 0;
        for(int i = coluna; i < this.numeroDeVariaveis; i++){
            if(Math.abs(matriz.get(i).get(coluna)) > maior){
                maior =Math.abs( matriz.get(i).get(coluna));
                linha = i;
            }
        }
       
        return linha;
    }
    
    public ArrayList<ArrayList<Double>> retornarZeroAbaixo(ArrayList<ArrayList<Double>> matriz,int coluna){
        ArrayList<Double> auxMultiplicado;
        
        for(int i = coluna+1; i < this.numeroDeVariaveis; i++ ){
            auxMultiplicado  = new ArrayList();
            double multiplicador = this.retornarMuliplicadorDaLinha(matriz,coluna, i);
    
            for(int j = 0; j <= this.numeroDeVariaveis; j++){    
               
                auxMultiplicado.add(matriz.get(i).get(j) - multiplicador*matriz.get(coluna).get(j));   
            }
           
            matriz.set(i, auxMultiplicado);
             
        }
        
       
        
     this.matrizParaTabela.addAll(matriz);
        this.imprimirMatriz(matriz);
        return matriz;
    }
    
    private double retornarMuliplicadorDaLinha(ArrayList<ArrayList<Double>> matriz,int coluna,int linha){                
        return matriz.get(linha).get(coluna) / matriz.get(coluna).get(coluna);
    }
    
    private void imprimirMatriz(ArrayList<ArrayList<Double>> matriz){
        
        for(int i=0;i < this.numeroDeVariaveis; i++){
            for(int j=0;j <= this.numeroDeVariaveis; j++){
                this.textoAImprimir += String.format("%.2f",matriz.get(i).get(j))+ " \t| ";
                System.out.print(matriz.get(i).get(j)+ " | ");
            }
             this.textoAImprimir += "\n";
            System.out.println("");
        }
        this.textoAImprimir += "================================================================================== \n";
        System.out.println("==============================================");
        
    }
    
    private void imprimirMatrizA(ArrayList<ArrayList> matriz){
        System.out.println("=aaaaaaaaaaaaaaaaa=============================================");
        for(int i=0;i < this.numeroDeVariaveis; i++){
            for(int j=0;j <= this.numeroDeVariaveis; j++){
                this.textoAImprimir += matriz.get(i).get(j)+ " | ";
                System.out.print(matriz.get(i).get(j)+ " | ");
            }
            this.textoAImprimir += this.textoAImprimir+"\n";
            System.out.println("");
        }
        System.out.println("===aaaaaaaaaaaa===========================================");
        
    }
    
    public Map<String,Double> retornarResultados(ArrayList<ArrayList<Double>> matriz){
        Map<String,Double> mapa = new HashMap<>();
        double x = 0;
        double somaDosAnteriores = 0;
        for(int i = this.numeroDeVariaveis-1; i >= 0;i--){
                       
            somaDosAnteriores = matriz.get(i).get(this.numeroDeVariaveis);

            for(int j = this.numeroDeVariaveis-1; j >= 0; j--){
              
                
               if(i == j){
                   mapa.put("X"+(i+1), somaDosAnteriores/matriz.get(i).get(i));
                   matriz.get(i).set(this.numeroDeVariaveis, somaDosAnteriores/matriz.get(i).get(i));
                   break;
                 
               }
               else{
                    somaDosAnteriores = somaDosAnteriores - (matriz.get(i).get(j) * matriz.get(j).get(this.numeroDeVariaveis));
              
               }
               
            }
            
        }
        
        return mapa;
    }
    
    private ModeloDaTabela modeloDaTabela;
    public JTable preencherTabela(ArrayList matriz, JTable tabela){
        JOptionPane.showMessageDialog(null,"fui chamado");
        modeloDaTabela = new ModeloDaTabela(matriz);
        tabela.setModel(modeloDaTabela);
        tabela.revalidate();
        return tabela;
    }
    
    public JTextArea imprimirNaTA(JTextArea ta){
       ta.setText(textoAImprimir);
       
       return ta;
    }
}