package controlo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alfredo Sebastiao
 * @author Ercilio Marques
 * @version 1.0
 */
public class Controle {
    ArrayList<ArrayList<Double>> matriz;
    ArrayList<ArrayList<Double>> matrizEscalonada;
    int numeroDeVariaveis = 3;
    
    public void receberMatriz(ArrayList<ArrayList<Double>> matriz){
        this.matriz = new ArrayList<>();
        this.matriz = matriz;
        this.numeroDeVariaveis = this.matriz.size();
        System.out.println("matriz original");
        this.imprimirMatriz(matriz);
        for(int i = 0; i < this.numeroDeVariaveis; i++){
            
            this.matriz = retornarMatrizComPivot(this.matriz, i);
        }
         System.out.println("Ultima impressao");
        this.imprimirMatriz(matriz);
    }
    
    public ArrayList<ArrayList<Double>> retornarMatrizComPivot(ArrayList<ArrayList<Double>> matriz,int coluna){
        int linhaDoPivot = this.retornarLinhaDoPivot(matriz, coluna);
        
        ArrayList<Double> auxParaTroca = new ArrayList();
        auxParaTroca = matriz.get(coluna);
        matriz.set(coluna, matriz.get(linhaDoPivot));
        matriz.set(linhaDoPivot, auxParaTroca);
         System.out.println("pivot passa para cima");
        imprimirMatriz(matriz);
        return retornarZeroAbaixo(matriz, coluna);
       // return null;
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
            System.out.println("multiplicador: "+multiplicador);
            for(int j = 0; j < this.numeroDeVariaveis; j++){    
               
                auxMultiplicado.add(matriz.get(i).get(j) - multiplicador*matriz.get(coluna).get(j));   
            }
            System.out.println(auxMultiplicado.toString());
            matriz.set(i, auxMultiplicado);
             
        }
        
        System.out.println("linha multiplicada!!");
        this.imprimirMatriz(matriz);
        return matriz;
    }
    
    private double retornarMuliplicadorDaLinha(ArrayList<ArrayList<Double>> matriz,int coluna,int linha){                
        return matriz.get(linha).get(coluna) / matriz.get(coluna).get(coluna);
    }
    
    private void imprimirMatriz(ArrayList<ArrayList<Double>> matriz){
        
        for(int i=0;i < this.numeroDeVariaveis; i++){
            for(int j=0;j < this.numeroDeVariaveis; j++){
                System.out.print(matriz.get(i).get(j)+ " | ");
            }
            System.out.println("");
        }
        System.out.println("==============================================");
        
    }
}
