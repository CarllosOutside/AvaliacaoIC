
package com.mycompany.avaliacaoic.problema2Hanoi;

import java.util.ArrayList;
import java.util.List;
import aima.core.search.framework.problem.StepCostFunction;

import java.util.Objects;

public class HFunctions {
    
    public int aux = 0;
    public static StepCostFunction<HState, HAction> getJCostFunction() {
        return new JStepCostFunctionImpl();        
    }
    
    /**
     * Verifica quais ações são possíveis no Estado atual
     * @param state estado atual
     * @return lista de ações
     */
    public static List<HAction> getActions(HState state) {
        List<HAction> actions = new ArrayList<>();
        
        //Disco 1 no topo de algum spike
        if(state.getDiskOnTop(state.A)==1){
            actions.add(new HAction(HAction.D1AB));
            actions.add(new HAction(HAction.D1AC));
        }else if(state.getDiskOnTop(state.B)==1){
            actions.add(new HAction(HAction.D1BA));
            actions.add(new HAction(HAction.D1BC));
        }else if(state.getDiskOnTop(state.C)==1){
            actions.add(new HAction(HAction.D1CA));
            actions.add(new HAction(HAction.D1CB));
        }
        
        //Disco 2 no topo de algum spike
        if(state.getDiskOnTop(state.A)==2){
            if(state.getDiskOnTop(state.A)<state.getDiskOnTop(state.B))
                actions.add(new HAction(HAction.D2AB));
            if(state.getDiskOnTop(state.A)<state.getDiskOnTop(state.C))
                actions.add(new HAction(HAction.D2AC));
        }else if(state.getDiskOnTop(state.B)==2){
            if(state.getDiskOnTop(state.B)<state.getDiskOnTop(state.A))
                actions.add(new HAction(HAction.D2BA));
            if(state.getDiskOnTop(state.B)<state.getDiskOnTop(state.C))
                actions.add(new HAction(HAction.D2BC));
        }else if(state.getDiskOnTop(state.C)==2){
            if(state.getDiskOnTop(state.C)<state.getDiskOnTop(state.A))
                actions.add(new HAction(HAction.D2CA));
            if(state.getDiskOnTop(state.C)<state.getDiskOnTop(state.B))
                actions.add(new HAction(HAction.D2CB));
        }
        
        //Disco 3 no topo de algum spike
        if(state.getDiskOnTop(state.A)==3){
            if(state.getDiskOnTop(state.A)<state.getDiskOnTop(state.B))
                actions.add(new HAction(HAction.D3AB));
            if(state.getDiskOnTop(state.A)<state.getDiskOnTop(state.C))
                actions.add(new HAction(HAction.D3AC));
        }else if(state.getDiskOnTop(state.B)==3){
            if(state.getDiskOnTop(state.B)<state.getDiskOnTop(state.A))
                actions.add(new HAction(HAction.D3BA));
            if(state.getDiskOnTop(state.B)<state.getDiskOnTop(state.C))
                actions.add(new HAction(HAction.D3BC));
        }else if(state.getDiskOnTop(state.C)==3){
            if(state.getDiskOnTop(state.C)<state.getDiskOnTop(state.A))
                actions.add(new HAction(HAction.D3CA));
            if(state.getDiskOnTop(state.C)<state.getDiskOnTop(state.B))
                actions.add(new HAction(HAction.D3CB));
        }
        
        //Disco 4 no topo de algum spike
        if(state.getDiskOnTop(state.A)==4){
            if(state.getDiskOnTop(state.A)<state.getDiskOnTop(state.B))
                actions.add(new HAction(HAction.D4AB));
            if(state.getDiskOnTop(state.A)<state.getDiskOnTop(state.C))
                actions.add(new HAction(HAction.D4AC));
        }else if(state.getDiskOnTop(state.B)==4){
            if(state.getDiskOnTop(state.B)<state.getDiskOnTop(state.A))
                actions.add(new HAction(HAction.D4BA));
            if(state.getDiskOnTop(state.B)<state.getDiskOnTop(state.C))
                actions.add(new HAction(HAction.D4BC));
        }else if(state.getDiskOnTop(state.C)==4){
            if(state.getDiskOnTop(state.C)<state.getDiskOnTop(state.A))
                actions.add(new HAction(HAction.D4CA));
            if(state.getDiskOnTop(state.C)<state.getDiskOnTop(state.B))
                actions.add(new HAction(HAction.D4CB));
        }
        
        //Disco5 no topo de algum spike
        if(state.getDiskOnTop(state.A)==5){
            if(state.getDiskOnTop(state.A)<state.getDiskOnTop(state.B))
                actions.add(new HAction(HAction.D5AB));
            if(state.getDiskOnTop(state.A)<state.getDiskOnTop(state.C))
                actions.add(new HAction(HAction.D5AC));
        }else if(state.getDiskOnTop(state.B)==5){
            if(state.getDiskOnTop(state.B)<state.getDiskOnTop(state.A))
                actions.add(new HAction(HAction.D5BA));
            if(state.getDiskOnTop(state.B)<state.getDiskOnTop(state.C))
                actions.add(new HAction(HAction.D5BC));
        }else if(state.getDiskOnTop(state.C)==5){
            if(state.getDiskOnTop(state.C)<state.getDiskOnTop(state.A))
                actions.add(new HAction(HAction.D5CA));
            if(state.getDiskOnTop(state.C)<state.getDiskOnTop(state.B))
                actions.add(new HAction(HAction.D5CB));
        }
        
        //Disco 6 no topo de algum spike
        if(state.getDiskOnTop(state.A)==6){
            if(state.getDiskOnTop(state.A)<state.getDiskOnTop(state.B))
                actions.add(new HAction(HAction.D6AB));
            if(state.getDiskOnTop(state.A)<state.getDiskOnTop(state.C))
                actions.add(new HAction(HAction.D6AC));
        }else if(state.getDiskOnTop(state.B)==6){
            if(state.getDiskOnTop(state.B)<state.getDiskOnTop(state.A))
                actions.add(new HAction(HAction.D6BA));
            if(state.getDiskOnTop(state.B)<state.getDiskOnTop(state.C))
                actions.add(new HAction(HAction.D6BC));
        }else if(state.getDiskOnTop(state.C)==6){
            if(state.getDiskOnTop(state.C)<state.getDiskOnTop(state.A))
                actions.add(new HAction(HAction.D6CA));
            if(state.getDiskOnTop(state.C)<state.getDiskOnTop(state.B))
                actions.add(new HAction(HAction.D6CB));
        }
        
        //Disco 7 no topo de algum spike
        if(state.getDiskOnTop(state.A)==7){
            if(state.getDiskOnTop(state.A)<state.getDiskOnTop(state.B))
                actions.add(new HAction(HAction.D7AB));
            if(state.getDiskOnTop(state.A)<state.getDiskOnTop(state.C))
                actions.add(new HAction(HAction.D7AC));
        }else if(state.getDiskOnTop(state.B)==7){
            if(state.getDiskOnTop(state.B)<state.getDiskOnTop(state.A))
                actions.add(new HAction(HAction.D7BA));
            if(state.getDiskOnTop(state.B)<state.getDiskOnTop(state.C))
                actions.add(new HAction(HAction.D7BC));
        }else if(state.getDiskOnTop(state.C)==7){
            if(state.getDiskOnTop(state.C)<state.getDiskOnTop(state.A))
                actions.add(new HAction(HAction.D7CA));
            if(state.getDiskOnTop(state.C)<state.getDiskOnTop(state.B))
                actions.add(new HAction(HAction.D7CB));
        }
        
        return actions;
    }
    
    /**
     * Gera um novo estado a partir do estado atual e de uma ação
     * @param e estado atual
     * @param ac ação
     * @return novo estado
     */
    public static HState getResult(HState e, HAction ac) {
        //definindo as 42 ações
        if (Objects.equals(ac.getName(), HAction.D1AB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[0] = -1;
            child.B[0] = 1;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D1AC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[0] = -1;
            child.C[0] = 1;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D1BA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[0] = -1;
            child.A[0] = 1;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D1BC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[0] = -1;
            child.C[0] = 1;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D1CA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[0] = -1;
            child.A[0] = 1;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D1CB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[0] = -1;
            child.B[0] = 1;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D2AB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[1] = -1;
            child.B[1] = 2;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D2AC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[1] = -1;
            child.C[1] = 2;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D2BA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[1] = -1;
            child.A[1] = 2;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D2BC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[1] = -1;
            child.C[1] = 2;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D2CA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[1] = -1;
            child.A[1] = 2;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D2CB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[1] = -1;
            child.B[1] = 2;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D3AB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[2] = -1;
            child.B[2] = 3;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D3AC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[2] = -1;
            child.C[2] = 3;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D3BA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[2] = -1;
            child.A[2] = 3;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D3BC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[2] = -1;
            child.C[2] = 3;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D3CA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[2] = -1;
            child.A[2] = 3;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D3CB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[2] = -1;
            child.B[2] = 3;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D4AB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[3] = -1;
            child.B[3] = 4;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D4AC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[3] = -1;
            child.C[3] = 4;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D4BA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[3] = -1;
            child.A[3] = 4;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D4BC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[3] = -1;
            child.C[3] = 4;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D4CA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[3] = -1;
            child.A[3] = 4;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D4CB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[3] = -1;
            child.B[3] = 4;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D5AB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[4] = -1;
            child.B[4] = 5;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D5AC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[4] = -1;
            child.C[4] = 5;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D5BA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[4] = -1;
            child.A[4] = 5;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D5BC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[4] = -1;
            child.C[4] = 5;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D5CA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[4] = -1;
            child.A[4] = 5;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D5CB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[4] = -1;
            child.B[4] = 5;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D6AB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[5] = -1;
            child.B[5] = 6;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D6AC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[5] = -1;
            child.C[5] = 6;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D6BA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[5] = -1;
            child.A[5] = 6;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D6BC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[5] = -1;
            child.C[5] = 6;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D6CA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[5] = -1;
            child.A[5] = 6;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D6CB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[5] = -1;
            child.B[5] = 6;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D7AB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[6] = -1;
            child.B[6] = 7;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D7AC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.A[6] = -1;
            child.C[6] = 7;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D7BA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[6] = -1;
            child.A[6] = 7;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D7BC)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.B[6] = -1;
            child.C[6] = 7;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D7CA)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[6] = -1;
            child.A[6] = 7;
            return child;
        }else if (Objects.equals(ac.getName(), HAction.D7CB)) {
            HState child = new HState(e.numDisks);
            child.setA(e.A);
            child.setB(e.B);
            child.setC(e.C);
            child.C[6] = -1;
            child.B[6] = 7;
            return child;
        }
        return null;
    }

    public static boolean testGoal(HState e) {
        HGoalTest gt = new HGoalTest(e.numDisks);
        return gt.test(e);
    } 
    
    
    private static class JStepCostFunctionImpl implements StepCostFunction<HState, HAction> {
        
        private static double constantCost = 1.0;

        private JStepCostFunctionImpl() {

        }

        @Override
        public double applyAsDouble(HState state, HAction action, HState statePrimed) {
            double jcost = 0;
            /*
            if (Objects.equals(action.getName(), JAction.COMPLETAR_J3)) jcost = 3 - statePrimed.getVol3L();
            if (Objects.equals(action.getName(), JAction.COMPLETAR_J4)) jcost = 4 - statePrimed.getVol4L();
            if (Objects.equals(action.getName(), JAction.COMPLETAR_J7)) jcost = 7 - statePrimed.getVol7L();
            */
            return jcost;
        }
        
        
        }
    public  List<HAction> testGetActions(HState state) 
    {
        List<HAction> ac = new ArrayList();
        ac=getActions(state);
        return ac;
    }
    public  HState testGetResult(HState e, HAction ac) 
    {
      HState child = getResult(e, ac);
      return child;
    }
}