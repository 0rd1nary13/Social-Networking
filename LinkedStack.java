/*
 *     Group Name: "Better Call Stack"
 *     Group Members: Linh Pham, Huiguang Ma, Jaya Singh and Vincent Xayasak
 *     Class Section: CIS 22C 46796
 *     Instructor: Mirsaeid Abolghasemi
 *     Date: 5/4/23
 **/
public final class LinkedStack<T> implements StackInterface<T>{

    private Node topNode;  // References the first node in the chain

    //defalt constructor
    public LinkedStack() {
        topNode = null;
    }

    //adds a new entry to the top of this stack.
    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }
    //removes and returns this stack's top entry.
    @Override
    public T pop() {
        try{
            T top = peek();
            topNode = topNode.next;
            return top;
        }catch (NullPointerException e){
            System.out.println("Stack is empty");
            return null;
        }
    }
    //retrieves this stack's top entry.
    @Override
    public T peek() {
        try{
            return topNode.data;
        }catch (NullPointerException e){
            System.out.println("Stack is empty");
            return null;
        }
    }
    //detects whether this stack is empty.
    @Override
    public boolean isEmpty() {
        if(topNode == null){
            return true;
        }
        return false;
    }
    //removes all entries from this stack.
    @Override
    public void clear() {
        topNode = null;
    }

    private class Node{
        private T data;
        private Node next;

        private Node(T data) {
            this(data, null);
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }//end Node

}//end LinkedStack