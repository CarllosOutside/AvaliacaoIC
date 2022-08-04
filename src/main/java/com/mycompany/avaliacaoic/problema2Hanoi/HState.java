package com.mycompany.avaliacaoic.problema2Hanoi;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HState {
/**
 * A,B e C = spikes
 */
    public int A[];
    public int B[];
    public int C[];
    public int numDisks;
    
    /**
     * Constructor inicializa Estado da torre
     * @param k número de discos 
     */
    public HState(int k) {
        numDisks = k;
        A = new int[k];
        B = new int[k];
        C = new int[k];
        //Initial State
        for(int i=0;i<k;i++){
            this.A[i] = i+1;
            this.B[i] = -1;
            this.C[i] = -1;
        }
    }
    
    /*
    * Getters e Setters
    */
    public void setA(int[] a){
        System.arraycopy(a, 0, A, 0, this.numDisks);
    }
    
    public void setB(int[] b){
        System.arraycopy(b, 0, B, 0, this.numDisks);
    }
    
    public void setC(int[] c){
        System.arraycopy(c, 0, C, 0, this.numDisks);
    }
    
    public int[] getA() {
        return this.A;
    }
    
    public int getA(int i){
        if (i == -1)
            return -1;
        return this.A[i];
    }
    
    public int[] getB() {
        return this.B;
    }
    
    public int getB(int i){
        if (i == -1)
            return -1;
        return this.B[i];
    }
    
    public int[] getC() {
        return this.C;
    }
    
    public int getC(int i){
        if (i == -1)
            return -1;
        return this.C[i];
    }
    
    public int getDiskOnTop(int[] spike){
        for(int i=0;i<numDisks; i++){
            if (spike[i]!=-1)
                return i+1;
        }
        return 999;
    }
    
    public boolean vazio(int[] spike){
        return Arrays.stream(spike).allMatch(i -> i==-1);
    }

    public String toString(){
        String impressao;
            impressao = "\n  S1:";
            for(int i=numDisks-1;i>=0; i--)
            {
                if (A[i]!=-1)
                    impressao+=" "+A[i]+"->";
            } 
            impressao += "\n  S2:";
            for(int i=numDisks-1;i>=0; i--)
            {
                if (B[i]!=-1)
                    impressao+=" "+B[i]+"->";
            } 
            impressao += "\n  S3:";
            for(int i=numDisks-1;i>=0; i--)
            {
                if (C[i]!=-1)
                    impressao+=" "+C[i]+"->";
            } 
        return impressao;
    }
}
