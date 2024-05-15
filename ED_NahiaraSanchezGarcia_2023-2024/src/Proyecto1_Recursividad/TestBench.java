package Proyecto1_Recursividad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestBench {
	
	/**
	 * Es est�tico porque al ser un objeto, ocupa mucho sitio en memoria. As� solo
	 * devuelve una referencia.
	 * 
	 * @param className
	 * @param methodName
	 * @param n
	 * @return
	 */
	public static Object testAlgorithm(String className, String methodName, int n) {

		Object obj = null;
		try {
			obj = Class.forName(className).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // crea un objeto de la clase className.

		Method method = null;

		try {
			method = obj.getClass().getMethod(methodName, int.class);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // int.class es que el parametro va a ser entero.

		try {
			return method.invoke(obj, n);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj;

		// los try catch son por si no existe la clase, el constructor...

	}

	public static void test (String output, int times, int startN, int endN, 
							String className, String methodName) throws IOException {
		// workLoad = carga de trabajo de un algoritmo
		FileWriter file = null;
		PrintWriter pw;
		Object result = null;
		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);
			
			for (int workLoad = startN ; workLoad < endN; workLoad++) {
				long startTime = System.currentTimeMillis();	// arrancamos cronometro
			
				for (int time = 0; time < times; time++) {
					testAlgorithm (className, methodName, workLoad);
				
				}
				long finalTime = System.currentTimeMillis();
			
				//System.out.println("Carga" + workLoad + "," + "Tiempo: "+ ((finalTime - startTime) / times));
				
				pw.println(workLoad + ";" + ((finalTime - startTime) / times));
			
				File archivo = new File(output);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (file != null) {
				file.close();
			}
		}
		
	}

}
