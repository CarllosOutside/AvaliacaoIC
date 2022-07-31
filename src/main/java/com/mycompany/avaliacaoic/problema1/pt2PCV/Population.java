package com.mycompany.avaliacaoic.problema1.pt2PCV;

import java.util.ArrayList;


/**
 * A Population is used to hold the VertexSet
 * as well as all the paths. It is the population
 * which will be evolved and thus new paths can
 * be developed. All the genetic algorithm stuff
 * is also included in this class.
 * 
 * @author Roman
 *
 */
public class Population {
	

	private long nGeneration;
	private ArrayList<Path> ps;
	//private VertexSet vs;
	private double nMutationRate;
	
	
	/**
	 * constructor I, used to initiate a population
	 * @param size number of paths created
	 * @param nMutationRate value between 0 and 1	
	 * 							indicating the intensity
	 * 							of mutation being applied
	 */
	public Population(int size, double nMutationRate, int numNodes, ArrayList<Integer[]> grafo) {
		
		//define object attributes
		this.nGeneration = 0;
		//this.vs = vs;
		this.nMutationRate = nMutationRate;
		
		//create initial paths
		this.ps = new ArrayList<Path>();
		for (int i=0 ; i<size ; i++) { //gera 'size' caminhos 
			Path p = new Path(numNodes, true);  //cria um caminho randomico
                        p.calculatePathCost(grafo, p.getPath()); //calcula custo do caminho
			this.ps.add(p);//adiciona caminho à populacao
		}
		

		//determine fitness of each path
		//this.assessFitness();
               
	}
	
	
	/**
	 * Returns a Path whit a randomly defined
	 * order of how to travel the vertices.
	 * @return a randomly defined Path
	 */
	public Path getRandomPath() {
		
		int nIndex = (int) (this.ps.size() * Math.random());
		Path aReturn = this.ps.get(nIndex);
		
		return aReturn.clone();		
	}
	

	/**
	 * Determines the fittest path in the
	 * population by looking for the highest
	 * fitness and returns a clone of it.
	 * @return a clone of the fittest path
	 */
	public Path getFittestPath() {
		
		Path aReturn = null;
		
		//determine fitness of each path
		//this.assessFitness();
		
		//take an assumption
		aReturn = this.ps.get(0);
		
		//check if we find a fitter path
		for(int i=0 ; i<this.ps.size() ; i++ ) {
			Path p = this.ps.get(i);
			if(aReturn.getFitness() < this.ps.get(i).getFitness()) {
				aReturn = p;
			}
		}
		//muito clones podem causar stackOverflow
		return aReturn.clone();
	}

	/**
	 * Updates the fitness of each path in the
	 * population according to the total distance
	 * computable when traveling the path. Fitness
	 * is expressed as a value between 0 and 1, 
	 * whereas greater values implies higher fitness
	 * and consequently a better solution.
	 */
	private void assessFitness() {
		//sum of all distances
		double nFitnessRef = this.getPointOfReference();

		double nTotalDistance = 0;
		double nNewFitness=0;
			
		//updates the fitness of each path proportionally
		for(int i=0 ; i<this.ps.size() ; i++) {
			
			Path p = this.ps.get(i);
			nTotalDistance = (double) 1/p.getFitness();
			
			
		   /* to evaluate the fitness, we must understand which
			* scenarios are good (equals in high fitness) and which
			* are worse (equals in low fitness). In TSP, lower 
			* total distances should be rewarded with high fitness 
			* rates, since this is object of a minimization problem.
			*/ 
			
			//this will reward short distances with high fitness (inversion)
                        //100 - the percentage that this path distance represents over the sum of all distances
                        //the lesser the distance, the greater is the result 
			nNewFitness = (nFitnessRef - nTotalDistance) / nFitnessRef;
			
			//with this approach, we have to reduce the fitness by 
			//the {(number of paths in PathSet) - (1)}
			nNewFitness = nNewFitness / (this.ps.size() -1);
                        //sets new fitness for path
			p.setFitness(nNewFitness);
				
		}
		
	}

	/**
	 * Sums up each total distance generated with each
	 * path in this population in order to get a point
	 * of reference.
	 * @return sum of each path's total distance 
	 * 		  as a double value
	 */
	private double getPointOfReference() {
		
		double nReturn = 0;
		double nDis = 0;
		
		for(int i=0 ; i<this.ps.size() ; i++) {
			Path p = this.ps.get(i);
			nDis = (double) 1/p.getFitness(); //distance is inversely proportional to fitness
			nReturn += nDis; //sum up all distances
		}
		
		return nReturn;
	}

	/**
	 * Method II used to evolve the population one
	 * generation further.The two fittest paths
 will be the only two parent paths for all
 child paths of the next generation.
     * @param grafo necessario para fazer calculo do custo de cada caminho(filho)
	 */
	public void evolve2(ArrayList<Integer[]> grafo) {
		//criando uma nova geração
		ArrayList<Path> ps_new = new ArrayList<Path>();
		
		this.assessFitness();
                //pega 1º e 2º elementos mais aptos da geração passada
		Path p1 = pickRank(this.ps, 0);
		Path p2 = pickRank(this.ps, 1);
		
                //cria todos elementos da nova geração usando os mais aptos da geração passada 
		while(ps_new.size() < this.ps.size()) {
			
			Path p3 = cross(p1, p2); //cruzamento entre mais aptos
			//mutação do novo filho
			p3.mutate(this.nMutationRate);
                        //calcula custo do caminho do filho
                        p3.calculatePathCost(grafo, p3.getPath());
			//novo elemento criado, inserido na nova geração
			ps_new.add(p3);
		} 
		//população assume nova geração
		this.ps = ps_new;
		this.nGeneration++;
	}	
	

	/**
	 * Picks a path out of the provided set of paths
	 * according to the rank. The path with the highest 
	 * fitness is placed on first rank (index 0).
	 * @param list ArrayList of paths
	 * @param nIndex index of the element in the sorted
	 * 			     ArrayList.
	 * @return
	 */
	private static Path pickRank(ArrayList<Path> list, int nIndex) {
		
		Path aReturn =null;
		ArrayList<Path> clonedList = new ArrayList<Path>(list);
				
		//sort paths according to their fitness in descending order (using lambda)
		clonedList.sort(  (p1, p2) -> Double.compare(p2.getFitness(), p1.getFitness())  );
		
		//get the required path
		aReturn = clonedList.get(nIndex);
		
		return aReturn;
	}
	
	/**
	 * Method used to cross two paths in order
	 * to receive an even better (fitter) path.
	 * @param p1 first parent path
	 * @param p2 second parent path
	 * @return the path resulted from the crossover
	 * 		  of the two parent paths
	 */
	private static Path cross(Path p1, Path p2) {
		
		Path aReturn = null;
		
                //tamanho do p1
		int nLength = p1.getLength();
                //tamanho da carga genetica vidna do p1
                int nSequenceLenght = (int) (nLength * Math.random());
                
		//set minimum length
		if(nSequenceLenght == 0) { 
			nSequenceLenght = 2;
		}	
		//decrease length
		if(nSequenceLenght == nLength) { 
			nSequenceLenght -= 2;
		}
		
		
		//define start index randomly
                /* nLenght -> indice do ultimo vertice
                 * Volta nSequenceLenght vértices para trás     ps: nSequenceLenght<nLenght
                 * Escolhe um indice cujo valor máximo é nLength-nSequenceLenght
                 */
		int nStartIndex = (int) ((nLength-nSequenceLenght) * Math.random());
		
		//initiate a new order/path (child path)
		int[] order = new int[nLength]; //all paths have same length = number of vertices
		
		//put -1 into each slot as a placeholder
		for (int i=0 ; i<nLength ; i++) {
			order[i] = -1;
		}
		
		
		//fill in genome of first parent1 path
		for(int i=nStartIndex ; nSequenceLenght>0 ; i++) {
			
			order[i] = p1.get(i);
			nSequenceLenght--;//preenche com a sequencia de genomas do pai 1
		}
		
		//restante dos genomas -> p2
		//fill in genome of second parent2 path
		int n=0;
		for (int i=0 ; i<nLength ; i++) { //caminha pelo filho
			if (order[i] == -1) { //espaço sem genoma
				
				//fillable slot found
				boolean lExit=false;				
				while(!lExit) {
					
					//check if vertex-index already included
					if (Path.contains(order, p2.get(n))) {
						n++;//itera valor de n até achar um genoma do pai 2 não presente no filho
					} else {
						lExit = true;
					}
					
				}
				//preenche genoma vazio com genoma do pai 2
				order[i] = p2.get(n);
			}
		}
		
		aReturn = new Path(order);
		//retorna novo filho	
		return aReturn;
	}

	
	/**
	 * Method used to get detailed information about a 
	 * population in form of a multiline string. 
	 * @return Accumulates the info string of each
	 * 		  path stored in this population
	 * 		  retrieved by the Path-method
	 * 		  .getInfo().
	 * 		 
	 */
	public String getDetailedInfo() {
		
		String cReturn="";
		
		for(int i=0 ; i<this.ps.size() ; i++) {
			Path p = this.ps.get(i);
			cReturn = cReturn + p.getInfo() + "\n";
		}
		
		return cReturn;
	}
	
	
	/**
	 * Method used to get information about a 
	 * population in form of a one line string. 
	 * @return the populations generation number
	 * 		  and the current lowest distance achieved
	 * 		  by the population's current fittest path
	 * 		  as a one line info string 		 
	 */
	public String getInfo() {
		
		String cReturn = "best distance in population: ";
		
		//double nCurrentLowestDistance = this.vs.getTotalDistanceWGS(this.getFittestPath());
		//cReturn = cReturn + String.format("%.4f", nCurrentLowestDistance) ;
			
		return cReturn;
	}
	
	
	
}