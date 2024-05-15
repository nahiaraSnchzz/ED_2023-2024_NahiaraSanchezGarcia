package Proyecto3_Arboles.AVL;

import static org.junit.Assert.*;


import org.junit.Test;

public class PruebasAVLAlumnos {

	@Test
	public void test_Add() {
		AVLTree<Integer> b = new AVLTree<Integer>();
		// Insertar una clave null
		assertThrows(NullPointerException.class, () -> {b.addNode(null);});
		
		
		// 5, 18 10 -- RDD(10)
		assertEquals(true,b.addNode(5));
		assertEquals(true,b.addNode(18));
		assertEquals(true,b.addNode(10));
		assertEquals("5:BF=0\t10:BF=0\t18:BF=0",b.inOrder());
		
		// 40, 50 -- RSD(18)
		assertEquals(true,b.addNode(40));
		assertEquals(true,b.addNode(50));
		assertEquals("5:BF=0\t10:BF=1\t18:BF=0\t40:BF=0\t50:BF=0",b.inOrder());
		
		// 15 -- RDD(10)
		assertEquals(true,b.addNode(15));
		assertEquals("5:BF=0\t10:BF=0\t15:BF=0\t18:BF=0\t40:BF=1\t50:BF=0",b.inOrder());
		
		// 16 
		assertEquals(true,b.addNode(16));
		assertEquals("5:BF=0\t10:BF=1\t15:BF=1\t16:BF=0\t18:BF=-1\t40:BF=1\t50:BF=0",b.inOrder());
		
		// 12 
		assertEquals(true,b.addNode(12));
		assertEquals("5:BF=0\t10:BF=1\t12:BF=0\t15:BF=0\t16:BF=0\t18:BF=-1\t40:BF=1\t50:BF=0",b.inOrder());
		
		// 14 -- RDD(10)
		assertEquals(true,b.addNode(14));
		assertEquals("5:BF=0\t10:BF=-1\t12:BF=0\t14:BF=0\t15:BF=0\t16:BF=0\t18:BF=-1\t40:BF=1\t50:BF=0",b.inOrder());
		
		// 17 -- RDI(18)
		assertEquals(true,b.addNode(17));
		assertEquals("5:BF=0\t10:BF=-1\t12:BF=-1\t14:BF=0\t15:BF=0\t16:BF=1\t17:BF=0\t18:BF=0\t40:BF=1\t50:BF=0",b.inOrder());
		
		// Inserta un elemento que ya existe
		assertEquals(false,b.addNode(15));
	}

	@Test
	public void test_Remove() {
		AVLTree<Integer> b = new AVLTree<Integer>();
		// Borra una clave null
		assertThrows(NullPointerException.class, () -> {b.removeNode(null);});
		
		// Borra en un �rbol vac�o
		assertEquals(false, b.removeNode(12));
		
		// Insertar 5, 18 10, 40, 50, 15, 16, 12, 14, 17
		b.addNode(5);
		b.addNode(18);
		b.addNode(10);
		b.addNode(40);
		b.addNode(50);
		b.addNode(15);
		b.addNode(16);
		b.addNode(12);
		b.addNode(14);
		b.addNode(17);
		assertEquals("5:BF=0\t10:BF=-1\t12:BF=-1\t14:BF=0\t15:BF=0\t16:BF=1\t17:BF=0\t18:BF=0\t40:BF=1\t50:BF=0",b.inOrder());
		
		// Borra un una clave que es null
		assertThrows(NullPointerException.class, () -> {b.removeNode(null);});
		
		// Borra un elemento que no existe
		assertEquals(false, b.removeNode(90));
		
		// Borra una clave sin hijos --> 50
		assertEquals(true, b.removeNode(50));
		assertEquals("5:BF=0\t10:BF=-1\t12:BF=-1\t14:BF=0\t15:BF=0\t16:BF=1\t17:BF=0\t18:BF=-1\t40:BF=0",b.inOrder());
		
		// Borra un elemento que no existe
		assertEquals(false, b.removeNode(50));
		
		// Borra una clave con un hijo izquierdo --> 10
		assertEquals(true, b.removeNode(10));
		assertEquals("5:BF=0\t12:BF=0\t14:BF=0\t15:BF=1\t16:BF=1\t17:BF=0\t18:BF=-1\t40:BF=0",b.inOrder());
	
		// Borra la ra�z que tiene dos hijos --> 15
		assertEquals(true, b.removeNode(15));
		assertEquals("5:BF=0\t12:BF=-1\t14:BF=1\t16:BF=1\t17:BF=0\t18:BF=-1\t40:BF=0",b.inOrder());
	
		// Borra la ra�z que tiene dos hijos --> 14
		assertEquals(true, b.removeNode(14));
		assertEquals("5:BF=0\t12:BF=-1\t16:BF=0\t17:BF=0\t18:BF=0\t40:BF=0",b.inOrder());
		
		// Borra una clave que es hoja --> 17
		assertEquals(true, b.removeNode(17));
		assertEquals("5:BF=0\t12:BF=-1\t16:BF=0\t18:BF=1\t40:BF=0",b.inOrder());
		
		// Borra una clave que tiene un hijo derecho --> 18
		assertEquals(true, b.removeNode(18));
		assertEquals("5:BF=0\t12:BF=-1\t16:BF=-1\t40:BF=0",b.inOrder());
		
		// Borra una clave que es hoja --> 40
		assertEquals(true, b.removeNode(40));
		assertEquals("5:BF=0\t12:BF=0\t16:BF=0",b.inOrder());
		
		// Borra la ra�z que tiene dos hijos --> 12
		assertEquals(true, b.removeNode(12));
		assertEquals("5:BF=1\t16:BF=0",b.inOrder());
		
		// Borra un hijo que es hoja --> 16
		assertEquals(true, b.removeNode(16));
		assertEquals("5:BF=0",b.inOrder());
		
		// Borra la ra�z que no tiene hijos
		assertEquals(true, b.removeNode(5));
		
		// Borra el 5 
		assertEquals(false, b.removeNode(5));
	}
	
	
}
