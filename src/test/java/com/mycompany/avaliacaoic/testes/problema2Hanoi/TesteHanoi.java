/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.avaliacaoic.testes.problema2Hanoi;

import com.mycompany.avaliacaoic.problema2Hanoi.HAction;
import com.mycompany.avaliacaoic.problema2Hanoi.HFunctions;
import com.mycompany.avaliacaoic.problema2Hanoi.HGoalTest;
import com.mycompany.avaliacaoic.problema2Hanoi.HState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peterpc
 */
public class TesteHanoi {
     public static void main(String[] args){
         HGoalTest gt = new HGoalTest(3);
         //gerando initial state
        HState estadoInicial = new HState(3);
        System.out.println(gt.test(estadoInicial));
        for(int k : estadoInicial.getA())
            System.out.println("A"+k+": "+estadoInicial.getA(k-1));
        System.out.println("TOPO A: " + estadoInicial.getDiskOnTop(estadoInicial.A));
        System.out.println("TOPO B: " + estadoInicial.getDiskOnTop(estadoInicial.B));
        //lista de acoes a aprtir do estado Inicial
        HFunctions f = new HFunctions();
        List<HAction> acList = new ArrayList();
        acList = f.testGetActions(estadoInicial);
        for(HAction a: acList)
            System.out.println(a.getName());
        //realizando movimento D1AB
        HState newstate = f.testGetResult(estadoInicial, acList.get(0));
        System.out.println("TOPO A: " + newstate.getDiskOnTop(newstate.A));
        System.out.println("TOPO B: " + newstate.getDiskOnTop(newstate.B));
        //lista de acoes a aprtir do newstate
        acList = f.testGetActions(newstate);
        for(HAction a: acList)
            System.out.println(a.getName());
        //jogando.. D2AC
        newstate = f.testGetResult(newstate, acList.get(2));
        System.out.println("TOPO A: " + newstate.getDiskOnTop(newstate.A));
        System.out.println("TOPO B: " + newstate.getDiskOnTop(newstate.B));
        System.out.println("TOPO C: " + newstate.getDiskOnTop(newstate.C));
        //lista de acoes a aprtir do newstate
        acList = f.testGetActions(newstate);
        for(HAction a: acList)
            System.out.println(a.getName());
        //D1BA
        newstate = f.testGetResult(newstate, acList.get(0));
        System.out.println("TOPO A: " + newstate.getDiskOnTop(newstate.A));
        System.out.println("TOPO B: " + newstate.getDiskOnTop(newstate.B));
        System.out.println("TOPO C: " + newstate.getDiskOnTop(newstate.C));
        //lista de acoes a aprtir do newstate
        acList = f.testGetActions(newstate);
        for(HAction a: acList)
            System.out.println(a.getName());
        //D2CB
        newstate = f.testGetResult(newstate, acList.get(2));
        System.out.println("TOPO A: " + newstate.getDiskOnTop(newstate.A));
        System.out.println("TOPO B: " + newstate.getDiskOnTop(newstate.B));
        System.out.println("TOPO C: " + newstate.getDiskOnTop(newstate.C));
        //lista de acoes a aprtir do newstate
        acList = f.testGetActions(newstate);
        for(HAction a: acList)
            System.out.println(a.getName());
        //D1AB
        newstate = f.testGetResult(newstate, acList.get(0));
        System.out.println("TOPO A: " + newstate.getDiskOnTop(newstate.A));
        System.out.println("TOPO B: " + newstate.getDiskOnTop(newstate.B));
        System.out.println("TOPO C: " + newstate.getDiskOnTop(newstate.C));
        //lista de acoes a aprtir do newstate
        acList = f.testGetActions(newstate);
        for(HAction a: acList)
            System.out.println(a.getName());
        //D3AC
        newstate = f.testGetResult(newstate, acList.get(2));
        System.out.println("TOPO A: " + newstate.getDiskOnTop(newstate.A));
        System.out.println("TOPO B: " + newstate.getDiskOnTop(newstate.B));
        System.out.println("TOPO C: " + newstate.getDiskOnTop(newstate.C));
        //lista de acoes a aprtir do newstate
        acList = f.testGetActions(newstate);
        for(HAction a: acList)
            System.out.println(a.getName());
        //D1BA
        newstate = f.testGetResult(newstate, acList.get(0));
        System.out.println("TOPO A: " + newstate.getDiskOnTop(newstate.A));
        System.out.println("TOPO B: " + newstate.getDiskOnTop(newstate.B));
        System.out.println("TOPO C: " + newstate.getDiskOnTop(newstate.C));
        //lista de acoes a aprtir do newstate
        acList = f.testGetActions(newstate);
        for(HAction a: acList)
            System.out.println(a.getName());
        System.out.println(gt.test(newstate));
        //D2BC
        newstate = f.testGetResult(newstate, acList.get(2));
        System.out.println("TOPO A: " + newstate.getDiskOnTop(newstate.A));
        System.out.println("TOPO B: " + newstate.getDiskOnTop(newstate.B));
        System.out.println("TOPO C: " + newstate.getDiskOnTop(newstate.C));
        //lista de acoes a aprtir do newstate
        acList = f.testGetActions(newstate);
        for(HAction a: acList)
            System.out.println(a.getName());
        System.out.println(gt.test(newstate));
        //D1AC
        newstate = f.testGetResult(newstate, acList.get(1));
        System.out.println("TOPO A: " + newstate.getDiskOnTop(newstate.A));
        System.out.println("TOPO B: " + newstate.getDiskOnTop(newstate.B));
        System.out.println("TOPO C: " + newstate.getDiskOnTop(newstate.C));
        //lista de acoes a aprtir do newstate
        acList = f.testGetActions(newstate);
        for(HAction a: acList)
            System.out.println(a.getName());
        System.out.println(gt.test(newstate));
        System.out.println(newstate);
     }
}
