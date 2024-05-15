package Proyecto3_Arboles.AVL;
/**
* @author Prodesores ED (Español)
* @version 2023-24 distribuible
*/
public class AVLTree <T extends Comparable<T>>{


private AVLNode<T> raiz;		//nunca debemos perder la raiz, siempre private


/**
 * Constructor
 */
public AVLTree() {
	raiz=null;
}


/**
 * getter del nodo raiz del arbol
 */
protected AVLNode<T> getRoot() {
	return raiz;
}

/**
 * setter del nodo raiz del arbol
 */
protected void setRoot(AVLNode<T> nodo) {
	this.raiz=nodo;
}


/**
 * Se le pasa el objeto comparable que hay que insertar
 * devuelve true si lo inserta
 * Si ya existe, no lo inserta y devuelve false (implementado mas tarde). 
 * Si recibe un nodo nulo, no lo inserta y lanza una NullPointerException
 */
public boolean addNode(T nodo ) {
	if(nodo==null) throw new  NullPointerException();
	if(searchNode(nodo)!=null) return false;
	
	if(raiz==null) setRoot(new AVLNode<T>(nodo));
	else raiz = addNodeR(raiz, nodo);
	
	return true;
	
}



private AVLNode<T> addNodeR(AVLNode<T> nodo,T clave) {
	T info = nodo.getInfo();
	AVLNode<T> l = nodo.getLeft();
	AVLNode<T> r = nodo.getRight();
	
	if(clave.compareTo(info)<0) {
		if(l != null) {
			nodo.setLeft(addNodeR(l,clave));
		}
		else {
			nodo.setLeft(new AVLNode<T>(clave));
		}
	}else {
		if(r != null) {
			nodo.setRight(addNodeR(r, clave));			
		}
		else {
			nodo.setRight(new AVLNode<T>(clave));
		}
	}
	
	return updateBFHeightBalanceo(nodo);
}

/**
 * Reorganiza el subarbol del nodo pasado como parametro si es necesario (cuando su FB
 * @param nodo
 * @return
 */
private AVLNode<T> updateBFHeightBalanceo(AVLNode<T> nodo) {
	nodo.actualizaAlturaYFB();
	int FB = nodo.getFB();
	if (FB == -2) {
		if (nodo.getLeft().getFB() == 1)
			nodo = rotacionDobleIzquierda(nodo);
	
		else 
			return rotacionSimpleIzquierda(nodo);
	}
	else if (FB == 2) {
		if (nodo.getRight().getFB() == -1)
			nodo = rotacionDobleDerecha(nodo);
		else 
			return rotacionSimpleDerecha(nodo);
	}
	return nodo;
}

private AVLNode<T> rotacionSimpleDerecha(AVLNode<T> nodo) {

	AVLNode<T> aux = nodo.getRight();
	nodo.setRight(aux.getLeft());
	nodo.actualizaAlturaYFB();
	
	aux.setLeft(nodo);
	aux.actualizaAlturaYFB();
	
	return aux;
}

private AVLNode<T> rotacionSimpleIzquierda(AVLNode<T> nodo) {
	nodo.actualizaAlturaYFB();
	
	AVLNode<T> aux = nodo.getLeft();
	
	nodo.setLeft(aux.getRight());
	nodo.actualizaAlturaYFB();
	
	aux.setRight(nodo);
	aux.actualizaAlturaYFB();
	
	return aux;
}

private AVLNode<T> rotacionDobleDerecha (AVLNode<T> nodo) {
	nodo.setRight(rotacionSimpleIzquierda(nodo.getRight()));
	return rotacionSimpleDerecha(nodo);
}

private AVLNode<T> rotacionDobleIzquierda (AVLNode<T> nodo) {
	nodo.setLeft(rotacionSimpleDerecha(nodo.getLeft()));
	return rotacionSimpleIzquierda(nodo);
}

public boolean removeNode (T node) {
	if (node == null) throw new NullPointerException();
	if (raiz == null) return false;
	if (searchNode(node) == null) return false;
	
	raiz = removeNodeR(node, raiz);
	return true;
}

public AVLNode<T> removeNodeR(T nodo, AVLNode<T> actual) {
	if (actual.getInfo().compareTo(nodo) > 0) {
		actual.setLeft(removeNodeR(nodo, actual.getLeft()));
	}
	else if (actual.getInfo().compareTo(nodo) < 0) {
		actual.setRight(removeNodeR(nodo, actual.getRight()));
	}
	else {
		AVLNode<T> l = actual.getLeft();
		AVLNode<T> r = actual.getRight();
		
		if (l==null && r==null) return null;
		else if (l==null || r == null)
			return (l==null) ? r : l;
		else
			actual.setInfo(getMaxR(actual.getLeft()));
			actual.setLeft(removeNodeR(actual.getInfo(), actual.getLeft()));
	}
	return updateBFHeightBalanceo(actual);
}

/**
* Se le pasa un objeto comparable que se busca
* Devuelve el objeto encontrado que cumple que es "equals" con el
* buscado, null si no lo encuentra por cualquier motivo
*/
public AVLNode<T> searchNode(T clave) {
	if(clave==null)return null;
	if(raiz==null)return null;
	

	return searchNodeR(raiz,clave);
	
}


private AVLNode<T> searchNodeR(AVLNode<T> nodo, T clave) {
	if(nodo==null)return null;
	
	if(clave.compareTo(nodo.getInfo())==0) {
		return nodo;
	}else if(clave.compareTo(nodo.getInfo())<0) {
		return searchNodeR(nodo.getLeft(),clave);
	}else {
		return searchNodeR(nodo.getRight(),clave);
	}
}

/**
* Genera un String con el recorrido en pre-orden (izquierda-derecha)
* (toString de los nodos separados por tabuladores)
*/
public String preOrder() {
	StringBuilder result = new StringBuilder();
	preOrderR(raiz, result);
	return result.toString().strip();
}

private void preOrderR(AVLNode<T> nodo, StringBuilder result) {
	if (nodo != null) {
		result.append(nodo.getInfo() + ("\t"));
		if (nodo.getLeft() != null) {
			preOrderR(nodo.getLeft(), result);
		}
		if (nodo.getRight() != null) {
			preOrderR(nodo.getRight(), result);
		}
	}
}


/**
* Genera un String con el recorrido en post-orden (izquierda-derecha)
* (toString de los nodos separados por tabuladores)
*/
public String postOrder() {
	StringBuilder result = new StringBuilder();
	postOrderR(raiz, result);
	return result.toString().strip();
}

private void postOrderR(AVLNode<T> nodo, StringBuilder result) {
	if (nodo != null) {
		if (nodo.getLeft() != null) {
			postOrderR(nodo.getLeft(), result);
		}
		if (nodo.getRight() != null) {
			postOrderR(nodo.getRight(), result);
		}
		result.append(nodo.getInfo() + ("\t"));
	}
}


/**
* Genera un String con el recorrido en in-orden (izquierda-derecha)
* (toString de los nodos separados por tabuladores)
*/
public String inOrder() {
	String result = inOrderR(raiz);
	return result.strip();
}

private String inOrderR (AVLNode<T> nodo) {
	String result = "";
	if (nodo != null) {
		result += inOrderR(nodo.getLeft());
		result += nodo.toString()+ ":BF=" +nodo.getFB() + "\t";
		result += inOrderR(nodo.getRight());
	}
	return result;
}



/**
* Se le pasa el objeto que se quiere borrar que coincida con equals
* Si recibe un nodo nulo, lanza una NullPointerException
* Devuelve true si lo ha borrado, false caso contrario
*/


private T getMaxR(AVLNode<T> nodo) {
	if (nodo.getRight() == null) {
		return nodo.getInfo();
	}
	else {
		return getMaxR(nodo.getRight());
	}
}



public String toString() {
	return tumbarArbol(raiz, 0);
}



/**
* Genera un String con el arbol "tumbado" (la raiz a la izquierda y las ramas hacia la derecha)
* Es un recorrido InOrden-Derecha-Izquierda, tabulando para mostrar los distintos niveles
* Utiliza el toString de la informacion almacenada
*
* @param p La raiz del arbol a mostrar tumbado
* @param esp El espaciado en numero de tabulaciones para indicar la profundidad
* @return El String generado
*/
public String tumbarArbol(AVLNode<T> p, int esp) {
StringBuilder cadena = new StringBuilder();

	if (p != null) {
		cadena.append(tumbarArbol(p.getRight(), esp + 1));
		for (int i = 0; i < esp; i++)
			cadena.append("\t");
		cadena.append(p + "\n");
		cadena.append(tumbarArbol(p.getLeft(), esp + 1));
	}
	return cadena.toString();
}


}