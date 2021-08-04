public class Grafo<E extends Comparable<E>>{
    /**
     * Clase Item -> realizar operaciones
     */
    class Node{
        private E data;
        private Node father;
        private Node next;
        private int order;
        private boolean visited;

        private Node(E data){
            this.data = data;
        }
    }
    
    class Cola{
    	private Node root;
    	private Node last;
    	
    	private void encolar(E data) {
    		if(this.root==null) {
    			root=last=new Node(data);
    		}else {
    			Node aux = new Node(data);
    			last.next=aux;
    			last = aux;
    		}
    	}
    	
    	private Node desencolar() {
    		Node aux=null;
    		if(this.root!=null) {
    			aux=this.root;
    			
    			if(root==last)
    				last=last.next;//null
    			this.root=this.root.next;
    		}
    		return aux;
    	}
    }
    
    private Integer inf = Integer.MAX_VALUE;
    private int cant=0, total;
    private E[] list;
    private int[][] listAdya;
    private Cola bfsList, dfsList;//resultado final
    private Cola listQueue;//donde se encola BFS o DFS
    
    public Grafo(int n) {
    	list = (E[]) new Comparable [n];//para recuperar las posiciones
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
    	this.relation(a, b, 1, true);//si tienes peso para ambas direcciones
    }
    
    public void relation(E a, E b, int peso) {
    	this.relation(a, b, peso, false);//no tienen peso para mabas direcciones
    }

    private void relation(E a, E b, int peso, boolean ambos) {
    	 int posA, posB;
    	 posA=capturePos(a);
    	 posB=capturePos(b);
    	 
    	 //se coloca la relacion en la lista de Adyacencia
    	 
    	 if(posA==-1||posB==-1) {
    		 System.out.println("Datos incorrectos");
    	 }else {
    		 this.listAdya[posA][posB]=peso;
    		 if(ambos)
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
    
    public String listaAdyacencia() {
    	String result="\t";
    	
    	for(int i=0;i<this.total;i++)
    		result+=this.list[i]+"\t";
    	result+="\n";
    	
    	for(int i=0;i<this.total;i++) {
    		result+=list[i]+"\t";
    		for(int u=0;u<this.total;u++) {
    			result+=((this.listAdya[i][u]==inf)?"inf":this.listAdya[i][u])+"\t";
    		}
    		result+="\n";
    	}
    	
    	return result;
    }
    
    //BSF
    public void BSF(E data) {
    	int posAbs = this.capturePos(data); //ya se puede trabajar con la adyacente
    	
    	Node dataAux;
    	if(this.list[posAbs]!=null) {
    		//this.bfsList = new Node(this.list[posAbs]);//se crea el nodo con datos vacios(ademas del dato neto)
    		//this.BSF(bfsList, 0, 0, posAbs);
    	}else {
    		System.out.println("No se puede hacer BSF desde un dato que no existe.");
    	}
    		
    	
    }
    
    private boolean BSF(Node actual, int orden, int nodosRecorridos, int posNodo) {
    	
    }
    
    public static void main(String args[]) {
    	Grafo<String> grafo = new Grafo<String>(3);
    	grafo.insert("A");
    	grafo.insert("B");
    	grafo.insert("C");
    	
    	grafo.relation("A", "C");
    	grafo.relation("B", "A", 5);
    	grafo.relation("A", "B", 8);
    	System.out.println(grafo.listaAdyacencia());
    	
    	
    	
    }
    
}