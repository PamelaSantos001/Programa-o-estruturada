public class Pilha { 
	
	public ListNode listNode = new ListNode();
    public Node desempilhar( ) {
    	
    	return this.listNode.remove(0);
    	
    }
    
    public void empilhar(String content) {
    	this.listNode.add(content, 0);
    }
}
