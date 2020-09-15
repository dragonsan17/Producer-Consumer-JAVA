import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Seller<V> extends SellerBase<V> {
	protected PriorityQueue<V> catalog; // Shared priority queue
    protected Lock lock; //Shared lock
    protected Condition full, empty; // Shared condition variables
    private int sleepTime; // Sleep duration (in ms) for current thread
    protected Queue<V> inventory; // List of items (shared between sellers)
    public void run() {
        while(inventory.isEmpty()!=true) {
            try {
                sell();
	    } catch (Exception e) {  e.printStackTrace(); }
	}
    }
    public Seller (int sleepTime, int catalogSize, Lock lock, Condition full, Condition empty, PriorityQueue<V> catalog, Queue<V> inventory) {
        //TODO Complete the constructor method by initializing the attibutes
        // ...
    	setSleepTime(sleepTime);
		this.catalog = catalog;
		this.lock = lock;
		this.full = full;
		this.empty = empty;
		this.inventory = inventory;
    }
    
    public void sell() throws InterruptedException {
	try {
            //TODO Complete the try block for produce method
            // ...
		this.lock.lock();
		//System.out.println("Seller initiated");
		while(this.catalog.isFull()) {
			this.full.await();
		}	
			if(!this.inventory.isEmpty()) {
				
				NodeBase<V> node = this.inventory.dequeue();
				//System.out.print("Sold ");
				//node.show();
				this.catalog.enqueue((Node<V>) node);
				
			
			}
			this.empty.signalAll();
		
	} catch(Exception e) {
            e.printStackTrace();
	} finally {
            //TODO Complete this block
		this.lock.unlock();
	}		
    }
    

    // Setter for sleepTime
    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }
}
