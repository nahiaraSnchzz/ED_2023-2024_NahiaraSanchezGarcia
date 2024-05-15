package Proyecto3_Arboles.AVL;


/** 
* @author Prodesores ED (Español) 
* @version 2023-24 
*/ 
public class AVLNode <T extends Comparable<T>>{ 
private T info; 
private AVLNode<T> left; 
private AVLNode<T> right; 
private int altura;
private int FB;
 
 
/** 
* Se le pasa un objeto comparable 
*/ 
public AVLNode (T clave) {
	this.info=clave;
	this.left=null;
	this.right=null;
	this.altura = 0;
	this.FB = 0;
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
	return info;
} 
 
 
/** 
* Se le pasa el nodo que se quiere enlazar en el subárbol izquierdo 
*/ 
protected void setLeft(AVLNode<T> left){
	this.left=left;
} 
 
 
/** 
* Se le pasa el nodo que se quiere enlazar en el subárbol derecho 
*/ 
public void setRight(AVLNode<T> right){
	this.right=right;
} 
 
 
/** 
* Devuelve el subárbol izquierdo 
*/ 
public AVLNode<T> getLeft () {
	return left;
} 
 
 
/** 
* Devuelve el subárbol derecho 
*/ 
public AVLNode<T> getRight () {
	return right;
} 




public void actualizaAlturaYFB() {
	
	int rH = (this.right != null) ? getRight().getAltura() +1:0;
	int lH = (this.left != null) ? getLeft().getAltura() +1: 0;
	this.FB = rH - lH;
	this.altura = Integer.max(rH, lH);
}


//public int calcularFB() {
//	int alturaIzquierdo = getLeft().calcularAltura();
//	int alturaDcha = getRight().calcularAltura();
//	return alturaDcha-alturaIzquierdo;
//}

public int getMaxAltura(int alturaIzq, int alturaDcha) {
	int maxAltura = 0;
	if (alturaIzq > alturaDcha) {
		maxAltura = alturaIzq;
		return maxAltura;
	}
	else if (alturaDcha > alturaIzq) {
		maxAltura = alturaDcha;
		return maxAltura;
	}
	else {
		maxAltura = alturaIzq;
		return maxAltura;
	}
}
 
 
public int getAltura() {
	return altura;
}


public int getFB() {
	return FB;
}


public void setAltura(int altura) {
	this.altura = altura;
}


public void setFB(int fB) {
	FB = fB;
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