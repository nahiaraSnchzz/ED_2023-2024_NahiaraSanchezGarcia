package Proyecto2_Grafos.GrafosTG;
import java.text.DecimalFormat;


public class GraphTG2023<T> {
	


	/**
	 * Constante infinito
	 */
	protected static final double Inf=Double.POSITIVE_INFINITY;
	
	/**
	 * Vector de nodos
	 */
	protected T[] nodes; // Vector de nodos
	/**
	 * Matriz de aristas
	 */
	protected boolean[][] edges; // matriz de aristas
	/**
	 * Matriz de pesos
	 */
	protected double[][] weights; // matriz de pesos

	/**
	 * Numero de elementos en un momento dado
	 */
	protected int numNodes; // numero de elementos en un momento dado

	
	/**
	 * 
	 * @param tam = Numero maximo de nodos del grafo
	 */
	@SuppressWarnings("unchecked")
	public GraphTG2023(int tam) {
		nodes = (T[]) new Object[tam];
		numNodes = 0;
		edges = new boolean[tam][tam];
		weights = new double[tam][tam];
	}

	public int getNumMaxNodes() {
		return nodes.length;
	}
	
	protected int getNumNodes() {
		return numNodes;
	}

	protected T[] getNodes() {
		return nodes;
	}

	protected boolean[][] getEdges() {
		return edges;
	}

	protected double[][] getWeights() {
		return weights;
	}

	protected double[][] getWeight() { // para compatibilidad con pruebas ingles
		return getWeights();
	}


	/**
	 * Obtiene el indice de un nodo en el vector de nodos
	 * 
	 * @param node que es el nodo que se busca
	 * @return la posicion del nodo en el vector, -1 si no se encuentra
	 */
	protected int getNodeEx(T node) {
		for (int i = 0; i < numNodes; i++) {
			if (nodes[i].equals(node)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Inserta un nuevo nodo que se le pasa como parametro.
	 * Siempre lo inserta, no se controlan casos en que no lo pueda hacer
	 * 
	 * @param node el nodo que se quiere insertar
	 * @return true siempre
	 */
	public boolean addNodeEx(T node) {
		nodes[numNodes] = node;
		for (int i = 0; i <= numNodes; i++) {
			edges[numNodes][i] = false;
			edges[i][numNodes] = false;
		}
		numNodes++;
		return true;
	}


	/**
	 * Inserta una arista entre dos nodos con el peso indicado 
	 * Devuelve true siempre
	 * No comprueba nada. 
	 * @param source
	 *            nodo origen
	 * @param target
	 *            nodo destino
	 * @param edgeWeight
	 *            peso de la arista
	 * @return true siempre
	 */
	public boolean addEdgeEx(T source, T target, double edgeWeight) {
		int posOrigen = getNodeEx(source);
		int posDestino = getNodeEx(target);
		edges[posOrigen][posDestino] = true;
		weights[posOrigen][posDestino] = edgeWeight;
		return true;
	}

	/**
	 * Borra la arista del grafo que conecta dos nodos.
	 * Siempre la borra sin comprobar nada
	 * 
	 * @param source Nodo origen de la arista
	 * @param target Nodo destino de la arista
	 * @return true siempre
	 */
	public boolean removeEdgeEx(T source, T target) {
		int posOrigen = getNodeEx(source);
		int posDestino = getNodeEx(target);
		edges[posOrigen][posDestino] = false;
		return true;
	}

	/**
	 * Devuelve el peso de la arista que conecta dos nodos.
	 * No comprueba nada...
	 *  
	 * @param source Nodo origen de la arista
	 * @param target Nodo destino de la arista
	 * @return El peso de la arista
	 */
	public double getEdgeEx(T source, T target) {
		int posOrigen = getNodeEx(source);
		int posDestino = getNodeEx(target);
		return weights[posOrigen][posDestino];
	}
	
	/**
	 * Devuelve la excentricidad del nodo que se le pasa por par�metro.
	 * si nodo es null lanza nullPointerException.
	 * Si el nodo no existe devuelve -1.
	 * @param element
	 * @return
	 */
	public double excentricidad (T element) {
		if (element == null) throw new NullPointerException ("El nodo no puede ser null");
		if (getNodeEx(element) == -1) return -1;
		return numNodes;
		
		
	}
	
	/**
	 * Devuelve la posici�n del nodo que es centro de un grafo.
	 * @return
	 */
	public int centroGrafo() {
		return numNodes;
		
	}
	
	/**
	 * Devuelve la longitud del camino m�s corto entre nodo origen y destino.
	 * Si alguno de los nodos no existe o es null devuelve -1.
	 * @param origen
	 * @param destino
	 * @return
	 */
	public double shortestPathLength(T origen, T destino) {
		if (origen == null || destino == null) return -1;
		
		int posOrigen = getNodeEx(origen);
		int posDestino = getNodeEx(destino);
		
		for (int pivote =0 ; pivote < getNumNodes(); pivote++) {
			if (weights[posOrigen][pivote] + weights[pivote][posDestino] < weights[posOrigen][posDestino]) {
				weights[posOrigen][posDestino] = weights[posOrigen][pivote] + weights[pivote][posDestino];
			}
		}
		
		return weights[posOrigen][posDestino];
	}
	
	/**
	 * Devuelve true si el grafo contiene ciclos, false en caso contrario.
	 * @return
	 */
	public boolean containsCycles() {
		for (int i = 0; i < getNumNodes() ; i++) {
			if (getEdgeEx(nodes[i], nodes[i]) != -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return Devuelve un String con la informacion del grafo usando StringBuilder
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		StringBuilder cadena = new StringBuilder();
		
		cadena.append("NODES\n");
		for (int i = 0; i < numNodes; i++) {
			cadena.append(nodes[i].toString() + "\t");
		}
		cadena.append("\n\nEDGES\n");
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				if (edges[i][j])
					cadena.append("T\t");
				else
					cadena.append("F\t");
			}
			cadena.append("\n");
		}
		cadena.append("\nWEIGHTS\n");
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {

				cadena.append((edges[i][j]?df.format(weights[i][j]):"-") + "\t");
			}
			cadena.append("\n");
		}
	
		return cadena.toString();
	}
	
}
