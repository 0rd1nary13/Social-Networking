
import java.util.ArrayList;
import java.util.Iterator;
/**
 A class that implements the ADT undirected graph.

 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 4.0
 */
public class UndirectedGraph<T> extends DirectedGraph<T> implements GraphInterface<T>
{

    public UndirectedGraph()
    {
        super();
    } // end default constructor

    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        return super.addEdge(begin, end, edgeWeight) &&
                super.addEdge(end, begin, edgeWeight);
        // Assertion: edge count is twice its correct value due to calling addEdge twice
    } // end addEdge

    public boolean addEdge(T begin, T end)
    {
        return this.addEdge(begin, end, 0);
    } // end addEdge

    public int getNumberOfEdges()
    {
        return super.getNumberOfEdges() / 2;
    } // end getNumberOfEdges

    public StackInterface<T> getTopologicalOrder()
    {
        throw new UnsupportedOperationException("Topological sort illegal in an undirected graph.");
    } // end getTopologicalOrder



} // end UndirectedGraph
class Profile {
    private String name;
    private String image;
    private String status;
    private ArrayList<Profile> friends;

    public Profile(String name, String image, String status) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.friends = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Profile> getFriends() {
        return friends;
    }

    public void addFriend(Profile friend) {
        friends.add(friend);
    }

    public void removeFriend(Profile friend) {
        friends.remove(friend);
    }

    public void printProfile() {
        System.out.println("Name: " + name);
        System.out.println("Image: " + image);
        System.out.println("Status: " + status);
        System.out.println("Friends: ");
        for (Profile friend : friends) {
            System.out.println(friend.getName());
        }
    }
}


// To make addEdge more efficient, DirectedGraph needs to provide accessors
// to its data fields. (See Project 3, Chapter 29.)
