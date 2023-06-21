/*
Group Name: "Better Call Stack".
Members: Linh Pham, Huiguang Ma, Java Singh and Vincent Xayasak.
Class Section: CIS 22C 46796
Instructor: Mirsaeid Abolghasemi
Date: 5/28/23

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
    private static UndirectedGraph<Profile> socialNetwork = new UndirectedGraph<>();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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
                System.out.println("8. Exit");
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

                        socialNetwork.addVertex(new Profile(joinName, joinImage, joinStatus));
                        break;
                    case 2:
                        System.out.print("Enter name: ");
                        String leaveName = scanner.nextLine();
                        Profile leaveProfile = new Profile(leaveName, "", "");
                        //remove profile from network
                        socialNetwork.removeVertex(leaveProfile);
                        break;
                    case 3:
                        System.out.print("Enter your name: (your name should be in the network) ");
                        String updateName = scanner.nextLine();
                        //find profile in network
                        Profile updateProfile = new Profile(updateName, "", "");
                        //update profile
                        while(!socialNetwork.hasVertex(updateProfile)){
                            System.out.print("Your name is not in the network. Please enter your name again: ");
                            updateName = scanner.nextLine();
                            updateProfile = new Profile(updateName, "", "");
                        }
                        socialNetwork.removeVertex(updateProfile);
                        //remove the old profile first
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
                        updateProfile.setImage(newImage);
                        updateProfile.setStatus(newStatus);
                        socialNetwork.addVertex(updateProfile);
                        break;
                    case 4:
                        System.out.print("Enter name: ");
                        String addName = scanner.nextLine();
                        Profile addProfile = new Profile(addName, "", "");
                        while(!socialNetwork.hasVertex(addProfile)){
                            System.out.print("Your name is not in the network. Please enter your name again: ");
                            addName = scanner.nextLine();
                            addProfile = new Profile(addName, "", "");
                        }
                        System.out.print("Enter friend's name: ");
                        String friendName = scanner.nextLine();
                        Profile friendProfile = new Profile(friendName, "", "");
                        while(!socialNetwork.hasVertex(friendProfile)){
                            System.out.print("Your friend's name is not in the network. Please enter your friend's name again: ");
                            friendName = scanner.nextLine();
                            friendProfile = new Profile(friendName, "", "");
                        }
                        socialNetwork.addEdge(addProfile, friendProfile);
                        break;
                    case 5:
                        System.out.print("Enter name: ");
                        String removeName = scanner.nextLine();
                        Profile removeProfile = new Profile(removeName, "", "");
                        while(!socialNetwork.hasVertex(removeProfile)){
                            System.out.print("Your name is not in the network. Please enter your name again: ");
                            removeName = scanner.nextLine();
                            removeProfile = new Profile(removeName, "", "");
                        }
                        System.out.print("Enter friend's name: ");
                        String removeFriendName = scanner.nextLine();
                        Profile removeFriendProfile = new Profile(removeFriendName, "", "");
                        while(!socialNetwork.hasVertex(removeFriendProfile)){
                            System.out.print("Your friend's name is not in the network. Please enter your friend's name again: ");
                            removeFriendName = scanner.nextLine();
                            removeFriendProfile = new Profile(removeFriendName, "", "");
                        }
                        socialNetwork.removeEdge(removeProfile, removeFriendProfile);
                        break;
                    case 6:
                        System.out.print("Enter name to search: ");
                        String searchName = scanner.nextLine();
                        Profile searchProfile = new Profile(searchName, "", "");
                        while(!socialNetwork.hasVertex(searchProfile)){
                            System.out.print("Your name is not in the network. Please enter your name again: ");
                            searchName = scanner.nextLine();
                            searchProfile = new Profile(searchName, "", "");
                        }
                        System.out.println("Friends of " + searchName + ":");
                        searchProfile.printProfile();
                        break;
                    case 7:
                        socialNetwork.displayEdges();
                        break;
                    case 8:
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