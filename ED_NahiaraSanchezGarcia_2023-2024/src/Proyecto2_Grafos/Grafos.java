package Proyecto2_Grafos;

import java.text.DecimalFormat;

import Proyecto2_Grafos.Excepciones.ElementNotPresentException;
import Proyecto2_Grafos.Excepciones.FullStructureException;



public class Grafos<T> {

	private double[][] weights; // pesos de la arista
	private boolean[][] edges; // matriz de aristas
	private T[] nodes;			// nodos
	private int size;			// número de nodos.


	private double[][] floydA;	// matriz A de floyd (matriz que almacena el coste de ir un nodo a otro)
	private int[][] floydP;		// matriz P de floyd (matriz que almacena los nodos pivote que hacen que el camino sea mínimo).
	
	private String cadP;	
	private String cad;

	/**
	 * Crea un grafo.
	 * 
	 * @param dimension
	 */
	@SuppressWarnings("unchecked")
	public Grafos(int dimension) {
		// crea un grafo con la dimension
		nodes = (T[]) new Object[dimension];

		this.weights = new double[dimension][dimension];
		this.edges = new boolean[dimension][dimension];
		size = 0;
	} 

	/**
	 * Devuelve el tama�o del grafo.
	 * 
	 * @return size
	 */
	public int getSize() {
		return this.size;
	}


	/**
	 * Devuelve la posici�n del nodo pasado como par�metro dentro del vector de
	 * nodos. Devuelve -1 si el nodo no existe o es null
	 * 
	 * @param node
	 * @return
	 */
	public int getNode(T node) {
		// si el nodo no existe o es null
		if (node == null || existsNode(node) == false) {
			return -1;
		}
		
		for (int i = 0; i < getSize(); i++) {
			if (nodes[i] == node) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * indica si existe un nodo o no.
	 * 
	 * @param node
	 * @return true si existe; false en caso contrario.
	 */
	public boolean existsNode(T node) {
		for (int i = 0; i < getSize(); i++) {
			if (nodes[i] == node) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Inserta un nuevo nodo que se le pasa como par�metro.
	 * 
	 * @param node
	 * @return si ya existe, no lo inserta y devuelve false. si node es nulo, no lo
	 *         inserta y devuelve false. si no cabe, no lo inserta y devuelve false.
	 *         En caso contrario devuelve true
	 * 
	 * @throws FullStructureException
	 */
	public boolean addNode(T node) throws FullStructureException {
		// si nodo es null
		if (node == null) throw new NullPointerException("El nodo a insertar no puede ser null");
		// si el nodo ya existe
		else if (this.existsNode(node) == true) return false;
		// si size es mayor que length
		else if (getSize() >= nodes.length) throw new FullStructureException("El grafo ya est� completo");
		
		else {
			// size apunta a la siguiente pos del ultimo nodo.
			this.nodes[getSize()] = node;

			for (int i = 0; i < this.getSize(); i++) {
				// inicializa a cero matriz de pesos
				this.weights[i][getSize()] = 0;
				this.weights[getSize()][i] = 0;
				// inicializa a false la matriz de aristas
				this.edges[i][getSize()] = false;
				this.edges[getSize()][i] = false;
			}
			// aumenta size
			size = getSize() + 1;
			return true;
		}
	}

	/**
	 * Si existe, borra el nodo deseado del vector de nodos, as� como las aristas
	 * en las que forma parte y devuelve true. si el nodo no existe devuelve false.
	 * si recibe un nodo nulo, lanza exceci�n
	 * 
	 * @param node
	 * @return true si se ha podido eliminar; false en caso contrario
	 */
	public boolean removeNode(T node) {
		// si nodo es null
		if (node == null) throw new NullPointerException("El nodo a borrar no puede ser null");
		// si no existe el nodo
		if (existsNode(node) == false) return false;
		
		// posición del nodo a borrar
		int pos = getNode(node);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		nodes[pos] = nodes[getSize() -1];

		for (int i = 0; i < getSize(); i++) {
			// borra de la matriz de pesos
			this.weights[i][pos] = weights[getSize() - 1][i];
			this.weights[pos][i] = weights[i][getSize() - 1];
			// borra de la matriz de aristas.
			this.edges[i][pos] = edges[getSize() - 1][i];	// la ultima fila pasa a ser la columna a borrar
			this.edges[pos][i] = edges[i][getSize() - 1];	// la ultima columna pasa a ser la fila a borrar.
		}
  
		size--;
		return true;

	}

	/**
	 * Inserta una arista con el peso indicado (>0) entre dos nodos, uno origen y
	 * otro destino.
	 * 
	 * @param source
	 * @param target
	 * @param weight
	 * @return Devuelve true si inserta la arista. Devuelve false si ya existe la
	 *         arista. Lanza excepción si no existe el nodo origen o destino. Lanza
	 *         excepcion si el peso es invalido
	 */
	public boolean addEdge(T source, T target, double weight) {
		// si el peso es cero o menos
		if (weight < 1) throw new IllegalArgumentException("El peso debe ser mayor de 0");
		// si no existe alguno de los ndos.
		if (existsNode(source) == false || existsNode(target) == false) throw new ElementNotPresentException("Al menos uno de los nodos no existe");
		// si ya existe la arista a borrar.
		if (existsEdge(source, target) == true) return false;
		
		int posSource = getNode(source);
		int posTarget = getNode(target);
		
		// se hace que exista la arista
		this.edges[posSource][posTarget] = true;
		// se añade el peso
		this.weights[posSource][posTarget] = weight;
		return true;
	}

	/**
	 * Devuelve true si existe una arista entre nodos origen y destino. False en
	 * caso contrario o no existen los nodos.
	 * 
	 * @param source
	 * @param target
	 * @return true si sí existe. False en caso contrario.
	 */
	public boolean existsEdge(T source, T target) {
		// si no existe algun nodo
		if (existsNode(source) == false || existsNode(target) == false) return false;
		
		int posSource = getNode(source);
		int posTarget = getNode(target);
		// si no existe la arista.
		if (edges[posSource][posTarget] == false) return false;

		return true;
	}

	/**
	 * Devuelve el peso de la arista que conecta los nodos. Si los nodos no existen,
	 * lanza excepcion. Si existen pero la arista a eliminar no, devuelve -1.
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public double getEdge(T source, T target) {
		int posSource = getNode(source);
		int posTarget = getNode(target);
		// si no existe algun nodo
		if (existsNode(source) == false || existsNode(target) == false) throw new ElementNotPresentException("Al menos uno de los nodos no existe");
		// si no existe la arista
		else if (this.edges[posSource][posTarget] == false) return -1;
		// devuelve el peso de la arista
		return this.weights[posSource][posTarget];
	}

	/**
	 * Borra la arista del gafo que conecta dos nodos.
	 * 
	 * @param source
	 * @param target
	 * @return si la arista existe, se borra y devuelve true. Si los nodos no
	 *         existen, lanza excepcion. Si los dos nodos existen pero la arista a
	 *         elimianr no, devuelve false.
	 */
	public boolean removeEdge(T source, T target) {
		// si no existe algun nodo
		if ((existsNode(source) == false || existsNode(target) == false)) throw new ElementNotPresentException("Al menos uno de los nodos no existe");
		// si no existe la arista
		else if (existsEdge(source, target) == false) return false;
		
		int posSource = getNode(source);
		int posTarget = getNode(target);
		// se hace que no exista arista
		this.edges[posSource][posTarget] = false;
		// el peso se pone a cero
		this.weights[posSource][posTarget] = 0;
		return true;
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public DijkstraDataClass dijkstra(T source) {
		// si el nodo es null o no existe
		if (source == null || !existsNode(source)) return null;
		// crea objeto dijkstra del tamaño size y indice inicial cero
		DijkstraDataClass dijkstra = new DijkstraDataClass(getSize(), 0);
		
		// vector para saber que nodos fueron accedidos
		boolean[] S = new boolean[getSize()];
		// inicializan vectores de dijkstra
		dijkstra.setdDijkstra(inicializaVectorD(getNode(source)));
		dijkstra.setpDijkstra(inicializaVectorP());
		
		// el primer nodo ya esta accedido
		S[getNode(source)] = true;
		
		// pivote (peso menor)
		int w = getPivote(dijkstra.getdDijkstra(), S);
		
		// mientras que el pivote sea distinto de -1
		while (w != -1) {
			// se accede al nodo
			S[w] = true;

			for (int i = 0; i < getSize(); i++) {
				// si no esta accedido, existe el eje y el elemento pivote de D + el peso, es menor que el elemento i de D
				if (!S[i] && edges[w][i] && dijkstra.getdDijkstra()[w] + weights[w][i] < dijkstra.getdDijkstra()[i]) {
					dijkstra.getdDijkstra()[i] = dijkstra.getdDijkstra()[w] + weights[w][i];
					dijkstra.getpDijkstra()[i] = w;
				}

			}
			// nuevo pivote
			w = getPivote(dijkstra.getdDijkstra(), S);

		}
		return dijkstra;

	}
	
	/**
	 * Inicializa la matriz D
	 * @param pos
	 * @return matriz D
	 */
	private double[] inicializaVectorD(int pos) {
		// pos es la posicion del nodo origen
		double[] d = new double[getSize()];
		
		for (int i=0; i < getSize(); i++) {
			d[i] = Double.POSITIVE_INFINITY;
			// de pos a pos es cero
			d[pos] = 0;
			
			// si existe el eje le pone el peso correspondiente.
			if (edges[pos][i]) {
				d[i] = weights[pos][i];
			}
		}
		return d;
	}
	
	/**
	 * inicializa la matriz P a -1
	 * @return matriz P
	 */
	private int[] inicializaVectorP() {
		int[] p = new int[getSize()];
		for (int i= 0; i< getSize();i++) {
			p[i] = -1;
		}
		return p;
	}

	/**
	 * Devuelve la posicion del pivote.
	 * 
	 * @param vectorD
	 * @param S
	 * @return
	 */
	public int getPivote(double[] vectorD, boolean[] S) {
		Double coste = Double.POSITIVE_INFINITY;
		int pivote = -1;
		
		for (int i = 0; i < getSize(); i++) {
			// si no esta accedido, el peso es menor de infinito
			if ( !S[i] && vectorD[i] < coste) {
				
				coste = vectorD[i];
				pivote = i;
			}

		}
		return pivote;
	}

	/***************************************************************************************************************************************************
	 * 
	 **************************************************************************************************************************************************/
	// FLOYD

	/**
	 * M�todo que implementa el algoritmo de Floyd.
	 * 
	 * @return devuelve true si lo aplica; false en caso contrario.
	 */
	public boolean floyd() {
		// si no existe el grafo.
		if (getSize() == 0) return false;
		
		// inicializa vectores de floyd
		floydA = inicializaA();
		floydP= inicializaP();

		
		// si existe el grafo.
		for (int pivote = 0; pivote < getSize(); pivote++) {
			for (int origen = 0; origen < getSize(); origen++) {
				for (int destino = 0; destino < getSize(); destino++) {
					
					// si el eje de origen a pivote + eje de pivote a destino, es menor que de origen a destino directamente:
					if (floydA[origen][pivote] + floydA[pivote][destino] < floydA[origen][destino]) {

						floydA[origen][destino] = floydA[origen][pivote] + floydA[pivote][destino];
						floydP[origen][destino] = pivote;
					}
				}
			}
		}
		return true;

	}
	
	/**
	 * Inicializa matriz A
	 * @return matriz A
	 */
	private double[][] inicializaA() {
		floydA = new double[getSize()][getSize()];
		
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				// si es de un nodo a si mismo
				if (i==j) {
					floydA[i][j] = 0;
				}
				// si existe el eje entre ambos
				else if (edges[i][j]) {
					floydA[i][j] = weights[i][j];
				}
				// si no existe eje
				else {
					floydA[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		return floydA;
	}
	
	/**
	 * Inicializa matriz P a -1
	 * @return matriz P
	 */
	private int[][] inicializaP() {
		floydP = new int[getSize()][getSize()];
		
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				floydP[i][j] = -1;
			}
		}
		return floydP;
	}

	/**
	 *  devuelve la matriz A de Floyd.
	 * 
	 * @return matriz A
	 */
	public double[][] getFloydA() {
		return this.floydA;
	}
	
	/**
	 * devuelve matriz P de floyd
	 * @return matriz P
	 */
	public int[][] getFloydP() {
		return this.floydP;
	}

	/**
	 * Devuelve el coste del camino de coste m�nimo entre origen y destino seg�n
	 * Floyd.
	 *  Si los nodos origen o destino no existen, lanza excepci�n de tipo
	 * ElementNotPresentException
	 * 
	 * @param nodoOrigen
	 * @param nodoDestino
	 * @return coste del camino de coste m�nimo.
	 */
	public double minCostPath(T nodoOrigen, T nodoDestino) {
		// si no existen nodos
		if (!(existsNode(nodoOrigen) || existsNode(nodoDestino))) {
			throw new ElementNotPresentException("No existe al menos un nodo");
		}
		floyd();
		double[][] a = getFloydA();
		
		return a[getNode(nodoOrigen)][getNode(nodoDestino)];
		

	}

	/**
	 * Indica el camino entre los nodos que se le pasan como par�metros con el
	 * formato siguiente:
	 * Origen<tab>(coste0)<tabr>Inter1<tab>(coste1)<tabulador>InterN<tab>(costeN)<tab>Destino<tab>
	 * 
	 * Si no hay camino: Origen<tab>(Infinity)<tab>Destino<tab> 
	 * Si origen y destino coinciden: Origen<tab> 
	 * Si no existen los 2 nodos devuelve una cadena vac�a.
	 * 
	 * @param origen
	 * @param destino
	 * @return
	 */
	public String path(T origen, T destino) {
		floyd();
		int posOrigen = getNode(origen);
		int posDestino = getNode(destino);
		
		if (!(existsNode(origen) && existsNode(destino))) {
			return "";
		}
		if (origen.equals(destino)) {
			return origen.toString() + "\t";
		}
		if (getFloydP()[posOrigen][posDestino] == -1 ) {
			return origen.toString() + "\t(" + Double.POSITIVE_INFINITY + ")\t" + destino.toString()  ;
		}
		return origen.toString() +pathRecursivo (origen, destino) + destino.toString();
	}
	
	private String pathRecursivo (T origen, T destino) {
		int k = floydP[getNode(origen)][getNode(destino)];
		if (k != -1) {
			return pathRecursivo(origen, nodes[k]) + nodes[k].toString() + pathRecursivo(nodes[k], destino);
		}
		else {
			return "\t(" + minCostPath(origen, destino) + ")\t";
		}
	}
	
	public String recorridoProfundidad (T origen) {
		boolean[] visitados = new boolean[getSize()];
		for (int i=0; i < getSize() ; i++) {
			visitados[i] = false;
		}
		cadP = "";
		cad = "";
		if (! existsNode(origen)) return cadP;
		
		//cadP += origen.toString() + profundidadRecursivo(origen, visitados);
		cadP +=  profundidadRecursivo(origen, visitados);
		return cadP;
	}
	
	private String profundidadRecursivo(T origen, boolean[] visitados) {
		
		// cuando nodo ya visitado
		visitados[getNode(origen)] = true;
		cad +=  origen.toString() + "\t";
		
		for (int destino=0; destino< getSize(); destino++) {
			if (visitados[destino] == false && existsEdge(origen, nodes[destino])) {
				
				profundidadRecursivo(nodes[destino], visitados);
			}
		}
		return cad;
	}
	
	
	/**
	 * Imprime el grafo
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		String cadena = "";
		cadena += "NODOS\n";
		for (int i = 0; i < size; i++) {
			cadena += nodes[i].toString() + "\t";
		}
		cadena += "\n\nARISTAS\n";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (edges[i][j])
					cadena += "T\t";
				else
					cadena += "F\t";
			}
			cadena += "\n";
		}
		cadena += "\nPESOS\n";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				cadena += (edges[i][j] ? df.format(weights[i][j]) : "-") + "\t";
			}
			cadena += "\n";
		}
		return cadena;
	}

}
