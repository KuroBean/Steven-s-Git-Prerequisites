// Represents a node of a doubly-linked list.

public class Node {
	private String value;
	private Node previous;
	private Node next;


	public Node (String v) {
		value = v;
		previous = null;
		next = null;
	}

	public Node(String v, Node prev, Node nx) {
		value = v;
		previous = prev;
		next = nx;
	}

	public String getValue() { return value; }
	public Node getPrevious() { return previous; }
	public Node getNext() { return next; }

	public void setValue(String v) { value = v; }
	public void setPrevious(Node previous) { this.previous = previous; }
	public void setNext(Node nx) { next = nx; }
}


