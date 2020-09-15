// This class implements the Queue
public class Queue<V> implements QueueInterface<V>{

    //TODO Complete the Queue implementation
    private NodeBase<V>[] queue;
    private int capacity, currentSize, front, rear;
	
    public Queue(int capacity) {    
    	this.capacity=capacity;
    	this.currentSize=0;
    	this.front = 0;
    	this.rear = -1;
    	this.queue = (NodeBase<V>[]) new Node[capacity];
    }

    public int size() {
    	return this.currentSize;
    }

    public boolean isEmpty() {
    	if(this.currentSize==0)return true;
    	else return false;
    }
	
    public boolean isFull() {
    	if(this.currentSize==this.capacity)return true;
    	else return false;
    }

    public void enqueue(Node<V> node) {
    	if(currentSize!=capacity) {
    	rear = (rear + 1) % capacity;
		this.queue[rear] = node;
		this.currentSize++;
    	}
    }

    public NodeBase<V> dequeue() {
    	if(currentSize>0) {
    	NodeBase<V> node = this.queue[front];
    	front = (front + 1) % capacity;
		this.currentSize--;
		return node;
    	}
    	else return null;
    }

}

