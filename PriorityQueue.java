
public class PriorityQueue<V> implements QueueInterface<V>{

    private NodeBase<V>[] queue;
    private int capacity, currentSize,front,rear;
	
    //TODO Complete the Priority Queue implementation
    // You may create other member variables/ methods if required.
    public PriorityQueue(int capacity) {    
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
    	NodeBase<V>[] que2 = (NodeBase<V>[]) new Node[capacity];
    	boolean flag = true;
    	//System.out.println("Inserting "+node.getPriority());
    	
    	for(int i=0; i <=currentSize; i ++) {
    		if(i==currentSize && flag) {
    			//System.out.println("Inserting at last");
    			que2[(i+front)%capacity]=node;
    			break;
    		}
    		if(flag) {
    			if(node.getPriority() < this.queue[(i+front)%capacity].getPriority()) {

        			//System.out.println("Inserting before " + this.queue[(i+front)%capacity].getPriority());
    				que2[(i+front)%capacity]=node;
    				flag = false;
    			}
    			else {
    				que2[(i+front)%capacity]=this.queue[(i+front)%capacity];
    			}
    		}
    		else {
    			que2[(i+front)%capacity]=this.queue[(i+front-1 + capacity)%capacity];
    		}
    	}
    	this.queue = que2;
    	rear = (rear + 1) % capacity;
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

    public void display () {
	if (this.isEmpty()) {
            System.out.println("Queue is empty");
	}
	for(int i=0; i<currentSize; i++) {
            queue[(i+front)%capacity].show();
	}
    }
}

