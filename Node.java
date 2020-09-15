
public class Node<V> extends NodeBase<V> {
	protected int priority; // Priority of the node
    protected V value;
	public Node(int priority, V value) {
		this.priority = priority;
		this.value = value;
	}

	//TODO Complete these methods	
	public int getPriority() {
		return this.priority;
	}

	public V getValue() {
		return this.value;
	}

}
