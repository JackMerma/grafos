public class Ejercicio5 {

    public static void main(String args[]) {
    	Grafo<Character> g1=new Grafo<Character>(4), g2=new Grafo<Character>(3);

    	g1.insert('A');
    	g1.insert('B');
    	g1.insert('C');
    	g1.insert('D');
    	
    	g1.relation('C', 'B');
    	g1.relation('A', 'D');
    	g1.relation('C', 'D');
    	g1.relation('B', 'D');
    	
    	
    	g2.insert('A');
    	g2.insert('B');
    	g2.insert('D');
    	g2.relation('B', 'D');
    	g1.relation('A', 'D');
    	
    	//el grafo A si incluye al B
    	System.out.println(Grafo.grafoIncluido(g2, g1));
    	
    	g2.relation('A', 'B');//nueva relacion de nodos
    	System.out.println(Grafo.grafoIncluido(g2, g1));
    }
}
