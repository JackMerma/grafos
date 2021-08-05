/**
 * 	Esta clase crea un tipo de grafo dirigido y no dirigido, con metodos BFS y DFS
 * 
 *	@author Jackson Fernando Merma Portocarrero
 *	
 */
public class Grafo<E extends Comparable<E>>{
    /**
     * Clase Node
     */
    class Node{
        private E data;
        private Node father;
        private Node next;
        private int order;

        /**
         * 
         * @param data dato a almacenar en el nodo
         * @param father padre del nodo (solo se usa para almacenamiento)
         * @param next siguiente nodo (se usa para enlazar estructura)
         * @param order orden segun el uso de BFS o DFS
         */
        private Node(E data, Node father, Node next, int order){
            this.data = data;
            this.father = father;
            this.next=next;
            this.order=order;
        }

        public String toString() {
        	String fa = (this.father==null)?"-":father.data.toString();
        	return data.toString()+"\t"+fa+"\t"+this.order;
        }
    }
    
    /*
     * Clase Cola
     */
    class Cola{
    	private Node root;
    	private Node last;
    	
    	/**
    	 * 
    	 * @param node nodo a encolar
    	 */
    	private void encolar(Node node) {
    		if(this.root==null)
    			this.root = last = node;
    		else {
    			last.next = node;
    			last=node;
    		}
    			
    	}
    	
    	/**
    	 * 
    	 * @return el primer nodo
    	 */
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
    	
    	/**
    	 * 
    	 * @return cola esta vacia
    	 */
    	private boolean isEmpty() {
    		return this.root==null;
    	}
    	
    	/**
    	 * 
    	 * @param data dato a buscar
    	 * @return Nodo que guada dicho dato
    	 */
    	private Node search(E data) {
    		Node aux = this.root;
    		while(aux!=null&&aux.data!=data)
    			aux=aux.next;
    		return aux;
    	}
    }
    
    private static Integer inf = Integer.MAX_VALUE;
    private int cant=0, total;
    private E[] list;
    private int[][] listAdya;
    private Cola bfsList, dfsList;//resultado final
    private Cola listAuxiliar;//auxiliar para BFS
    
    /**
     * 
     * @param n cantidad de elementos
     */
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
   
    /**
     * 
     * @return grafo lleno
     */
    public boolean isFull() {
    	return cant == total;
    }
    
    /**
     * 
     * @param data dato a insertar
     */
    public void insert(E data) {
    	if(this.isFull()) {
    		System.out.println("Esta lleno");
    	}else {
    		this.list[cant]= data;
    		this.cant++;
    	}
    }
    
    /**
     * 
     * @param a primer elemento a relacionar
     * @param b segundo elemento relacionado
     */
    public void relation(E a, E b) {//sin peso
    	this.relation(a, b, 1, true);//si tienes peso para ambas direcciones
    }
    
    /**
     * 
     * @param a primer elemento a relacionar
     * @param b segundo elemento relacionado
     * @param peso coste
     */
    public void relation(E a, E b, int peso) {
    	this.relation(a, b, peso, false);//no tienen peso para mabas direcciones
    }

    /**
     * 
     * @param a primer elemento a relacionar
     * @param b segundo elemento relacionado
     * @param peso coste
     * @param ambos relacionar ambos (no dirigido) o no
     */
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
    
    /**
     * 
     * @param data dato a buscar
     * @return posicion del dato
     */
    private int capturePos(E data) {
    	//se busca en list
    	for(int i=0;i<this.cant;i++) {
    		if(list[i].compareTo(data)==0)
    			return i;
    	}
    	return -1;
    }
    
    /**
     * 
     * @return matriz con relaciones entre elementos
     */
    public String listaAdyacencia() {
    	String result="\nLista de Adyacencia\n\t";
    	
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
    
    /**
     * 
     * @param data dato desde donde se empezara el BFS
     */
    public void BFS(E data) {
    	int posAbs = this.capturePos(data);
    	
    	if(this.list[posAbs]!=null) {
    		this.bfsList = new Cola();
    		//primero
    		Node aux = new Node(data, null, null, 0);
    		
    		this.bfsList.encolar(aux);
    		this.listAuxiliar = new Cola();
    		
    		this.BFS(aux, posAbs);
    	}else {
    		System.out.println("No se puede hacer BFS desde un dato que no existe.");
    	}
    }
    
    /**
     * 
     * @param actual nodo actual
     * @param pos posicion dentro de List de dicho nodo
     */
    private void BFS(Node actual, int pos) {
    	for(int i=0;i<this.total;i++) {
    		if(this.listAdya[pos][i]!=inf&&pos!=i) {
    			//existe relacion con otro nodo
    			E dataRelac = this.list[i];//se recupera la pos, y el dato en List

    			if(listAuxiliar.search(dataRelac)==null&&bfsList.search(dataRelac)==null){//se verifica en ambos
    				Node aux = new Node(dataRelac, actual, null, actual.order+1);
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
    	System.out.println("\nBFS");
    	this.tableRecorridos(this.bfsList);
    }
    
    /**
     * 
     * @param data dato desde donde se empezara el DFS
     */
    public void DFS(E data) {
    	int posAbs = this.capturePos(data);
    	
    	if(this.list[posAbs]!=null) {
    		this.dfsList = new Cola();
    		//primero
    		Node aux = new Node(data, null, null, 0);
    		
    		this.dfsList.encolar(aux);
    		this.DFS(aux, posAbs);
    	}else {
    		System.out.println("No se puede hacer DSF desde un dato que no existe.");
    	}
    }
    
    /**
     * 
     * @param actual nodo actual
     * @param pos posicion dentro de List de dicho nodo
     */
    private void DFS(Node actual, int pos) {
    	for(int i=0;i<this.total;i++) {
    		if(this.listAdya[pos][i]!=inf&&pos!=i) {
    			//existe relacion con otro nodo
    			E dataRelac = this.list[i];//se recupera la pos, y el dato en List

    			if(dfsList.search(dataRelac)==null){//se verifica solo en dfsList 
    				Node aux = new Node(dataRelac, actual, null,actual.order+1);
    				this.dfsList.encolar(aux);
    				this.DFS(aux, i);
    			}
    		}
    	}
    }
    
    public void DFStable() {
    	System.out.println("\nDFS");
    	this.tableRecorridos(this.dfsList);
    }
    
    /**
     * 
     * @param x Cola a listar
     */
    public void tableRecorridos(Cola x) {
    	Node aux = x.root;
    	while(aux!=null) {
    		System.out.println(aux);
    		aux = aux.next;
    	}
    }

    /**
     * 
     * @param a grafo que incluye a b
     * @param b grafo incluido en a
     * @return si el grafo a incluye al b
     */
    public static boolean grafoIncluido(Grafo a, Grafo b) {
    	int [][]listAdyaA = a.listAdya, listAdyaB = b.listAdya;
    	Comparable []listA = a.list, listB = b.list;

    	//determina que grafo tiene menor nodos
    	//se recorre todos los Datos incorrectosdatos de A
    	for(int i=0;i<a.cant;i++) {
    		//se verifica que el dato en (i) exista en el otro grafo

    		int posDataB1 = b.capturePos(listA[i]);

    		if(posDataB1==-1)//no existe
    			return false;

    		//se verifica las relaciones del nodo A con las del nodo B
    		for(int u=0;u<a.cant;u++) {
    			if(listAdyaA[i][u]!=inf) {//existe la arista de dos datos de un nodo del grafo 1
    				int posDataB2 = b.capturePos(listA[u]);
    				if(posDataB2==-1) {// el otro dato no existe
    					return false;
    				}else {
    					if(listAdyaB[posDataB1][posDataB2]==inf)//existen ambos datos pero no la arista
    						return false;
    				}
    			}
    		}
    	}
    	return true;
    }
}