
//material given as example in class demo
public class SharedQueue {
    private class Node {
        private String data;
        private Node next;

        public Node(String inputString) {
            this.data = inputString;
            this.next = null;
        }
    }

    private Node headNode, tailNode;
    private int currentSize;
    private int maxSize;
    
    private Boolean isDoneReading;

    public SharedQueue(int maxSize) {
        headNode = null;
        tailNode = null;
        currentSize = 0;
        this.maxSize = maxSize;
        isDoneReading = false;
    }

    public synchronized boolean isEmpty() {
        if ((headNode == null) && (tailNode == null) || (currentSize == 0)) {
            return true;
        }
        return false;
    }

    public synchronized void enqueue(String inputString) {
        // Check if the queue has reached MaxSize
        while (currentSize >= maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Node newNode = new Node(inputString);

        if (currentSize == 0) {
            headNode = newNode;
        } else {
            tailNode.next = newNode;
        }

        tailNode = newNode;
        currentSize++;

        notifyAll();
    }

    public synchronized String dequeue() {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String outputString = headNode.data;
        headNode = headNode.next;
        currentSize--;

        if (currentSize == 0) {
            tailNode = null;
        }

        notify();
        return outputString;
    }

    public synchronized void setDoneReading(Boolean doneReading) {
        isDoneReading = doneReading;
        notifyAll();
    }

    public synchronized Boolean getDoneReading() {
        return isDoneReading;
    }

}