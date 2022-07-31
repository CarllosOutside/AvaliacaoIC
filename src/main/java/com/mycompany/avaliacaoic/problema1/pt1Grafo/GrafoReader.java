
package com.mycompany.avaliacaoic.problema1.pt1Grafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Realiza leitura de arquivo .txt contendo grafo
public class GrafoReader {
    
    //Le grafo e devolve lista com PESO de arestas em ordem cerscente
    public ArrayList<Integer[]> readGrafo(String nomeGrafo) throws FileNotFoundException
    {
        Integer[] edge = new Integer[3]; //edge[0]-> Vertice a ; edge[1] -> Vertice B; edge[2] -> Peso
        ArrayList<Integer[]> edgesL = new ArrayList(); //lista de arestas edge
        int i=0; //indice da aresta -> edge[i]
        File file = new File(nomeGrafo);
        Scanner in = new Scanner(file);
        //Lendo grafo do arquivo 
        while(in.hasNext())
        {
            String token = in.next();//lê token
            
            if(token.matches("\\d+"))//se token for inteiro
            {
                //System.out.println(token);
                if(i==0 && edge[2]!=null)
                {//Se já leu edge[0], edge[1], edge[2] 
                    int idx = 0;/*indice onde aresta sera armazenada*/
                        while (idx < edgesL.size() && edge[2] >= edgesL.get(idx)[2]) //ordena aresta por Peso 
                            idx++; /*caminha para o fim da lista/fila*/
                     edgesL.add(idx, edge); //System.out.println(edgesL.get(0)[0]); //adiciona aresta à lista
                    edge = new Integer[3]; //cria novo edge
                }
                edge[i] = Integer.parseInt(token); //lê proximo valor inteiro 
                i=(i+1)%3; //Sempre que edge[2] é lido, i recebe 0 novamente. 
            }
 
        }
        //ystem.out.println("ok");
        in.close();
        
        return edgesL;
    }
}
