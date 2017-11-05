package controlo;

import java.util.ArrayList;

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
       
        for(int i = 0; i < this.numeroDeVariaveis; i++){
            this.matriz = retornarMatrizComPivot(this.matriz, i);
        }
        
        this.imprimirMatriz(matriz);
    }
    
    public ArrayList<ArrayList<Double>> retornarMatrizComPivot(ArrayList<ArrayList<Double>> matriz,int coluna){
        int linhaDoPivot = this.retornarLinhaDoPivot(matriz, coluna);
        
        ArrayList<Double> auxParaTroca = new ArrayList();
        auxParaTroca = matriz.get(coluna);
        matriz.set(coluna, matriz.get(linhaDoPivot));
        return this.matriz = retornarZeroAbaixo(matriz, coluna);
       // return null;
    }
    
    
    public int retornarLinhaDoPivot(ArrayList<ArrayList<Double>> matriz,int coluna){
        int linha = 0;
        double maior = 0;
        for(int i = 0; i < this.numeroDeVariaveis; i++){
            if(Math.abs(matriz.get(i).get(coluna)) > maior){
                maior = matriz.get(i).get(coluna);
                linha = i;
            }
        }
        return linha;
    }
    
    public ArrayList<ArrayList<Double>> retornarZeroAbaixo(ArrayList<ArrayList<Double>> matriz,int coluna){
        ArrayList<Double> auxMultiplicado  = new ArrayList();
        for(int i = coluna+1; i < this.numeroDeVariaveis; i++ ){
            double multiplicador = this.retornarMuliplicadorDaLinha(matriz,coluna, i);
            
            for(int j = 0; j < this.numeroDeVariaveis; j++){
                auxMultiplicado.add(matriz.get(i).get(j) - multiplicador*matriz.get(coluna).get(coluna));
                matriz.set(i, auxMultiplicado);
            }
            
        }
        return matriz;
    }
    
    private double retornarMuliplicadorDaLinha(ArrayList<ArrayList<Double>> matriz,int linha,int coluna){                
        return matriz.get(linha).get(coluna) / matriz.get(linha-1).get(coluna);
    }
    
    private void imprimirMatriz(ArrayList<ArrayList<Double>> matriz){
        for(int i=0;i < this.numeroDeVariaveis; i++){
            for(int j=0;j < this.numeroDeVariaveis; i++){
                System.out.print(matriz.get(i).get(j)+ " | ");
            }
            System.out.println("");
        }
        
        
    }
}
