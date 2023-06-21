/*
** Group Name: "Better Call Stack"
 * Group Members: Linh Pham, Huiguang Ma, Jaya Singh and Vincent Xayasak
 * Class Section: CIS 22C 46796
 * Instructor: Mirsaeid Abolghasemi
 * Date: 5/11/23
 *
* */
public final class LinkedQueue<T> implements QueueInterface<T>
{
    private Node firstNode; // references node at front of queue
    private Node lastNode; // references node at back of queue
    public LinkedQueue()
    {
        firstNode = null;
        lastNode = null;
    } // end default constructor
    @Override
    public void enqueue(T newEntry) {
//add a new entry to the back of this queue
        checkInitializaiton();
    Node newNode = new Node(newEntry, null);
    if (isEmpty())
        firstNode = newNode;
    else
        lastNode.setNextNode(newNode);
    lastNode = newNode;
    }

    @Override
    public T dequeue() {
        checkInitializaiton();
        T front = getFront(); // Might throw EmptyQueueException
        assert firstNode != null;
        firstNode.setData(null);
        firstNode = firstNode.getNextNode();
        if (firstNode == null)
            lastNode = null;
        return front;
    }

    private void checkInitializaiton() {
        try{
            if(isEmpty())
                throw new EmptyQueueException();
        }catch (EmptyQueueException e){
            System.out.println("Queue is empty");
        }
    }

    @Override
    public T getFront() {
        if(isEmpty())
            throw new EmptyQueueException();
        else
            return firstNode.getData();
    }

    @Override
    public boolean isEmpty() {
        return (firstNode == null) && (lastNode == null);
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
    }

    class Node{
        private T data;
        private Node next;

        private Node(T dataPortion){
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode){
            data = dataPortion;
            next = nextNode;
        }

        private T getData(){
            return data;
        }

        private void setData(T newData){
            data = newData;
        }

        private Node getNextNode(){
            return next;
        }

        private void setNextNode(Node nextNode){
            next = nextNode;
        }


    } // end Node
} // end Linkedqueue