package Proyecto3_Arboles.BST;


/**
* @author Prodesores ED (Español)
* @version 2023-24 distribuible
*/
public class BSTree <T extends Comparable<T>>{

// nodo ra�z del �rbol
private BSTNode<T> raiz;

public BSTree() {
	this.raiz = null;
}

/**
 * getter del nodo raiz del arbol
 */
protected BSTNode<T> getRoot() {
	return this.raiz;
}

/**
 * setter del nodo raiz del arbol
 */
protected void setRoot(BSTNode<T> nodo) {
	this.raiz = nodo;
}


/**
 * Se le pasa el objeto comparable que hay que insertar
 * devuelve true si lo inserta
 * Si ya existe, no lo inserta y devuelve false (implementado mas tarde). 
 * Si recibe un nodo nulo, no lo inserta y lanza una NullPointerException
 */
public boolean addNode(T nodo) {
	if (nodo == null) throw new NullPointerException();
	if (searchNode(nodo) != null) return false;
	if (raiz == null) {
		setRoot(new BSTNode<T>(nodo));
		return true;
	}
	
	if (nodo.compareTo(raiz.getInfo()) < 0) {
		if (raiz.getLeft() == null) {
			raiz.setLeft(new BSTNode<T>(nodo));
			return true;
		}
		return addNodeR(raiz.getLeft(), nodo);
	}
	else {
		if (raiz.getRight() == null) {
			raiz.setRight(new BSTNode<T>(nodo));
			return true;
		}
		return addNodeR(raiz.getRight(), nodo);
	}
}

private boolean addNodeR (BSTNode<T> hijo, T nodo) {
	
	if (nodo.compareTo(hijo.getInfo()) < 0) {
		if (hijo.getLeft() == null) {
			hijo.setLeft(new BSTNode<T>(nodo));
			return true;
		}
		return addNodeR(hijo.getLeft(), nodo);
	}
	else{
		if (hijo.getRight() == null) {
			hijo.setRight(new BSTNode<T> (nodo));
			return true;
		}
		return addNodeR(hijo.getRight(), nodo);
	}
}


/**
* Se le pasa el objeto que se quiere borrar que coincida con equals
* Si recibe un nodo nulo, lanza una NullPointerException
* Devuelve true si lo ha borrado, false caso contrario
*/
public boolean removeNode (T node) {
	if (node == null) throw new NullPointerException();
	if (raiz == null) return false;
	if (searchNode(node) == null) return false;
	removeNodeR(raiz , node);
	return true;
}

public BSTNode<T> removeNodeR(BSTNode<T> actual, T nodo) {
	if (actual.getInfo().compareTo(nodo) > 0) {
		actual.setLeft(removeNodeR(actual.getLeft(), nodo));
	}
	else if (actual.getInfo().compareTo(nodo) < 0) {
		actual.setRight(removeNodeR(actual.getRight(), nodo));
	}
	else {
		BSTNode<T> l = actual.getLeft();
		BSTNode<T> r = actual.getRight();
		
		if (l == null && r == null) return null;
		else if (l == null || r == null) 
			return (l==null) ? r : l;
		else 
			actual.setInfo(getMaxR(actual.getLeft()).getInfo());
			actual.setLeft(removeNodeR(actual.getLeft(), actual.getInfo()));
	}
	return actual;
}

/**
* Se le pasa el objeto que se quiere borrar que coincida con equals
* Si recibe un nodo nulo, lanza una NullPointerException
* Devuelve true si lo ha borrado, false caso contrario
*/


private BSTNode<T> getMaxR(BSTNode<T> nodo) {
	if (nodo.getRight() == null) {
		return nodo;
	}
	else {
		return getMaxR(nodo.getRight());
	}
}


/**
* Se le pasa un objeto comparable que se busca
* Devuelve el objeto encontrado que cumple que es "equals" con el
* buscado, null si no lo encuentra por cualquier motivo
*/
public BSTNode<T> searchNode(T clave) {
	if (clave == null) return null;
	if (raiz == null) return null;
	return searchRecursivo (raiz, clave);
}

private BSTNode<T> searchRecursivo (BSTNode<T> nodo, T clave) {
	if (nodo == null) return null;
	if (clave.compareTo(nodo.getInfo()) == 0) {
		return nodo;
	} else if (clave.compareTo(nodo.getInfo()) < 0) {
		return searchRecursivo(nodo.getLeft(), clave);
	}
	else {
		return searchRecursivo(nodo.getRight(), clave);
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

private void preOrderR(BSTNode<T> nodo, StringBuilder result) {
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

private void postOrderR(BSTNode<T> nodo, StringBuilder result) {
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
	String result = "";
	if (raiz != null)
		return inOrderR(raiz);
	else
		return result;
}

private String inOrderR (BSTNode<T> nodo) {
	String result = "";
	if (nodo != null) {
		if (nodo.getLeft() != null) {
			result += inOrderR(nodo.getLeft()) + "\t";
		}
		result += nodo.getInfo() + "\t" ;
		if (nodo.getRight() != null) {
			result += inOrderR(nodo.getRight())+"\t";
		}
	}
	return result.strip();
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
public String tumbarArbol(BSTNode<T> p, int esp) {
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