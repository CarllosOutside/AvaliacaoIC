package com.mycompany.avaliacaoic.problema1.pt2PCV;

import java.util.ArrayList;

/*Implementa a classe Path, que pode sofrer mutações(contanto que os novos caminhos gerados sejam validos)
Logo, toda combinação só será feita se o elemento for completo!
*/
public class Path implements Cloneable{
        //Path object attributes
	private long nID; //pathid
	private int[] Order; //path
	private double nFitness; //path fitness
	private static long nCount;
        
       /**
        * Faz validação de um caminho, que deve ser completo
        * @param grafo recebe grafo (V1 V2 P)
        * @param path recebe o caminho pretendente
        * @return retorna se caminho é valido
        */
        public boolean validatePath(ArrayList<Integer[]> grafo, int[] path)
        {
            //for N vertices, must find N-1 edges
            int count=0; 
            for(int i=0; i<path.length-1; i++){
                for(int j=0; j<grafo.size(); j++){
                    if((grafo.get(j)[0] == path[i] && grafo.get(j)[1] == path[i+1]) || (grafo.get(j)[1] == path[i] && grafo.get(j)[0] == path[i+1])){
                        //edge found
                        count++;
                        break;
                    }
                        
                }
            }
            //if N-1 edges were found
            return count==path.length-1?true:false;
        }
	
        /**
         * Calcula custo do caminho -> Se um caminho entre 2 vertices não existe, o custo é 999
         * @param grafo custos armazenados em grafo
         * @param path caminho escolhido
         * @return 
         */
        public int calculatePathCost(ArrayList<Integer[]> grafo, int[] path){
            int cost=0, mincost=999;
                int count=0; 
            for(int i=0; i<path.length-1; i++){
                for(int j=0; j<grafo.size(); j++){
                    if((grafo.get(j)[0] == path[i] && grafo.get(j)[1] == path[i+1]) || (grafo.get(j)[1] == path[i] && grafo.get(j)[0] == path[i+1])){
                        //edge found
                        if(grafo.get(j)[2]<mincost)
                            mincost=grafo.get(j)[2]; //Menor custo entre 2 vertices
                    }
                        
                }
                cost+=mincost;
                mincost=999;
            }
            nFitness =(double) 1/cost; //fitness is inversely proportional to path cost
            return cost;
        }
	/**
	 * constructor I, gera um Path randomico
	 * @param nVertices indicates the length of 
	 * 					  the path
	 * @param lShuffle shuffles the order if set to true
	 * 					 intensively
	 */
	public Path(int nVertices,boolean lShuffle) {
	//public Path(int nVertices, ArrayList<Integer[]> grafo,boolean lShuffle) {	
		//set ID of Path
		this.nID = ++nCount;
		
		//initialize the array
		this.Order = new int[nVertices];
		
		//set initial order (path) -> probably not a valid one
		for (int i = 0 ; i < nVertices ; i++ ) {
			this.Order[i] = i+1;
                       // System.out.println("gerando..");
		}
                //cria qualquer caminho(mesmo que invalido)
                if(lShuffle) {
			//shuffle the elements randomly
			this.shuffle(nVertices);
		}
		//shuffle the elements randomly until we get a valid complete path
               // while(!validatePath(grafo, this.Order)){
                   // this.shuffle(nVertices);
                    /*System.out.println("Randomico Gerado");
                    for (int i = 0 ; i < nVertices ; i++ ) {
                        System.out.print(this.Order[i]+"->");
                    }
                    System.out.print(validatePath(grafo, this.Order));
                    System.out.println();*/
                //}

	}
	
		
	/**
	 * constructor II, used to create a copy
	 * of another path by means of the 
	 * .clone() method
	 * @param copyOf Path being copied
	 */
	private Path(Path copyOf) {
		this.Order = copyOf.Order.clone();
		this.nID = copyOf.nID;
                this.nFitness = copyOf.nFitness;
	}
	
	
	/**
	 * constructor III, used to initiate a
	 * Path with a give order
	 * @param order int array providing the 
	 * 				 order
	 */
	public Path(int[] order) {
		//set ID of Path
		this.nID = ++nCount;
		this.Order = order.clone();
	}
	
	
	/**
	 * creates a clone of a Path with help
	 * of the constructor II
	 */
	public Path clone() {
		return new Path(this);
	}
		

	
	/**
	 * shuffles the order according to
	 * the intensity
	 * @param nIntensity number of swaps
	 * 						performed
	 */
	private void shuffle(int nIntensity) {

		int nMax = this.getLength(); //number of vertices
		int nIndexA = -1; //arbitrary
		int nIndexB = -1; //arbitrary
		boolean lExit = false; 
		
		if(nMax <= 1) {
			//no need to swap
			lExit = true;
		}
		if(nMax == 2) {
			//swap once
			this.swap(0, 1);
			lExit = true;
                }
		
		if (!lExit) {
			//swap two elements randomly a certain number of times
			for ( int i = 0 ; i <= nIntensity ; i++ ) {
				
				//set indices equal
				nIndexA = nIndexB;
				
				//find two different indices
				while(nIndexA == nIndexB) {
					//choose two indices randomly
					nIndexA = (int)(Math.floor(nMax * Math.random())); 
					nIndexB = (int)(Math.floor(nMax * Math.random())); 
				}
				
				//swap those two
				this.swap(nIndexA, nIndexB);
			}
		}
	}
	
	
	
	/**
	 * makes two values of the int array
	 * Order changing place
	 * @param a one index of the array
	 * @param b another index of the array
	 */
	private void swap(int a, int b) {
		
		int temp = this.Order[a];
		this.Order[a] = this.Order[b];
		this.Order[b] = temp;
	}
	
	
		
	/**
	 * swaps values in the int array
	 * Order randomly according to the
	 * mutation rate
	 * @param nMutationRate Should be a value between
	 * 							0 and 1 indicating how much
	 * 							percent of the path may be 
	 * 							affected through mutation.
	 * 							Mutation will be performed
	 * 							by means of the .shuffle()
	 * 							method.
	 */
	public void mutate(double nMutationRate) {
		
		int nIntensity = 0;
			
		/*when mutation rate is 0.4, only 40% of the path 
		 * should be changed   */
		int len = this.getLength(); //number of vertices
		double a = Math.floor(len * Math.abs(nMutationRate));//percentage of vertices
		nIntensity = (int)(a);
		
	   /*since the mutation will be simply execute by swapping
		  the elements we have to divide the intensity by
		  two as well (one swap ~~> two changes)   */
		nIntensity = nIntensity / 2;
               // do
                    this.shuffle(nIntensity); //muta de qualquer maneira -> mesmo que se crie invalidos
                //while(!validatePath(grafo, this.Order));
	}
	
	
	/**
	 * 
	 * @return length of the path
	 */
	public int getLength() {
		
		int nReturn = this.Order.length;
		
		return nReturn;
	}
	
	
	/**
	 * retrieves the value of a specific
	 * element in the path's Order array
	 * @param i index of the element being
	 * 			retrieved
	 * @return array value as an integer
	 */
	public int get(int i) {
		
		int nReturn = this.Order[i];
		
		return nReturn;
	}
	
	
	
	
	/**
	 * method used to get information about a 
	 * path in form of a one line string.
	 * @return the specific order of vertices,
	 * 		  indicated by their indices (index based),
	 * 		  and the current fitness as a one 
	 * 		  line info string
	 */
	public String getInfo() {
		
		String cReturn = "";
		
		for( int i = 0 ; i < this.getLength() ; i++ ) {
			cReturn = cReturn + "[" + this.get(i) + "] ";
		}
		
		//remove last character
		cReturn = cReturn.substring(0, cReturn.length() - 1);
		
		cReturn = cReturn + "  {" + String.format("%.5f",nFitness)+ "}";
		
		//used for debugging reasons
		//cReturn = cReturn + "  {" + System.identityHashCode(this) + "}";
		
		return cReturn;
	}

	/**
	 * gets the path's fitness
	 * @return Fitness as a double value
	 * 		  between 0 and 1. The higher the 
	 * 		  value the better the path
	 */
	public double getFitness() {
		return nFitness;
	}


	
	/**
	 * sets the path's fitness
	 * @param nFitness value between 0 and 1,
	 * 		          whereas a higher value
	 * 					 is considered as better
	 */
	public void setFitness(double nFitness) {
		this.nFitness = nFitness;
	}

        /**
         * retorna path(array inteiros)
         * @return 
         */
	public int[] getPath()
        {
            return this.Order;
        }
	/**
	 * used to check if a value is included in 
	 * an array
	 * @param array the array being searched
	 * @param v the value which is being checked
	 * @return true if value is included, false 
	 * 		  if not
	 */
	public static boolean contains(final int[] array, final int v) {

        boolean result = false;

        for(int i : array){
            if(i == v){
                result = true;
                break;
            }
        }

        return result;
    }
	
	
	
}
