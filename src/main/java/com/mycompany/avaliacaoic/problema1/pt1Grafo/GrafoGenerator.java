package com.mycompany.avaliacaoic.problema1.pt1Grafo;


// Java program to Generate a Random Undirected Graph

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

//Gera um grafo onde todos os vertices estão direta ou indiretamente conectados
public class GrafoGenerator {
    static int maxVertex = 4;
    
    public GrafoGenerator(int numNodos)
    {
        this.maxVertex = numNodos;
    }
    
    // Function to generate random graph
    public void generateRandomGraphs(int e, String nomeGrafo) throws FileNotFoundException, UnsupportedEncodingException
    {
        int i = 0, j = 0, count = 0;
        boolean allVConnected;
        boolean hamiltonian  =true; //habilita garantia de grafo com caminho hamiltoniano
        allVConnected = hamiltonian; //se hamiltoniano estiver habilitado, todos os vertices estarao conectados
        ArrayList<Integer> interconnected = new ArrayList();
        int[] Vconnected = new int[maxVertex];
        for(int m=0;m<maxVertex;m++)
            Vconnected[m]=-1;
        String line;
        PrintWriter writer = new PrintWriter(nomeGrafo+".txt", "UTF-8");
        Integer[] edge = new Integer[3]; //edge[0]-> Vertice a ; edge[1] -> Vertice B; edge[2] -> Peso
        ArrayList<Integer[]> edgesL = new ArrayList(); //lista de arestas edge
        Random rand = new Random();
 
        //conecta todos os vertices criando arestas até que todos estajam interconectados
        while(allVConnected==false || interconnected.size()<maxVertex){
        // Build a connection between two random vertex
        while (i < e) {//e= numero minimo de arestas
            //primeiro garantimos que caminhos hamiltonianos existam para este problema
            if(hamiltonian){
            for(int k=1; k<=maxVertex-1; k++){ //combinacao 2 a 2
                for(int l=k+1; l<=maxVertex; l++){
                    edge[0] = k;
                    edge[1] = l;
                    edge[2] = rand.nextInt(10)+1;
                    edgesL.add(i, edge);
                    j=i+1;
                    line = "e"+j+"[0] = "+edgesL.get(i)[0]+
                    "      e"+j+"[1] = "+edgesL.get(i)[1]+ "           valor = "+edgesL.get(i)[2] + " \n";
                    System.out.println(line);
            
                    //escreve no arquivo
                    writer.print(line);
                    edge = new Integer[3]; //cria novo edge
                    i++;
                }
                interconnected.add(k);
            }interconnected.add(maxVertex);hamiltonian=false;}
            if(i>=e) break;
            //continua criando edges se necessario
            edge[0] = rand.nextInt(maxVertex) + 1; //Vertice A: 1 a 15
            edge[1] = rand.nextInt(maxVertex) + 1; //Vertice B: 1 a 15
            edge[2] = rand.nextInt(10)+1; //Peso da aresta : 1 a 10
            edgesL.add(i, edge); //adiciona nova aresta na lista
            
            //**Desnecessario após iteração hamiltoniana (se for habilitada)** 
            //garantindo inetrconexão de todos os vértices
            if(interconnected.isEmpty()){interconnected.add(edge[0]);interconnected.add(edge[1]);} //insere na lista primeira ligação entre 2 vertices 
            //le todas as arestas
            for(int c =0; c<edgesL.size(); c++){
                //insere na lista interconnected o vertice, se ainda nao foi inserido
                if(interconnected.contains(edgesL.get(c)[0]) && !interconnected.contains(edgesL.get(c)[1])){
                    interconnected.add(edgesL.get(c)[1]);}//System.out.println("Found pair");}
                //o vertice precisa ter ligação(direta/indireta) com todos os vertices na lista interconected
                if(!interconnected.contains(edgesL.get(c)[0]) && interconnected.contains(edgesL.get(c)[1])){
                    interconnected.add(edgesL.get(c)[0]);}
            } 
            //verifica se todos os vertices foram conectados
            Vconnected[edge[0]-1]=1;Vconnected[edge[1]-1]=1; //marca vertices A e B recentes como conectados
            allVConnected = true;
            for(int idx=0; idx<maxVertex; idx++){if(Vconnected[idx]==-1) allVConnected=false;} //se encontrar um vertice desconectado
            
            j=i+1;//para impressao
            line = "e"+j+"[0] = "+edgesL.get(i)[0]+
                    "      e"+j+"[1] = "+edgesL.get(i)[1]+ "           valor = "+edgesL.get(i)[2] + " \n";
            System.out.println(line);
            
            //escreve no arquivo
            writer.print(line);
            edge = new Integer[3]; //cria novo edge
            i++;
        }
        //se o numero minimo de arestas foi criado, e os vertices ainda nao foram todos conectados..
        e+=1; // incrementa numero de aresta
        }
        
        //AJSUTE: escreve dado na ultima linha do arquivo(facilita leitura posterior)
        writer.print("e"+j+"[0] = "+edgesL.get(i-1)[0]+
                    "      e"+j+"[1] = "+edgesL.get(i-1)[1]+ "           valor = "+edgesL.get(i-1)[2] + " \n");
        System.out.println("Final number of Edges : " +e);
        for(int indice=0; indice<interconnected.size(); indice++){
            System.out.print(interconnected.get(indice)+ "  ");}
        System.out.println();
        writer.close();
    }

}
