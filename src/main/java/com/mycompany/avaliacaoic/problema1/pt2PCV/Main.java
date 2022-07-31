package com.mycompany.avaliacaoic.problema1.pt2PCV;

import com.mycompany.avaliacaoic.problema1.pt1Grafo.GrafoGenerator;
import com.mycompany.avaliacaoic.problema1.pt1Grafo.GrafoReader;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {
    static int numVertices=15;
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //Gerando Grafos de solução hamiltoniana possível com 15 nós cada
        GrafoGenerator gg = new GrafoGenerator(numVertices);
        gg.generateRandomGraphs(numVertices*(numVertices-1)/2, "Grafo1");
        gg.generateRandomGraphs(numVertices*(numVertices-1)/2, "Grafo2");
        gg.generateRandomGraphs(numVertices*(numVertices-1)/2, "Grafo3");
        
        //Lendo Grafos de arquivos
        GrafoReader gr = new GrafoReader();
        ArrayList<Integer[]> grafo1, grafo2, grafo3;
        grafo1=gr.readGrafo("Grafo1.txt");
        grafo2=gr.readGrafo("Grafo2.txt");
        grafo3=gr.readGrafo("Grafo3.txt");
        
        //criando uma população de 50 Paths aleatórios(tamanho, taxaShuffle, numNodos, grafo) para cada grafo
        Population pop1 = new Population(50, 0.1, numVertices, grafo1);
        Population pop2 = new Population(50, 0.4, numVertices, grafo2);
        Population pop3 = new Population(50, 0.2, numVertices, grafo3);
        
        //Criando x populações e selecionando melhores caminhos
        int numPop=0; Path best1 = new Path(numVertices, true), best2=new Path(numVertices, true), best3=new Path(numVertices, true); 
        best1.calculatePathCost(grafo1, best1.getPath());best2.calculatePathCost(grafo2, best2.getPath());best3.calculatePathCost(grafo3, best3.getPath());
        while(numPop<100){
        //evoluindo populações por x gerações
        int gen=0;
        while(gen<100){
            pop1.evolve2(grafo1);
            pop2.evolve2(grafo2);
            pop3.evolve2(grafo3);
            gen++; //cada evolucao sobrescreve a geração anterior (50 paths novos)
        }
        //Após 100 gerações, verifica se a população gerou uma solução final melhor
        if(pop1.getFittestPath().getFitness()>best1.getFitness())
            best1 = pop1.getFittestPath();
        if(pop2.getFittestPath().getFitness()>best2.getFitness())
            best2 = pop2.getFittestPath();
        if(pop3.getFittestPath().getFitness()>best3.getFitness())
            best3 = pop3.getFittestPath();
        //próxima população
        pop1 = new Population(50, 0.3, numVertices, grafo1);
        pop2 = new Population(50, 0.1, numVertices, grafo2);
        pop3 = new Population(50, 0.6, numVertices, grafo3);
        numPop++;
        }
        //Imprimindo melhores soluções de cada grafo
        System.out.println("Grafo1: "+ best1.getInfo());
        System.out.println("Grafo2: "+ best2.getInfo());
        System.out.println("Grafo3: "+ best3.getInfo());
        
    }
}
