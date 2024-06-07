package Proyecto4_Monticulos;

/**
 * @author Profesores ED
 * @version 2023-24 distribuible
 */
 public class EDBinaryHeapMin<T extends Comparable<T>> implements EDPriorityQueue<T>{
	
	 
		private T[] monticulo;
		private int numElementos;

		@SuppressWarnings("unchecked")
		public EDBinaryHeapMin(int tamano) {

			monticulo = (T[]) new Comparable[tamano];
			this.numElementos = 0;
		}

		/**
		 * Añade un elemento al final del monticulo y despues hace un filtrado
		 * ascendente.
		 */
		@Override
		public boolean add(T elemento) {
			if (elemento == null)
				throw new NullPointerException();
			if (numElementos == monticulo.length)
				return false;

			monticulo[numElementos] = elemento;

			filtradoAscendente(numElementos);
			numElementos++;
			return true;
		}

		/**
		 * Saca el primer elemento del monticulo. Coloca el ultimo elemento en la
		 * primera posicion y realiza un filtrado descendente.
		 */
		@Override
		public T poll() {
			if (isEmpty()) return null;
			T primerElem = monticulo[0];
			
			monticulo[0] = monticulo[numElementos - 1];
			numElementos--;
			filtradoDescendente(0);

			return primerElem;

		}

		/**
		 * Borra un elemento. Coloca el ultimo elemento del monticulo en la posicion del
		 * elemento a borrar y hace un filtrado.
		 */
		@Override
		public boolean remove(T elemento) {

			if (elemento == null)
				throw new NullPointerException();
			if (numElementos == 0)
				return false;

			int posicionEliminar = searchElement(elemento);

			if (posicionEliminar == -1)
				return false;

			T ultimoElem = monticulo[numElementos - 1];

			monticulo[posicionEliminar] = ultimoElem;

			monticulo[numElementos - 1] = null;

			if (ultimoElem.compareTo(elemento) == 0) {
				numElementos--;
				return true;
			}
			else if (ultimoElem.compareTo(elemento) < 0)
				filtradoAscendente(posicionEliminar);
			else
				filtradoDescendente(posicionEliminar);

			numElementos--;
			return true;
		}

		/**
		 * Busca el elemento y devuelve su posicion en el monticulo. Si no existe
		 * devuelve -1
		 * 
		 * @param elemento a buscar
		 * @return
		 */
		public int searchElement(T elemento) {
			for (int i = 0; i < numElementos; i++) {
				if (monticulo[i].compareTo(elemento) == 0) {
					return i;
				}
			}
			return -1;
		}

		@Override
		public boolean isEmpty() {
			return numElementos == 0;
		}

		@Override
		public void clear() {
			numElementos = 0;
		}

		/**
		 * Cambia un elemento del monticulo. Al hacer esto, hay que realizar un filtrado
		 * ascendente o descendente.
		 * 
		 * Devuelve true si la prioridad cambia; false en caso contrario.
		 */
		@Override
		public boolean cambiarPrioridad(int pos, T elemento) {
			if (elemento == null)
				throw new NullPointerException();
			if (pos >= numElementos || pos < 0)
				return false;
			if (isEmpty())
				return false;

			T anterior = monticulo[pos];
			monticulo[pos] = elemento;

			if (anterior.compareTo(monticulo[pos]) == 0)
				return false;
			else if (anterior.compareTo(monticulo[pos]) > 0) {
				filtradoAscendente(pos);
			} else {
				filtradoDescendente(pos);
			}

			return true;
		}

		/**
		 * Realiza un filtrado ascendente. Si el hijo es menor que el padre, los
		 * intercambia.
		 * 
		 * @param posicionActual
		 */
		public void filtradoAscendente(int posicionActual) {
			int posicionPadre = (posicionActual - 1) / 2;
			// mientras que el hijo sea menor que el padre
			while (posicionPadre >= 0 && monticulo[posicionActual].compareTo(monticulo[posicionPadre]) < 0) {
				T padre = monticulo[posicionPadre];
				monticulo[posicionPadre] = monticulo[posicionActual];
				monticulo[posicionActual] = padre;

				posicionActual = posicionPadre;
				posicionPadre = (posicionActual - 1) / 2;

			}
		}

		/**
		 * Realiza un filtrado descendente
		 * 
		 * @param posicionPadre
		 */
		public void filtradoDescendente(int posicionPadre) {
			int posicionHijo = getMinHijo(posicionPadre);

			while (posicionHijo != -1) {
				if (monticulo[posicionPadre].compareTo(monticulo[posicionHijo]) > 0) {
					// intercambia hijo y padre
					T padre = monticulo[posicionPadre];
					monticulo[posicionPadre] = monticulo[posicionHijo];
					monticulo[posicionHijo] = padre;

					posicionPadre = posicionHijo;
					posicionHijo = getMinHijo(posicionPadre);
				}
				// si el hijo es mayor que el padre para de iterar
				else {
					break;
				}

			}

		}

		/**
		 * Devuelve el menor de los hijos de la posicion padre. Devuelve -1 si no
		 * existen los hijos
		 * 
		 * @param posicionPadre
		 * @return
		 */
		private int getMinHijo(int posicionPadre) {
			int posHijoIzq = 2 * posicionPadre + 1;
			int posHijoDcho = 2 * posicionPadre + 2;

			if (posHijoIzq >= numElementos)
				return -1;
			// si el derecho esta fuera del array y el izq esta dentro, devuelve el izq
			else if (posHijoDcho >= numElementos && posHijoIzq < numElementos) {
				return posHijoIzq;
			}
			// si los dos hijos existen
			else {
				if (monticulo[posHijoIzq].compareTo(monticulo[posHijoDcho]) < 0) {
					return posHijoIzq;
				} else {
					return posHijoDcho;
				}
			}
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

}
