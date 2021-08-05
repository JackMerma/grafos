public class Ejercicio2 {

    public static void main(String args[]) {
    	Grafo<String> grafo = new Grafo<String>(4);
    	grafo.insert("A");
    	grafo.insert("B");
    	grafo.insert("C");
    	grafo.insert("D");

    	grafo.relation("C", "D", 11);
    	grafo.relation("A", "B", 7);
    	grafo.relation("A", "D", 3);
    	grafo.relation("B", "D", 2);
    	grafo.relation("D", "B", 21);
    	grafo.relation("D", "C", 5);
    	grafo.relation("C", "A", 17);
    	System.out.println(grafo.listaAdyacencia()+"\n");
    }
}
