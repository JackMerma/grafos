public class Ejercicio3 {

    public static void main(String args[]) {
    	Grafo<String> grafo = new Grafo<String>(8);
    	grafo.insert("A");
    	grafo.insert("B");
    	grafo.insert("C");
    	grafo.insert("D");
    	grafo.insert("E");
    	grafo.insert("F");
    	grafo.insert("G");
    	grafo.insert("H");

    	grafo.relation("A", "B");
    	grafo.relation("A", "D");
    	grafo.relation("B", "D");
    	grafo.relation("E", "D");
    	grafo.relation("B", "C");
    	grafo.relation("F", "D");
    	grafo.relation("C", "H");
    	grafo.relation("F", "H");
    	grafo.relation("F", "G");
    	
    	System.out.println(grafo.listaAdyacencia()+"\n");
    	
    	grafo.BFS("A");
    	grafo.DFS("A");
    	grafo.BFStable();
    	grafo.DFStable();
    }
}

