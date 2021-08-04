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
    	list = (E[]) new Object [n];//para recuperar las posiciones
    	listAdya = new int [n][n]; //lista de adyacencia con pesos
    	total=n;
    	
    	iniciarListaAdyacencia();
    }
    
    private void iniciarListaAdyacencia() {
    	for(int i=0;i<total;i++)
    		for(int u=0;u<total;u++)
    			listAdya[i][u]=inf;
    }
    
    public boolean isFull() {
    	return cant == total;
    }
    
    public void insert(E data) {
    	if(this.isFull()) {
    		System.out.println("Esta lleno");
    	}else {
    		this.list[cant]= data;
    		this.cant++;
    	}
    }
    
    public void relation(E a, E b) {//sin peso
    	this.relation(a, b, 1);
    }

    public void relation(E a, E b, int peso) {// con peso
    	 int posA, posB;
    	 posA=capturePos(a);
    	 posB=capturePos(b);
    	 
    	 //se coloca la relacion en la lista de Adyacencia
    	 
    	 if(posA==-1||posB==-1) {
    		 System.out.println("Datos incorrectos");
    	 }else {
    		 this.listAdya[posA][posB]=peso;
    		 
    		 //por defecto tambien se colocara en la contraria
    		 this.listAdya[posB][posA]=peso;
    	 }
    }
    private int capturePos(E data) {
    	//se busca en list
    	for(int i=0;i<this.cant;i++) {
    		if(list[i].compareTo(data)==0)
    			return i;
    	}
    	return -1;
    }
    
}