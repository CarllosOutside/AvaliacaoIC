package com.mycompany.avaliacaoic.problema2Hanoi;

import aima.core.search.framework.Node;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.Problem;
import aima.core.search.uninformed.UniformCostSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.search.uninformed.DepthLimitedSearch;

import java.util.List;
import java.util.Scanner;
import java.util.Optional;

public class Main{
    
    public static void main(String[] args) {
        int k=0;
        Scanner sc = new Scanner(System.in);
        while(k<3 || k>7){
            System.out.println("Quantidade de discos 3<=k<=7:");
            k = sc.nextInt();}
        Main hanoi = new Main();
        hanoi.run(k);
    }
    
    public void run(int k){
       this.depthLimitSearch(k);
    }
    
    
    private void iterativeSearch(int k) {
        HState inicial = new HState(k);
        Problem<HState, HAction> problem;
        problem = new GeneralProblem<>(
                        inicial,
                        HFunctions::getActions,
                        HFunctions::getResult,
                        HFunctions::testGoal);
        SearchForActions<HState, HAction> search = new IterativeDeepeningSearch<>();
        try {
            SearchAgent<HState, HAction> agent = new SearchAgent<>(problem, search);
            Optional<List<HAction>> actions = search.findActions(problem);
            this.printActions(actions);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

    }
    
    private void depthLimitSearch(int k) {
        //cria um estado -> nó inicial
        HState inicial = new HState(k);
        Node noinicio = new Node(inicial);
        
        Problem<HState, HAction> problem;
        problem = new GeneralProblem<>(
                        inicial,
                        HFunctions::getActions,
                        HFunctions::getResult,
                        HFunctions::testGoal);
        int depth = 5169;
        SearchForActions<HState, HAction> search = new DepthLimitedSearch<>(depth, noinicio);
        
        try {
            SearchAgent<HState, HAction> agent = new SearchAgent<>(problem, search);
            Optional<List<HAction>> actions = search.findActions(problem);
            this.printActions(actions);
            System.out.println("Acoes tomadas: "+actions.get().size());
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

    }
  
    private void printActions(Optional<List<HAction>> actions) {
	List<HAction> acList ;
        Object aux = actions.get();
        acList = ((List<HAction>) aux);
        for (int i = 0; i < acList.size(); i++) {
            String act = (String) acList.get(i).getName();
            System.out.println(act);
	}
    }

}
