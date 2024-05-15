package Proyecto3_Arboles.tests;


import org.junit.jupiter.api.Test;

import Proyecto3_Arboles.BST.BSTNode;
import Proyecto3_Arboles.BST.BSTree;


class TreeTests {


	@Test
	void addNode() {
		BSTree<Integer> t = new BSTree<Integer>();
		
		BSTNode<Integer> n1 = new BSTNode<Integer>(1);
		BSTNode<Integer> n2 = new BSTNode<Integer>(2);
		BSTNode<Integer> n3 = new BSTNode<Integer>(3);
		t.addNode(n1.getInfo());
		t.addNode(n2.getInfo());
		t.addNode(n3.getInfo());
		System.out.println(t.tumbarArbol(n1, 3));
		
		
	}

}
