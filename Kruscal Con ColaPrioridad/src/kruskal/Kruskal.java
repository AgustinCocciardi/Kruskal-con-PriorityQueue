package kruskal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Kruskal {

	private int cantidadNodos;
	private int cantidadAristas;
	private int aristasEnUso;
	private PriorityQueue<Arista> cola = new PriorityQueue<Arista>();
	private ArrayList<Arista> arbolAbarcadorMinimo = new ArrayList<Arista>();
	private int costo;
	private static int padre[];
	
	public Kruskal(Scanner entrada) {
		int nodo1, nodo2, costo;
		this.cantidadNodos = entrada.nextInt();
		this.cantidadAristas = entrada.nextInt();
		this.aristasEnUso = 0;
		this.costo = 0;
		for(int i=0; i<this.cantidadAristas; i++) {
			nodo1 = entrada.nextInt();
			nodo2 = entrada.nextInt();
			costo = entrada.nextInt();
			cola.offer(new Arista(nodo1, nodo2, costo));
		}
		this.padre = new int[this.cantidadNodos + 1];
		for(int i = 1; i <= this.cantidadNodos; i++) {
			this.padre[i] = i;
		}
	}
	
	public int find(int x) {
		if (x == padre[x])
			return x;
		else
			return find(padre[x]);
	}
	
	public boolean mismoComponente(int x, int y) {
		if(find(x) == find(y))
			return true;
		return false;
	}
	
	public void union(int x, int y) {
		this.padre[find(x)] = find(y);
	}
	
	public void calcularKruskal() {
		Arista arista;
		while(this.aristasEnUso != this.cantidadNodos-1 && this.cola.isEmpty() == false) {
			arista = this.cola.poll();
			if(mismoComponente(arista.getNodo1(),arista.getNodo2()) == false) {
				arbolAbarcadorMinimo.add(arista);
				this.costo += arista.getCosto();
				this.aristasEnUso++;
				union(arista.getNodo1(),arista.getNodo2());
			}
		}
	}
	
	public void mostrarArbolYCosto() {
		System.out.println("El costo del arbol abarcador minimo es: " + this.costo);
		for (Arista arista : arbolAbarcadorMinimo) {
			System.out.println(arista.getNodo1() + " - " + arista.getNodo2() + " = " + arista.getCosto());
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner entrada = new Scanner(new FileReader("grafo.in"));
		Kruskal kruskal = new Kruskal(entrada);
		entrada.close();
		kruskal.calcularKruskal();
		kruskal.mostrarArbolYCosto();
	}

}
