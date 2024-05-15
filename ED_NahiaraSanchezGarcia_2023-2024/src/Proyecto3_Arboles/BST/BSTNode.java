package Proyecto3_Arboles.BST;

/** 
* @author Prodesores ED (Español) 
* @version 2023-24 
*/ 
public class BSTNode <T extends Comparable<T>>{ 
// contenido del nodo que ser� de tipo gen�rico
private T info; 
// nodo hijo izquierdo
private BSTNode<T> left;
// nodo hijo derecho.
private BSTNode<T> right; 
 
 
/** 
* Se le pasa un objeto comparable 
*/ 
public BSTNode (T clave) {
	this.info = clave;
	this.left = null;
	this.right = null;
} 
 
 
/** 
* Se le pasa la información que se quiere meter en el nodo 
*/ 
protected void setInfo(T info) {
	this.info = info;
} 
 
 
/** 
* Devuelve la información que almacena el nodo 
*/ 
public T getInfo() {
	return this.info;
} 
 
 
/** 
* Se le pasa el nodo que se quiere enlazar en el subárbol izquierdo 
*/ 
protected void setLeft(BSTNode<T> left){
	this.left = left;
} 
 
 
/** 
* Se le pasa el nodo que se quiere enlazar en el subárbol derecho 
*/ 
protected void setRight(BSTNode<T> right){
	this.right = right;
} 
 
 
/** 
* Devuelve el subárbol izquierdo 
*/ 
public BSTNode<T> getLeft () {
	return this.left;
} 
 
 
/** 
* Devuelve el subárbol derecho 
*/ 
public BSTNode<T> getRight () {
	return this.right;
} 
 
 
/**
	 * Metodo que devuelve una cadena con la información del BTSNode
	 * 
	 * @return  Un String que representa al objeto BTSNode
	 */
public String toString() { 
return info.toString(); 
}  
} 