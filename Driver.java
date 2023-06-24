/*
Group Name: "Better Call Stack".
Members: Linh Pham, Huiguang Ma, Java Singh and Vincent Xayasak.
Class Section: CIS 22C 46796
Instructor: Mirsaeid Abolghasemi
Date: 6/23/23

The given code represents the driver class for a social network application. It
utilizes the `SocialNetwork` class to provide a user interface through the command line.
The program presents a menu of options for users to interact with the social network, including
joining or leaving the network, updating profiles, adding or removing friends, searching for profiles,
and displaying the network's members. It uses a `Scanner` object to receive user input and handles
various input validation scenarios. The code employs the `SocialNetwork` class and its associated
methods to manage user profiles and their relationships within the network. The supporting data
structures used in the implementation include `ArrayList` for storing profiles and a custom
`LinkedDictionary` for efficient retrieval of profiles by name. Overall, the code enables users
to interact with the social network and perform various operations on profiles and friendships.
*/

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        SocialNetwork socialNetwork = new SocialNetwork();
        Scanner scanner = new Scanner(System.in);
        UndirectedGraph<String> graph = new UndirectedGraph<>();
        //this graph is the graph of friends
        while (true) {
            try {
                System.out.println("\nThe Social Network");
                System.out.println("1. Join Network");
                System.out.println("2. Leave Network");
                System.out.println("3. Update Profile");
                System.out.println("4. Add Friend");
                System.out.println("5. Remove Friend");
                System.out.println("6. Search Profile");
                System.out.println("7. Display Network");
                System.out.println("8. Display Friend's Friends");
                System.out.println("9. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String joinName = scanner.nextLine();
                        System.out.print("Enter image: ");
                        String joinImage = scanner.nextLine();

                        System.out.println("Status:");
                        System.out.println("1. Online");
                        System.out.println("2. Offline");
                        System.out.println("3. Busy");
                        System.out.print("Pick A Choice (1-3): ");

                        int statusChoice = 0;
                        boolean validChoice = false;
                        while (!validChoice) {
                            try {
                                statusChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (statusChoice >= 1 && statusChoice <= 3) {
                                    validChoice = true;
                                } else {
                                    System.out.print("Invalid choice. Please pick a choice (1-3): ");
                                }
                            } catch (Exception e) {
                                System.out.print("Invalid input. Please enter a valid integer choice: ");
                                scanner.nextLine();
                            }
                        }
                        String joinStatus;
                        switch (statusChoice) {
                            case 1:
                                joinStatus = "Online";
                                break;
                            case 2:
                                joinStatus = "Offline";
                                break;
                            case 3:
                                joinStatus = "Busy";
                                break;
                            default:
                                joinStatus = "";
                        }
                        socialNetwork.joinNetwork(joinName, joinImage, joinStatus);
                        graph.addVertex(joinName);
                        break;
                    case 2:
                        System.out.print("Enter name: ");
                        String leaveName = scanner.nextLine();
                        socialNetwork.leaveNetwork(leaveName);
                        graph.removeVertex(leaveName);
                        break;
                    case 3:
                        System.out.print("Enter name: ");
                        String updateName = scanner.nextLine();
                        System.out.print("Enter new image: ");
                        String newImage = scanner.nextLine();

                        System.out.println("Status:");
                        System.out.println("1. Online");
                        System.out.println("2. Offline");
                        System.out.println("3. Busy");
                        System.out.print("Pick A Choice (1-3): ");

                        int statusChoice2 = 0;
                        boolean validChoice2 = false;
                        while (!validChoice2) {
                            try {
                                statusChoice2 = scanner.nextInt();
                                scanner.nextLine();

                                if (statusChoice2 >= 1 && statusChoice2 <= 3) {
                                    validChoice2 = true;
                                } else {
                                    System.out.print("Invalid choice. Please pick a choice (1-3): ");
                                }
                            } catch (Exception e) {
                                System.out.print("Invalid input. Please enter a valid integer choice: ");
                                scanner.nextLine();
                            }
                        }
                        String newStatus;
                        switch (statusChoice2) {
                            case 1:
                                newStatus = "Online";
                                break;
                            case 2:
                                newStatus = "Offline";
                                break;
                            case 3:
                                newStatus = "Busy";
                                break;
                            default:
                                newStatus = "";
                        }

                        socialNetwork.updateProfile(updateName, newImage, newStatus);
                        break;
                    case 4:
                        System.out.print("Enter name: ");
                        String addName = scanner.nextLine();
                        System.out.print("Enter friend's name: ");
                        String friendName = scanner.nextLine();
                        socialNetwork.addFriend(addName, friendName);
                        graph.addEdge(addName, friendName);
                        break;
                    case 5:
                        System.out.print("Enter name: ");
                        String removeName = scanner.nextLine();
                        System.out.print("Enter friend's name: ");
                        String removeFriendName = scanner.nextLine();
                        socialNetwork.removeFriend(removeName, removeFriendName);
                        if(graph.hasEdge(removeName, removeFriendName))
                            graph.removeEdge(removeName, removeFriendName);
                        else
                            System.out.println("No such friend exists!");
                        break;
                    case 6:
                        System.out.print("Enter name to search: ");
                        String searchName = scanner.nextLine();
                        socialNetwork.searchProfile(searchName);
                        break;
                    case 7:
                        socialNetwork.displayNetwork();
                        System.out.println("Friendship Graph: ");
                        graph.printGraph();
                        break;
                    case 8:
                        System.out.println("Enter name: ");
                        String yourName = scanner.nextLine();
                        System.out.println("Enter friend's name: ");
                        String friendName2 = scanner.nextLine();
                        //if friendName2 is not a friend of yourName
                        if( !graph.hasEdge(yourName, friendName2) )
                            System.out.println("No such friend exists!");
                        else
                            //print all friends of friendName2
                            socialNetwork.displayFriends(friendName2);
                        break;
                    case 9:
                        System.out.println("\nThanks for using \"Better Call Stack\" Social Network!\n"
                                + "Hope to see you soon. Goodbye!\n");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please Enter An Integer! ");
                scanner.nextLine();
            }
        }
    }
}


/*
Sample Output:
The Social Network
1. Join Network
2. Leave Network
3. Update Profile
4. Add Friend
5. Remove Friend
6. Search Profile
7. Display Network
8. Display Friend's Friends
9. Exit
Enter your choice: 1
Enter name: a
Enter image: w
Status:
1. Online
2. Offline
3. Busy
Pick A Choice (1-3): 2
Joined the network.

The Social Network
1. Join Network
2. Leave Network
3. Update Profile
4. Add Friend
5. Remove Friend
6. Search Profile
7. Display Network
8. Display Friend's Friends
9. Exit
Enter your choice: 1
Enter name: b
Enter image: e
Status:
1. Online
2. Offline
3. Busy
Pick A Choice (1-3): 3
Joined the network.

The Social Network
1. Join Network
2. Leave Network
3. Update Profile
4. Add Friend
5. Remove Friend
6. Search Profile
7. Display Network
8. Display Friend's Friends
9. Exit
Enter your choice: 1
Enter name: c
Enter image: r
Status:
1. Online
2. Offline
3. Busy
Pick A Choice (1-3): 1
Joined the network.

The Social Network
1. Join Network
2. Leave Network
3. Update Profile
4. Add Friend
5. Remove Friend
6. Search Profile
7. Display Network
8. Display Friend's Friends
9. Exit
Enter your choice: 4
Enter name:
Enter friend's name: a
Profile(s) not found.

The Social Network
1. Join Network
2. Leave Network
3. Update Profile
4. Add Friend
5. Remove Friend
6. Search Profile
7. Display Network
8. Display Friend's Friends
9. Exit
Enter your choice: 4
Enter name: a
Enter friend's name: b
Friend added.

The Social Network
1. Join Network
2. Leave Network
3. Update Profile
4. Add Friend
5. Remove Friend
6. Search Profile
7. Display Network
8. Display Friend's Friends
9. Exit
Enter your choice: 4
Enter name: c
Enter friend's name: b
Friend added.

The Social Network
1. Join Network
2. Leave Network
3. Update Profile
4. Add Friend
5. Remove Friend
6. Search Profile
7. Display Network
8. Display Friend's Friends
9. Exit
Enter your choice: 4
Enter name: a
Enter friend's name: c
Friend added.

The Social Network
1. Join Network
2. Leave Network
3. Update Profile
4. Add Friend
5. Remove Friend
6. Search Profile
7. Display Network
8. Display Friend's Friends
9. Exit
Enter your choice: 8
Enter name:
a
Enter friend's name:
c
Friends:
b
a

The Social Network
1. Join Network
2. Leave Network
3. Update Profile
4. Add Friend
5. Remove Friend
6. Search Profile
7. Display Network
8. Display Friend's Friends
9. Exit
Enter your choice: 7
Network Members:
a
b
c
Friendship Graph:
c b 0.0 a 0.0
b a 0.0 c 0.0
a b 0.0 c 0.0

The Social Network
1. Join Network
2. Leave Network
3. Update Profile
4. Add Friend
5. Remove Friend
6. Search Profile
7. Display Network
8. Display Friend's Friends
9. Exit
Enter your choice: 9

Thanks for using "Better Call Stack" Social Network!
Hope to see you soon. Goodbye!


Process finished with exit code 0
*/
