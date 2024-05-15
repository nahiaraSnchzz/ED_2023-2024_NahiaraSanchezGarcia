package Proyecto1_Recursividad;

public class Algoritmos {
	
	public static int iterativeFactorial(int n) {

		int acumulador = 1;
		if (n == 0) {
			return 1;
		} else {
			for (int i = n; i > 0; i--) {
				acumulador = acumulador * i;
			}
			return acumulador;
		}
	}

	public static int recursiveFactorial(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n * recursiveFactorial(n - 1);
		}
	}

	public static int iterativeFibonacci(int n) {
		int siguiente = 1;
		int actual = 0;
		int temporal = 0;

		for (int i = 1; i <= n; i++) {

			temporal = actual;
			actual = siguiente;

			siguiente = siguiente + temporal;
		}
		return actual;

	}

	public static int recursiveFibonacci(int n) {
		
		if (n < 2) {
			return n;
		}
		else {
			return recursiveFibonacci(n-1) + recursiveFibonacci(n-2);
		}

	}
	
	public static int iterativePow (int n, int p) {
		if (p==0) {
			return 1;
		}
		int pow = 1;
		for (int i=0 ; i<p ; i++) {
			pow = pow * n;
		}
		return pow;
	}
	
	public static int recursivePow (int n, int p) {
		if (p==0) {
			return 1;
		}
		return n * recursivePow (n, p-1) ;
	}
	
	public static int recursivePow1 (int n) {
		if (n == 0) {
			return 1;
		}
		return 2 * recursivePow1(n-1);
	}
	
	public static int recursivePow2(int n) {
		if (n == 0) {
			return 1;
		}
		return recursivePow1(n-1) + recursivePow1(n-1);
	}
	
	public static int recursivePow3 (int n) {
		if (n == 0) {
			return 1;
		}
		if (n % 2 == 0) {
			return recursivePow3(n/2) * recursivePow3(n/2); 
		}
		return recursivePow3(n-1) *2 ;
	}
	
	
	public static void lineal(int n) {
		
		for (int i=0; i < n; i++) {
			doNothing(250);
			recursivePow1(n);
		}
		
	}
	
	public static void quadratic(int n) {
		
		for (int i=0; i < n; i++) {
			for (int j=0; j < n; j++) {
				doNothing(250);
				recursivePow1(n);
			}
		}
		
	}
	
	public static void cubic (int n) {
		for (int i=0; i < n; i++) {
			for (int j=0; j < n; j++) {
				for (int k=0 ; k < n; k++) {
					doNothing(250);
					recursivePow1(n);
				}
			}
		}
	}
	
	
	public static void doNothing(int timeSleep) {
		try {
			Thread.sleep(timeSleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void logarithmic (int n) {
		while (n>0) {
			doNothing(250);
			recursivePow1(n);
			n = n/2;
		}
	}


}
