package Proyecto4_Monticulos.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Proyecto4_Monticulos.EDBinaryHeapMin;

public class TestMetodos {
	
	@Test
	public void getMinHijo() {
		EDBinaryHeapMin<Integer> bh = new EDBinaryHeapMin<Integer>(5);
		bh.add(1);
		bh.add(2);
		bh.add(3);
		bh.add(4);
		assertEquals(3, bh.getMinHijo(1));
		bh.toString();
	}

}
