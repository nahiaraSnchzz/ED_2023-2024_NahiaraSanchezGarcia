package Proyecto3_Arboles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Proyecto3_Arboles.AVL.AVLNode;
import Proyecto3_Arboles.AVL.AVLTree;



public class RotarDcha {
	@Test
	void rotarDcha() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		AVLNode<Integer> n8 = new AVLNode<Integer>(8);
		AVLNode<Integer> n9 = new AVLNode<Integer>(9);
		AVLNode<Integer> n10 = new AVLNode<Integer>(10);
		AVLNode<Integer> n11 = new AVLNode<Integer>(11);
		
		
		n8.setRight(n9);
		
		tree.addNode(n8.getInfo());
		n9.setRight(n10);
		tree.addNode(n9.getInfo());
		n10.setRight(n11);
		tree.addNode(n10.getInfo());
		
		assertEquals(true,tree.addNode(n11.getInfo()));
		
		System.out.println(tree.tumbarArbol(n8, 2));
		
	}
}
