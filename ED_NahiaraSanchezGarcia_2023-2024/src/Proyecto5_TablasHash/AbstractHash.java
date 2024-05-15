package Proyecto5_TablasHash;

/**
 * @author Profesores ED
 * @version 2023-24 distribuible
 *
 * @param <T>
 */
public abstract class AbstractHash <T>{
	

	/**
	 * Devuelve el numero de elementos que contiene la tabla Hash en el momento de la invocacion
	 */
	abstract public int getNumOfElems();


	/**
	 * Devuelve el tama�o de la tabla Hash
	 */
	abstract public int getSize();
	
	/**
	 * Inserta un nuevo elemento en la tabla hash (que se le pasa) y devuelve true si lo ha insertado 
	 * o false en caso contrario (si no encuentra sitio). Y NullPointer si el elemento es null
	 */
	abstract public boolean  add(T elem);

	/**
	 * Busca y devuelve el elemento (igual al que se le pasa) encontrado en la tabla hash  
	*  o null si no lo encuentra
	*/
	abstract public T find(T elem);

	/**
	 * Borra un elemento (igual al que se le pasa) que se encuentra en la tabla hash 
	 * Devuelve true si lo ha borrado o false en caso contrario (no existe). Y NUllPointer si el elemento es null
	 */
	abstract public boolean remove(T elem);

	/**
	 * Realiza una redispersion (aumentando el tama�o) al numero primo mayor que el doble del tama�o actual, 
	 *  recolocando los elementos, e indicando si lo realiza correctamente o no 
	 */
	abstract protected boolean reDispersion ();

	/**
	* Realiza una redispersion inversa (disminuyendo el tama�o) al n�mero primo menor que la mitad del tama�o actual, 
	* recolocando los elementos, e indicando si lo realiza correctamente o no 
	*/
	abstract protected boolean inverseReDispersion();

	/**
	* Convierte la tabla a una String que se pueda mostrar de forma "visible" 	 
	*/
	abstract public String toString ();

	/**
	 * Calcula el resultado de aplicar la funcion hash basada en modulo, sobre el parametro  
	 * Utiliza hashCode() y convierte posibles negativos a positivos 
	 */
	protected int fHash(T elem){ // fHash basada en MODULO... 
		int position=elem.hashCode()%getSize(); 
		return position<0 ? position+getSize() : position; 
	} 

	/**
	  * 
	  * Calcula si un numero positivo es un numero primo, los negativos devuelve que no
	  * 
	 * @param n El numero que queremos comprobar
	 * @return true si es primo, false en caso contrario
	 */
	protected boolean isPositivePrime(int n){
		if (n<2 || (n>2 && n%2==0))
			 return false;
		if (n<=7)
			 return true;
		for (int i=3;i*i<=n;i+=2) //impares
			 if (n%i==0)
				 return false; // no es primo
		return true;
	 }
	 
	 /**
	 * @param n  El numero de partida
	 * @return Si el numero es primo, el mismo numero; y sino, el primer primo encontrado MAYOR que el numero de partida
	 */
	protected int nextPrimeNumber(int n){
		if (n<=3) 
			return 3; // No permite primos menores de 3
		for (;!isPositivePrime(n);n++);
		return n;
	 }
	 
	 /**
	 * @param n El numero de partida
	 * @return Si el numero es primo, el mismo numero; y sino, el primer primo encontrado MENOR que el numero de partida
	 */
	protected int previousPrimeNumber (int n){
		if (n<=3) // No permite primos menores de 3
			return 3;
		for(;!isPositivePrime(n);n--);
		return n;
	 }
	
	protected double factorDeCarga()
	{
		return getNumOfElems()*1.0/getSize();
	}

}
