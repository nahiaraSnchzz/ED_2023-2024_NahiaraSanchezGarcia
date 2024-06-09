package Proyecto5_TablasHash;


/**
 * @author Profesores ED
 * @version 2023-24 distribuible
 *
 * @param <T>
 */
public class ClosedHashTable<T> extends AbstractHash<T> {

	protected HashNode<T> tabla[]; 

	protected int hashSize;	// tamaño de la tabla, debe ser un numero primo
	protected int numElems;	// numero de elementos en la tabla en cada momento.
	
	protected int exploracion; //exploracion que se realizara en caso de colision (LINEAL por defecto)
	
	public static final int LINEAL = 0;	// Tipo de exploracion en caso de colision, por defecto sera LINEAL
	public static final int CUADRATICA = 1;
	public static final int DOBLEHASH = 2;
	
	protected static final double MAXLF = 0.5;
	protected static final double MINLF = 0.16;
	
	private double minlf ;
	private double maxlf;
	
	
	
	@SuppressWarnings("unchecked")
	public ClosedHashTable (int tamano, int expl) {
		// si tamano es primo, ese será el tamaño de la tabla
		this.hashSize = nextPrimeNumber(tamano);
		
		// numero de elementos inicial
		this.numElems = 0;
		
		// crea la tabla
		this.tabla = (HashNode<T>[]) new HashNode[hashSize]; // Crea el array de HashNode's
		
		// inicializa la tabla
		for(int i=0;i<hashSize;i++) {
			tabla[i]=new HashNode<T>();
		}
		
		// exploración por defecto (en caso de error) lineal
		if(expl == 0 || expl == 1 || expl == 2) {
			this.exploracion=expl;
		}
		else {
			this.exploracion=LINEAL;
		}
		
		this.maxlf = MAXLF;
		this.minlf = MINLF;
	}
		
		
	public ClosedHashTable(int tamano, int expl, double max, double min) {
		this(tamano, expl);
		this.maxlf = max;
		this.minlf = min;
	}
	
	
	/**
	 * Añade un elemento a la tabla hash.
	 * 
	 * @param elem --> elemento a añadir.
	 * @return true si añade; false en caso contrario
	 */
	@Override
	public boolean add(T elem) {
			if(elem==null)throw new NullPointerException();
			if(getNumOfElems()==getSize()) return false;
			
			int intentos=0;
			int pos= funcionDispersion(elem,intentos);
		
			while(tabla[pos].getStatus()==HashNode.LLENO ) {
					intentos++;
					pos= funcionDispersion(elem,intentos);
				}
			tabla[pos].setInfo(elem);
			numElems++;
			reDispersion();
			
			return true;
			
	}
	
	
	
	/**
	 * Busca un elemento en la tabla hash
	 * @param elem
	 * @return el elemento en caso de encontrarlo.
	 */
	@Override
	public T find(T elem) {
		if (elem == null) throw new NullPointerException();
		
		int intentos = 0;
		int pos = funcionDispersion(elem, intentos);
		
		// Mientras esté lleno o borrado, busca
		while (tabla[pos].getStatus() != HashNode.VACIO && intentos < getSize()) {
			if (tabla[pos].getInfo().equals(elem)) {
				return tabla[pos].getInfo();
			}
			// calcula la siguiente posicion
			intentos++;
			pos = funcionDispersion(elem, intentos);
		}
		// si no lo encuentra
		return null;
	}


	/**
	 * Elimina un elemento de la tabla hash
	 * @param elem
	 * @return true si lo elimina; false caso contrario
	 */
	@Override
	public boolean remove(T elem) {
		if (elem == null) throw new NullPointerException();
		// si no se encuentra el elemento en la tabla
		if (find(elem) == null) return false;
		
		int intentos = 0;
		int pos = funcionDispersion(elem, intentos);
		
		// mientras esté lleno
		while (tabla[pos].getStatus() != HashNode.VACIO && intentos < getSize()) {
			if (tabla[pos].getStatus() == HashNode.LLENO) {
				if (tabla[pos].getInfo().equals(elem)) {
					// lo marca como borrado
					tabla[pos].setStatus(HashNode.BORRADO);
					numElems--;
					inverseReDispersion();
					return true;
				}
				
			}
			intentos++;
			pos = funcionDispersion(elem, intentos);
			
		}
		// si no lo encuentra
		return false;
	}
	
	
	/**
	 * Encuentra la posición en la que irá el elemento a añadir.
	 * 
	 * @param info --> la clave a añadir.
	 * @param intentos --> el número de intentos realizados para añadir un elemento.
	 * @return
	 */
	protected int funcionDispersion(T info,int intentos) {
		// asigna a la clave info una posición de la tabla hash
		int clave = fHash(info);
		
		if(exploracion==LINEAL) {	
			// f(x) = (h(x) + i) % B
			return (clave+intentos) % getSize();
		}
		else if(exploracion==CUADRATICA) {
			// f(x) = (h(x) + i^2) % B
			return (clave+intentos * intentos) % getSize();
		}
		else if(exploracion==DOBLEHASH){
			// f(x) = (h(x) + i * H2(x)) % B
			// H2(x) = R - h(x) % R
			// siendo R un número primo antecesor de B
			int R=previousPrimeNumber(getSize());
			return (clave+intentos* (R-clave%R)) % getSize();
			
		}
		return 0;
		
	}

	


	/**
	 * Devuelve el número de elementos en la tabla hash
	 * @return numero elementos
	 */
	@Override
	public int getNumOfElems() {
		return numElems;
	}


	/**
	 * Devuelve el tamaño de la tabla Hash
	 * @return tamaño
	 */
	@Override
	public int getSize() {
		return hashSize;
	}




	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected boolean reDispersion() {
		
		double lf = factorDeCarga();
		if (lf >= this.maxlf) {
			
			// copia de la tabla actual
			HashNode<T> aux [] = (HashNode<T>[]) new HashNode[this.hashSize];
			
			for (int i=0; i < aux.length; i++) {
				aux[i] = new HashNode<T>();
			}
			
			for (int i=0; i < this.hashSize; i++) {
				if (tabla[i].getStatus() == HashNode.LLENO) {
					aux[i] = tabla[i];
				}
			}
			
			// modifico el tamaño a uno mayor
			this.hashSize = nextPrimeNumber(this.hashSize*2);
			
			// nuevo tamaño de la tabla
			tabla = (HashNode<T>[]) new HashNode[this.hashSize];
			this.numElems=0;
			
			for (int i=0; i < hashSize; i++) {
				tabla[i] = new HashNode<T>();
			}
			
			// recorro la copia y los añado a la tabla
			for (int i=0; i < aux.length; i++) {
				if (aux[i].getStatus() == HashNode.LLENO) {
					add(aux[i].getInfo());
				}
			}
			// si hizo la redispersión devuelve true
			return true;
		}
		// si no, devuelve false
		return false;
	}


	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected boolean inverseReDispersion() {
		// calcula el factor de carga actual
		double lf = factorDeCarga();
		
		if (lf <= 0.16) {
			
			// se crea una tabla auxiliar copia de la tabla
			@SuppressWarnings("unchecked")
			HashNode<T> aux[] = (HashNode<T>[]) new HashNode[this.hashSize];
			
			for (int i=0; i < hashSize; i++) {
				aux[i] = new HashNode<T>();
			}
			
			
			for (int i=0; i<this.hashSize; i++) {
				if (tabla[i].getStatus() == HashNode.LLENO) {
					aux[i] = tabla[i];
				}
			}
			
			// nuevo tamaño de la tabla
			this.hashSize = previousPrimeNumber(this.hashSize/2);
			
			tabla = (HashNode<T>[]) new HashNode[this.hashSize];
			this.numElems =0;
			
			
			for (int i=0; i < hashSize; i++) {
				tabla[i] = new HashNode<T>();
			}
			
			for (int i=0; i < aux.length; i++) {
				if (aux[i].getStatus() == HashNode.LLENO) {
					add(aux[i].getInfo());
				}
			}
			
			return true;
			
		}
		return false;
	}


	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder cadena=new StringBuilder();
		for (int i=0;i< getSize();i++){
			cadena.append(tabla[i]);
			cadena.append(";");
		}
		cadena.append("[Size: ");
		cadena.append(getSize());
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		cadena.append("]");
		return cadena.toString();
	}
}