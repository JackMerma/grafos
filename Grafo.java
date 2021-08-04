public class Grafo<E extends Comparable<E>>{
    /**
     * Clase Item -> realizar operaciones
     */
    class Item{
        private E data;
        private Item father;
        private int order;
        private boolean visited;

        private Item(E data){
            this.data = data;
        }
    }

    private Integer inf = Integer.MAX_VALUE;
    private int cant=0, total;
    private E[] list;
    private int[][] listAdya;
    private Item bfsList, dfsList;
    
    public Grafo(int n) {
    	list = (E[]) new Object [n];
    	listAdya = new int [n][n]; //lista de adyacencia con pesos
    	total=n;
    	
    	iniciarListaAdyacencia();
    }
    
    private void iniciarListaAdyacencia() {
    	for(int i=0;i<total;i++)
    		for(int u=0;u<total;u++)
    			listAdya[i][u]=inf;
    }
    
}