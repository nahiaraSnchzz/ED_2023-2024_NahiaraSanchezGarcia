package Proyecto5_TablasHash;

/**
 * @author Profesores ED
 * @version 2023-24 distribuible
 *
 * @param <T>
 */
public class HashNode<T> {
	private T info;
	private int status;

	
	public static final int BORRADO = -1;
	public static final int VACIO = 0;
	public static final int LLENO = 1;

	public HashNode () {
		this.info = null;
		this.status = VACIO;
	}
	
	public T getInfo() {
		return info;
	}
	
	public void remove (){
		this.status = BORRADO;
		// lo que tenga info da igual
	}
	
	public void setInfo(T elem){
		info = elem;
		status = LLENO;	
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public String toString (){
		StringBuilder cadena=new StringBuilder("{");
		switch (getStatus()){
		case LLENO:
			cadena.append(info);
			break;
		case VACIO:
			cadena.append("_E_");
			break;
		case BORRADO:
			cadena.append("_D_");
		}
		cadena.append("}");
		return cadena.toString();
	}

}
