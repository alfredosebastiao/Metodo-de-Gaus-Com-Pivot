package controlo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;

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
    String textoAImprimir = "";
    private String nrDeCasas;
   
    
    /**
     *  Metodo usado para receber a matriz q sera calculada
     * @param matriz  - matriz a calcuar
     * @return Map<String, Double> map de resultados
     */
    public Map<String,Double> receberMatriz(ArrayList<ArrayList<Double>> matriz, String nrDeCasasDecimais){
        this.matriz = new ArrayList<>();
        this.matrizParaTabela = new ArrayList<>();
        this.matriz = matriz;
        this.numeroDeVariaveis = this.matriz.size();
        this.nrDeCasas = nrDeCasasDecimais;
        
        this.preencherVarsNaList();
        this.imprimirMatriz(matriz);
        
        
        
        for(int i = 0; i < this.numeroDeVariaveis; i++){
            this.matriz = retornarMatrizComPivot(this.matriz, i);
        }
     
        return  this.retornarResultados(matriz);
    }
    
    /**
     * Metodo usado para pegar pegar a linha linha do pivot e de seguida fazer a permutacao de linhas! colocando acima a linha do pivot
     * @param matriz - matriz dos valores
     * @param coluna - coluna com a qual estamos a trabalhar
     * @return matriz com zeros abaixo do pivot
     */
    public ArrayList<ArrayList<Double>> retornarMatrizComPivot(ArrayList<ArrayList<Double>> matriz,int coluna){
        int linhaDoPivot = this.retornarLinhaDoPivot(matriz, coluna);
        
        ArrayList<Double> auxParaTroca = new ArrayList();
        auxParaTroca = matriz.get(coluna);
        matriz.set(coluna, matriz.get(linhaDoPivot));
        matriz.set(linhaDoPivot, auxParaTroca);
        
        /**
         * Para q possa imprimir a matriz depois de fazer a permutacao de linhas
         */
        this.imprimirMatriz(matriz);
        
        return retornarZeroAbaixo(matriz, coluna);
    }
    
    
    /**
     * Metodo usado para achar a linha do pivot! para de seguida fazer  a permutacao no metodo retornarMatrizComPivot()
     * @param matriz - matriz onde deve encontrar o pivot
     * @param coluna - coluna com a qual estamos a trabalhar
     * @return 
     * @see retornarMatrizComPivot()
     */
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
    
    /**
     * Metodo usado para zerar as linhas abaixo do pivot
     * @param matriz - matriz
     * @param coluna - coluna com a qual estamos a trabalhar
     * @return - matriz com zeros abaixo do pivot
     */
    public ArrayList<ArrayList<Double>> retornarZeroAbaixo(ArrayList<ArrayList<Double>> matriz,int coluna){
        ArrayList<Double> auxMultiplicado;
        
        for(int i = coluna+1; i < this.numeroDeVariaveis; i++ ){
            auxMultiplicado  = new ArrayList();
            double multiplicador = this.retornarMuliplicadorDaLinha(matriz,coluna, i);
    
            for(int j = 0; j <= this.numeroDeVariaveis; j++){    
               
                auxMultiplicado.add(matriz.get(i).get(j) - multiplicador*matriz.get(coluna).get(j));   
            }
         
            auxMultiplicado.add(i+0.0);
            auxMultiplicado.add(multiplicador+0.0);
            auxMultiplicado.add(coluna+1.0);
            matriz.set(i, auxMultiplicado);
             
        }
        
        /**
         * Para imprimir a matriz apos  ser zerada as linhas abaixo do pivot
         */
        this.imprimirMatriz(matriz);
        return matriz;
    }
    
    /**
     * Metodo usado para encontrar o multiplicador de uma linha abaixo do pivot
     * @param matriz - matriz com a qual trabalhamos
     * @param coluna - coluna
     * @param linha linha em q qeremos multiplicar
     * @return 
     */
    private double retornarMuliplicadorDaLinha(ArrayList<ArrayList<Double>> matriz,int coluna,int linha){                
        return matriz.get(linha).get(coluna) / matriz.get(coluna).get(coluna);
    }
    
    /**
     * Metodo chamado para concatenar a string do texto a imprimir na textArea
     * @param matriz 
     */
    private void imprimirMatriz(ArrayList<ArrayList<Double>> matriz){
        
        for(int i=0;i < this.numeroDeVariaveis; i++){
            for(int j=0;j <= this.numeroDeVariaveis; j++){
                this.textoAImprimir += String.format("%."+this.nrDeCasas+"f",matriz.get(i).get(j))+ " \t| ";
                System.out.print(matriz.get(i).get(j)+ " | ");
            }
           
            this.textoAImprimir += "L'"+(i+1)+" = L"+(i+1)
                    +" - ("+String.format("%."+this.nrDeCasas+"f",this.matriz.get(i).get(this.matriz.get(i).size()-2))+") * L"+matriz.get(i).get(this.matriz.get(i).size()-1);
            this.textoAImprimir += "\n";
            System.out.println("");
        }
        this.textoAImprimir += "=================================================================================================== \n";
        System.out.println("==============================================");
        
    }
    
    /**
     * Metodo usado para efectuar a retrosubstituicao e armazenar o resultado num hashMap
     * @param matriz - matriz
     * @return mapa de resultados
     */
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
    
    /**
     * metodo usado para imprimir o texto na textArea
     * @param ta
     * @return 
     */
    public JTextArea imprimirNaTA(JTextArea ta){
       ta.setText(textoAImprimir);
       
       return ta;
    }

    /**
     * Metodo usado para preencher a string texto a imprimir, de acordo com o nr de variaveis que existem na matriz 
     * - preenche apenas a primeira linha  do texto
     */
    private void preencherVarsNaList() {
        
        for(int i =0; i <= this.numeroDeVariaveis; i++){
            if(i != this.numeroDeVariaveis)
                this.textoAImprimir += "X"+(i+1)+ " \t |";
            else
                this.textoAImprimir += "bi \t |";
        }
        
        this.textoAImprimir += " Equacao \t\t\t| \n";
       
    }
}