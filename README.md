# Producer-Consumer-JAVA

**Simulated Producer-Consumer problem with extension to multiple sellers and buyers for a real-world scenario
**Implemented Priority Circular queues for catalogs and inventory and achieved synchronization of multiple threads

Item and Node
	
	Are same as we have been given. The functions to be completed were completed accordingly.

Buyer
	The buyer class locks the lock before buying and releases in the "finally" block after the 'buy' function runs. The await() call
	automatically transfers the control of this lock to other threads whose signals may contribute to the reinitiating of this current
	thread. After the control returns, the lock is ensured to transfer to the current buyer thread till the end of the buy process.

	The Buyer class is implemented in a way such that it first waits for empty signal. If the Catalog is not empty, a seller
	thread will send this signal and the buyer thread will execute further. It will dequeue the front element of the catalog
	(the front element is always the one with the highest priority in the catalog in the way I have implemented). It will also 
	send the message "Consumed " and node priority. After this, the thread sends the full signal, signalling that the catalog is
	no longer full and seller threads can add elements to the catalog from the inventory. Also in the way we have been given buyer
	base, the buyer has fixed no. of iterations and the buyer thread runs that times accordingly.
	
Seller
	
	The seller class locks the lock before selling and releases in the "finally" block after the 'sell' function runs. The await() call
	automatically transfers the control of this lock to other threads whose signals may contribute to the reinitiating of this current
	thread. After the control returns, the lock is ensured to transfer to the current seller thread till the end of the sell process.

	The seller class is implemented in a way such that it waits for the full signal. If the Catalog is not full, this signal comes
	from buyer threads as they buy and then send this signal that the catalog isn't full. Also, there is an If function which will
	check whether the inventory is empty or not. If empty, it means that the inventory has been emptied and that this thread cannot
	sell. It will exit without any process and releases the loclk. Else, it will enqueue the front element of the
	inventory into the Catalog. The enqueue function of the Catalog is such that the inserted item is inserted according to the 
	priority and that the front element of the catalog is always thr highest priority. After this, the thread sends the empty signal
	signalling that the catalog is no longer empty and that buyer threads waiting for this signal can continue to buy when initiated.

PriorityQueue(Catalog)

	The priority queue is implemented in a circular fashion. After repeated dequeues and enqueues, if the iterator comes to the 	
	end of the array, it will return to the 0th index of the array by (i+front)%capacity expression. This expression ensures that
	the iteration of elements always keeps on rotating within the lengths, just the values of front and rear keep on changing. The 
	intital values of front is 0 and rear is -1. And during enqueue, the elements are inserted according to the priorities. I have 
	created a duplicate array 'que2' in which I iterate once over the current array, and keep on inserting elements after comparing to the
	current element to be inserted. If the element is the one with higher priority, then it gets inserted and later the rest elements
	of the current queue are inserted in this duplicate array. Later, I replace the current array with this duplicate array. The dequeue
	function is simple as simply the front value gets incremented. And incase it is already equal to capacity-1, it gets equal to 0 as
	increment happens in (front+1)%capacity fashion.

Queue(Inventory)

	The dequeue is similar to PriorityQueue. The enqueue is simply made as the element is inserted in the rear and the rear is incremented
	in the same way front is incremented during dequeue. This queue is filled in the main function and is dequeued by the seller threads.


Main function

	The main function is mostly what we have been given already. The elements from the given array is transfered to Inventory Queue.
	Multiple seller threads and buyer threads have been created in for loops and started. All the other classes are initiated accordingly.


