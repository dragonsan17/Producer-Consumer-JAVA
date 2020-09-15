import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Buyer<V> extends BuyerBase<V> {
	protected PriorityQueue<V> catalog; // The shared priority queue
    protected Lock lock; // Shared lock
    protected Condition full, empty; // Shared condition variables
    private int sleepTime; // Sleep duration (in ms) for current thread
    private int iteration; // No. of iterations for buyer threads
 // Setter for sleepTime
    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    // Setter for iteration
    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
    
    public void run() {
        for(int i=0;i<this.iteration;i++) {
            try {
                buy();
                Thread.sleep(this.sleepTime);
            } catch (Exception e) {  e.printStackTrace(); }
	}
    }
    
	public Buyer (int sleepTime, int catalogSize, Lock lock, Condition full, Condition empty, PriorityQueue<V> catalog, int iteration) {
        //TODO Complete the Buyer Constructor method
        // ...
		setSleepTime(sleepTime);
		setIteration(iteration);
		this.catalog = catalog;
		this.lock = lock;
		this.full = full;
		this.empty = empty;
	}
    public void buy() throws InterruptedException {
	try {
		
            //TODO Complete the try block for consume method
            // ...
		
		this.lock.lock();
		//System.out.println("Buyer initiated");
		
		while(this.catalog.isEmpty()) {
			this.empty.await();
		}
		
		NodeBase<V> n = this.catalog.dequeue();
		
	    System.out.print("Consumed "); // DO NOT REMOVE (For Automated Testing)
            n.show(); // DO NOT REMOVE (For Automated Testing)
            // ...
        this.full.signalAll();
            
    } catch (Exception e) {
            e.printStackTrace();
	} finally {
            //TODO Complete this block
		
		this.lock.unlock();
	}
    }
    
}
