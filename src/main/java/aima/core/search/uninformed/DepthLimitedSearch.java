package aima.core.search.uninformed;

import aima.core.search.framework.*;
import aima.core.search.framework.problem.Problem;
import aima.core.util.Tasks;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): Figure 3.17, page
 * 88.<br>
 * <br>
 * 
 * <pre>
 * function DEPTH-LIMITED-SEARCH(problem, limit) returns a solution, or failure/cutoff
 *   return RECURSIVE-DLS(MAKE-NODE(problem.INITIAL-STATE), problem, limit)
 *   
 * function RECURSIVE-DLS(node, problem, limit) returns a solution, or failure/cutoff
 *   if problem.GOAL-TEST(node.STATE) then return SOLUTION(node)
 *   else if limit = 0 then return cutoff
 *   else
 *       cutoff_occurred? &lt;- false
 *       for each action in problem.ACTIONS(node.STATE) do
 *           child &lt;- CHILD-NODE(problem, node, action)
 *           result &lt;- RECURSIVE-DLS(child, problem, limit - 1)
 *           if result = cutoff then cutoff_occurred? &lt;- true
 *           else if result != failure then return result
 *       if cutoff_occurred? then return cutoff else return failure
 * </pre>
 * 
 * Figure 3.17 A recursive implementation of depth-limited search.
 *
 * @author Ruediger Lunde
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 */
public class DepthLimitedSearch<S, A> implements SearchForActions<S, A>, SearchForStates<S, A> {

	public static final String METRIC_NODES_EXPANDED = "nodesExpanded";
	public static final String METRIC_PATH_COST = "pathCost";

        public final ArrayList<Node<S, A>> visitedNodes = new ArrayList();
	public final Node<S, A> cutoffNode = new Node<>(null);
	private final int limit;
	private final NodeExpander<S, A> nodeExpander;
	private Metrics metrics = new Metrics();
        Node<S, A> fresult;

        public void addVisitedNode(Node<S, A> inicial){
            visitedNodes.add(inicial);
        }
	public DepthLimitedSearch(int limit, Node<S, A> inicial) {
		this(limit, new NodeExpander<>());
                visitedNodes.add(inicial); //ja adiciona o nó source em visitados
	}

	public DepthLimitedSearch(int limit, NodeExpander<S, A> nodeExpander) {
		this.limit = limit;
		this.nodeExpander = nodeExpander;
	}

	// function DEPTH-LIMITED-SEARCH(problem, limit) returns a solution, or
	// failure/cutoff
	/**
	 * Returns a list of actions to reach the goal if a goal was found, or empty.
	 * The list itself can be empty if the initial state is a goal state.
	 * 
	 * @return if goal found, the list of actions to the goal, empty otherwise.
	 */
	@Override
	public Optional<List<A>> findActions(Problem<S, A> p) {
		nodeExpander.useParentLinks(true);
		Optional<Node<S, A>> node = findNode(p);
                //seearch utils retorna uma lista de acoes ate o no objetivo
		return !isCutoffResult(node) ? SearchUtils.toActions(node) : Optional.empty();
	}

	@Override
	public Optional<S> findState(Problem<S, A> p) {
		nodeExpander.useParentLinks(false);
		Optional<Node<S, A>> node = findNode(p);
		return !isCutoffResult(node) ? SearchUtils.toState(node) : Optional.empty();
	}
	
	public Optional<Node<S, A>> findNode(Problem<S, A> p) {
		clearMetrics();
		// return RECURSIVE-DLS(MAKE-NODE(INITIAL-STATE[problem]), problem,
		// limit)
		Node<S, A> node = recursiveDLS(nodeExpander.createRootNode(p.getInitialState()), p, limit);
		return node != null ? Optional.of(node) : Optional.empty();
	}

	// function RECURSIVE-DLS(node, problem, limit) returns a solution, or
	// failure/cutoff

	/**
	 * Returns a solution node, the {@link #cutoffNode}, or null (failure).
	 */
	private Node<S, A> recursiveDLS(Node<S, A> node, Problem<S, A> problem, int limit) {
              // System.out.println(node);
                
		// if problem.GOAL-TEST(node.STATE) then return SOLUTION(node)
		if (problem.testSolution(node)) {
			metrics.set(METRIC_PATH_COST, node.getPathCost());
			return node;
		} else if (0 == limit || Tasks.currIsCancelled()) {
			// else if limit = 0 then return cutoff
                       
			return cutoffNode;
		} else {
			// else
			// cutoff_occurred? <- false
			boolean cutoffOccurred = false;
			// for each action in problem.ACTIONS(node.STATE) do
			metrics.incrementInt(METRIC_NODES_EXPANDED);
                        //o Expander define o pai do novo no, para que searchUtils aches as acoes linkadas
			for (Node<S, A> child : nodeExpander.expand(node, problem)) {
                            boolean executeChild = true;
                            
                            //verifica se filho ja foi executado
                            for(int j=0; j<visitedNodes.size(); j++){
                                if(visitedNodes.get(j).getState().toString().equals(child.getState().toString()))
                                executeChild=false;
                            }
                            
                           // System.out.println(child);
                            if(executeChild)
                            { 
                                //adiciona child à lista de executados
                                visitedNodes.add(child);
				// child <- CHILD-NODE(problem, node, action)
				// result <- RECURSIVE-DLS(child, problem, limit - 1)
				Node<S, A> result = recursiveDLS(child, problem, limit - 1);
                                
				if (result != null) {
                                    //System.out.println("ACHADO");
					fresult = result;
					return result;
				}
                            }
                            
			}return fresult;
		}
	}

	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	public boolean isCutoffResult(Optional<Node<S, A>> node) {
		return node.isPresent() && node.get() == cutoffNode;
	}

	/**
	 * Returns all the search metrics.
	 */
	@Override
	public Metrics getMetrics() {
		return metrics;
	}

	@Override
	public void addNodeListener(Consumer<Node<S, A>> listener)  {
		nodeExpander.addNodeListener(listener);
	}

	@Override
	public boolean removeNodeListener(Consumer<Node<S, A>> listener) {
		return nodeExpander.removeNodeListener(listener);
	}

	/**
	 * Sets the nodes expanded and path cost metrics to zero.
	 */
	private void clearMetrics() {
		metrics.set(METRIC_NODES_EXPANDED, 0);
		metrics.set(METRIC_PATH_COST, 0);
	}
}
