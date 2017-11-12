package controlo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
    
    public void receberMatriz(ArrayList<ArrayList<Double>> matriz, ArrayList<Double> Results){
        this.matriz = new ArrayList<>();
        this.matriz = matriz;
        this.numeroDeVariaveis = this.matriz.size();
        System.out.println("matriz original");
        this.imprimirMatriz(matriz);
        for(int i = 0; i < this.numeroDeVariaveis; i++){
            
            this.matriz = retornarMatrizComPivot(this.matriz, i);
        }
        
        System.out.println("Ultima impressao");
        this.imprimirMatriz(this.matriz);
        
        System.out.println("RESULT");
        System.out.println(this.retornarResultados(matriz).toString());
        
        
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
            for(int j = 0; j <= this.numeroDeVariaveis; j++){    
               
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
            for(int j=0;j <= this.numeroDeVariaveis; j++){
                System.out.print(matriz.get(i).get(j)+ " | ");
            }
            System.out.println("");
        }
        System.out.println("==============================================");
        
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
}
