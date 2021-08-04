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

        private Node(E data, Node father, Node next, boolean visi, int order){
            this.data = data;
            this.visited=visi;
            this.father = father;
            this.next=next;
            this.order=order;
        }

        public String toString() {
        	String fa = (this.father==null)?"-":father.data.toString();
        	return data.toString()+"\t"+fa+"\t"+this.order;
        }
    }
    
    class Cola{
    	private Node root;
    	private Node last;
    
    	/*
    	private void encolar(E data) {
    		if(this.root==null) {
    			root=last=new Node(data);
    		}else {
    			Node aux = new Node(data);
    			last.next=aux;
    			last = aux;
    		}
    	}
    	*/
    	
    	private void encolar(Node node) {
    		if(this.root==null)
    			this.root = last = node;
    		else {
    			last.next = node;
    			last=node;
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
    	
    	private boolean isEmpty() {
    		return this.root==null;
    	}
    	
    	private Node search(E data) {
    		Node aux = this.root;
    		while(aux!=null&&aux.data!=data)
    			aux=aux.next;
    		return aux;
    	}
    }
    
    private Integer inf = Integer.MAX_VALUE;
    private int cant=0, total;
    private E[] list;
    private int[][] listAdya;
    private Cola bfsList, dfsList;//resultado final
    private Cola listAuxiliar;//donde se encola BFS o DFS
    
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
    public void BFS(E data) {
    	int posAbs = this.capturePos(data);
    	
    	if(this.list[posAbs]!=null) {
    		this.bfsList = new Cola();
    		//primero
    		Node aux = new Node(data, null, null, true, 0);
    		
    		this.bfsList.encolar(aux);
    		this.listAuxiliar = new Cola();
    		
    		this.BFS(aux, posAbs);
    	}else {
    		System.out.println("No se puede hacer BSF desde un dato que no existe.");
    	}
    }
    
    private void BFS(Node actual, int pos) {
    	for(int i=0;i<this.total;i++) {
    		if(this.listAdya[pos][i]!=inf&&pos!=i) {
    			//existe relacion con otro nodo
    			E dataRelac = this.list[i];//se recupera la pos, y el dato en List

    			if(listAuxiliar.search(dataRelac)==null&&bfsList.search(dataRelac)==null){//se verifica en ambos
    				Node aux = new Node(dataRelac, actual, null, true, actual.order+1);
    				this.listAuxiliar.encolar(aux);
    				this.bfsList.encolar(aux);
    			}
    		}
    	}
    	
    	//se desencola en orden
    	while(!listAuxiliar.isEmpty()) {
    		Node aux = listAuxiliar.desencolar();
    		if(aux!=null) {
    			int posNode = this.capturePos(aux.data);
    			this.BFS(aux, posNode);
    		}
    	}
    }
    
    public void BFStable() {
    	this.tableRecorridos(this.bfsList);
    }
    
    public void DFS(E data) {
    	int posAbs = this.capturePos(data);
    	
    	if(this.list[posAbs]!=null) {
    		this.dfsList = new Cola();
    		//primero
    		Node aux = new Node(data, null, null, true, 0);
    		
    		this.dfsList.encolar(aux);
    		this.listAuxiliar = new Cola();
    		
    		this.DFS(aux, posAbs);
    	}else {
    		System.out.println("No se puede hacer BSF desde un dato que no existe.");
    	}
    }
    
    private void DFS(Node actual, int pos) {
    	for(int i=0;i<this.total;i++) {
    		if(this.listAdya[pos][i]!=inf&&pos!=i) {
    			//existe relacion con otro nodo
    			E dataRelac = this.list[i];//se recupera la pos, y el dato en List

    			if(listAuxiliar.search(dataRelac)==null&&dfsList.search(dataRelac)==null){//se verifica en ambos
    				Node aux = new Node(dataRelac, actual, null, true, actual.order+1);
    				this.dfsList.encolar(aux);
    				this.DFS(aux, i);
    			}
    		}
    	}
    }
    
    public void DFStable() {
    	this.tableRecorridos(this.dfsList);
    }
    
    public void tableRecorridos(Cola x) {
    	Node aux = x.root;
    	while(aux!=null) {
    		System.out.println(aux);
    		aux = aux.next;
    	}
    }
    
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
    	//grafo.BFStable();
    	grafo.DFStable();
    }
}