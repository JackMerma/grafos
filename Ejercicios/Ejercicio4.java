public class Ejercicio4 {

    public static void main(String args[]) {
    	Grafo<String> grafo = new Grafo<String>(11);
    	
    	String words[] = {"words","cords", "corps","coops","crops","drops","drips","grips","gripe","grape","graph"};
    	
    	for(int i=0;i<words.length;i++) {
    		grafo.insert(words[i]);
    		if(i>0)
    			grafo.relation(words[i-1],words[i]);
    	}
    	System.out.println(grafo.listaAdyacencia());
    }
}

