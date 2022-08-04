
package com.mycompany.avaliacaoic.problema2Hanoi;


import aima.core.search.framework.problem.GoalTest;
import java.util.Arrays;

public class HGoalTest implements GoalTest {
    
    int gEmpty[]; 
    int gFull[]; 
    
    /**
     * Constructor defines the Goal State
     * @param k number of disks
     */
    public HGoalTest(int k) {
        gEmpty = new int[k];
        gFull = new int[k];
        for(int i=0;i<k;i++){
            this.gEmpty[i] = -1;
            this.gFull[i] = i+1;
        }
    }
    
    /**
     * Tests if Goal state was reached
     * @param state state parameter to be compared to goal
     * @return 
     */
    public boolean test(Object state) {
        HState hanoi = (HState) state;
        if((Arrays.equals(hanoi.getA(), this.gEmpty) && Arrays.equals(hanoi.getC(), this.gFull) && Arrays.equals(hanoi.getB(), this.gEmpty) )){
                    return true;
        }
        return false;
    }
}