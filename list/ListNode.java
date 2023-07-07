public class ListNode {

    private Node refNode;
    private int length = 0;
	private Node in�cio;


    public void add(Node n) {

        Node newNode =  new Node();
        newNode.content = n;

        newNode.prox = this.refNode;
        this.refNode = newNode;
        this.length++;

    }

    public Node remove(int i) {
         Node removido = null;
        if(this.refNode == null) {
            return null;
        }
        	
        Node ant = this.getNode(i-1);
        if(ant == null) {  
	    	removido = this.refNode; 
	    	this.refNode = this.refNode.prox;      
        } 
	    else {
	    	  removido = ant.prox;
	    	  ant.prox = ant.prox.prox;
	    } 
      
        return removido;
    }

    /*
     * Imprime todos os conte�dos
     */
    public void imprimir() {
        System.out.println("Lista de Conte�dos:")
        for(int i=0;i<this.length;i++) {
            System.out.println(this.getNode().content)
        }
    }

/*
 * Retorna uma lista invertida da lista original
 */
    public ListNode invert() {
        ListNode l = new ListNode()

        for(int i=0;i<this.length;i++) {
            Node n = this.getNode(i);
            l.add(n);
        }

        return l;

    }
/*
 * Adiciona o conte�do na posi��o i, caso n�o exista adiciona no in�cio.
 */
    public void add (String content, int i) {
        Node ant = this.getNode(i-1);
        Node novo = new Node();
        novo.content = content;
        
        if(ant == null) {
            this.add(novo)
        } else {
            novo.prox = ant.prox;
            ant = novo;
            this.length++;
        }
    }
/*
 * Subtitui o conte�do do node i, caso n�o exista n�o faz nada
 */
    public void replace(String content, int i) {

        Node n = this.getNode(i);

        if(n == null) {
            return;
        } 

        n.content = content;
    }
/*
 * Rerona uma nova lista com a quantidade de elementos de acordo com length e todos os elementos com o mesmo content.
 */
    public ListNode newList(int length,String content) {
        ListNode l = new ListNode();

        for(int i=1;i<=length;i++) {
            this.add(content);
        }
        return l;
    }
/*
 * Retorna o node da posi��o i
 */
    public Node getNode(int i) {
        int c = -1;
        Node aux = this.in�cio;
        while(aux!=null) {
            aux = aux.prox;
            c++;
            if(c == i) {
                break;
            }
        }

        return aux;

    }
}