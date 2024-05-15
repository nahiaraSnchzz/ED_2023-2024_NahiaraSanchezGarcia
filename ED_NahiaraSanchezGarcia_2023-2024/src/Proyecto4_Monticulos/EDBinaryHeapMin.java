package Proyecto4_Monticulos;

/**
 * @author Profesores ED
 * @version 2023-24 distribuible
 */
 public class EDBinaryHeapMin<T extends Comparable<T>> implements EDPriorityQueue<T>{
	protected T [] monticulo;
	protected int numElementos;

	
	@SuppressWarnings("unchecked")
	public EDBinaryHeapMin(int numMaxElementos) {
		monticulo = (T[]) new Comparable[numMaxElementos];
		numMaxElementos = 0;
	}
	
	/**
	 * Se le pasa el elemento que se quiere insertar en la cola
	 * Lanza NullPointerException si el elemento a insertar es null
	 * devuelve true si consigue insertarlo, false en caso contrario
	 */
	@Override
	public boolean add(T elemento) {
		if (elemento == null) throw new NullPointerException();
		if (numElementos == monticulo.length) return false;
		monticulo[numElementos] = elemento;
		filtradoAscendente(numElementos);
		numElementos++;
		return true;
	}
	
	/** 
	 * devuelve y elimina el elemento con mayor prioridad (cima del monticulo), o null si no hay elementos
	 */
	@Override
	public T poll() {
		if (isEmpty()) return null;
		T primerElemento = monticulo[0];
		monticulo[0] = monticulo[--numElementos];
		filtradoDescendente(0);
		return primerElemento;
	}

	@Override
	public boolean remove(T elemento) {
		if (elemento == null) throw new NullPointerException();
		if (isEmpty()) return false;
		int posicionEliminar = searchElement(elemento);
		if (posicionEliminar == -1) return false;
		T anterior = monticulo[posicionEliminar];
		monticulo[posicionEliminar] = monticulo[--numElementos];
		if (monticulo[posicionEliminar].compareTo(anterior) == 0) {
			return true;
		}
		if (monticulo[posicionEliminar].compareTo(anterior)<0) {
			filtradoAscendente(posicionEliminar);
		}
		else if (monticulo[posicionEliminar].compareTo(anterior) > 0) {
			filtradoDescendente(posicionEliminar);
		}
		return true;
		
	}
	
	public int searchElement (T elemento) {
		for (int i=0; i < numElementos; i++) {
			if (monticulo[i].compareTo(elemento) == 0) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Indica si no hay ningun elemento
	 */
	@Override
	public boolean isEmpty() {
		return numElementos == 0;
	}
	
	/**
	 * Elimina todos los elementos de la cola
	 */
	@Override
	public void clear() {
		numElementos = 0;
	}

	/**  
	 * Devuelve una cadena con la informacion de los elementos que contiene el  
	 * monticulo separados por tabuladores
	*/
	@Override
	public String toString() {
		String str = "";
		for (int i=0; i < numElementos; i++) {
			str += monticulo[i] + "\t";
		}
		// quita el tab del final
		return str.strip();
	
	}

	@Override
	public boolean cambiarPrioridad(int pos, T elemento) {
		if (elemento == null) throw new NullPointerException();
		if (pos >= numElementos || pos < 0) return false;
		if (isEmpty()) return false;
		T anterior = monticulo[pos];
		monticulo[pos] = elemento;
		if (elemento.compareTo(anterior) ==0) return false;
		else if (elemento.compareTo(anterior) < 0) filtradoAscendente(pos);
		else if (elemento.compareTo(anterior) > 0) filtradoDescendente(pos);
		return true;
	}
    

    /**
     * Realiza una filtrado ascendente de minimos en el monticulo
     * 
     * Se le pasa el indice del elemento a filtrar
     */
    protected void filtradoAscendente(int pos) {
    	int padre = (pos-1)/2;
		// es el padre menor que el hijo?
		while (padre >= 0 && monticulo[pos].compareTo(monticulo[padre]) < 0) {
			T aux = monticulo[padre];
			monticulo[padre] = monticulo[pos];
			monticulo[pos] = aux;
			pos = padre;
			// hay que actualizar la posicion del padre.
			
			padre = (pos-1)/2;
		}
    }

    /**
     * Realiza una filtrado descendente de minimos en el monticulo
     * 
     * Se le pasa el indice del elemento a filtrar
     */
    protected void filtradoDescendente(int pos) {
    	int hijo = getMinHijo(pos);
    	while (hijo != -1 && monticulo[pos].compareTo(monticulo[hijo]) > 0) {
    		T aux = monticulo[hijo];
    		monticulo[hijo] = monticulo[pos];
    		monticulo[pos] = aux;
    		pos = hijo;
    		hijo = getMinHijo(pos);
    	}
    }
	
	/**
	 * Metodo que calcula y devuelve la posicion del hijo menor del nodo pasado
	 * como parametro.
	 * 
	 * @param pos  La posicion del nodo padre
	 * @return  La posicion del menor de sus hijos o -1 si no existe ninguno
	 */
	public int getMinHijo(int pos) {
		
		int elementoHijoIzq = 2*pos+1;
		int elementoHijoDcho = 2*pos+2;
		if (elementoHijoIzq >= numElementos) return -1;
		else if (elementoHijoDcho >= numElementos && elementoHijoIzq < numElementos) {
			return elementoHijoIzq;
		}
		else {
			if (monticulo[elementoHijoIzq].compareTo(monticulo[elementoHijoDcho])<0) {
				return elementoHijoIzq;
			}
			else {
				return elementoHijoDcho;
			}
		}
		
	}

}
