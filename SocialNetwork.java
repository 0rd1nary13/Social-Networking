/*
Group Name: "Better Call Stack".
Members: Linh Pham, Huiguang Ma, Java Singh and Vincent Xayasak.
Class Section: CIS 22C 46796
Instructor: Mirsaeid Abolghasemi
Date: 6/23/23

The given code represents a social network implementation using Java classes.
The `Profile` class defines a user profile with attributes such as name, image, status,
and a list of friends. The `SocialNetwork` class serves as the main class for managing
profiles within the network. It utilizes an `ArrayList` to store all the profiles and a
custom `LinkedDictionary` class to efficiently store and retrieve profiles by their names.
The program allows users to join or leave the network, update their profiles, add or remove
friends, search for profiles, and display the network's members. The code demonstrates the use
of object-oriented programming principles to model a social network and utilizes data structures
like `ArrayList` and `LinkedDictionary` to store and organize the profiles.
*/

import java.util.ArrayList;

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
}

public class SocialNetwork {
    private ArrayList<Profile> profiles;
    private LinkedDictionary<String, Profile> profileDictionary;

    public SocialNetwork() {
        profiles = new ArrayList<>();
        profileDictionary = new LinkedDictionary<>();
    }

    public void joinNetwork(String name, String image, String status) {
        if (profileDictionary.contains(name)) {
            System.out.println("Profile already in network");
        } else {
            Profile newProfile = new Profile(name, image, status);
            profiles.add(newProfile);
            profileDictionary.add(name, newProfile);
            System.out.println("Joined the network.");
        }
    }

    public void leaveNetwork(String name) {
        Profile removedProfile = profileDictionary.remove(name);
        if (removedProfile != null) {
            profiles.remove(removedProfile);
            System.out.println("Left the network.");
        } else {
            System.out.println("Profile not found.");
        }
    }

    public void updateProfile(String name, String newImage, String newStatus) {
        Profile profile = profileDictionary.getValue(name);
        if (profile != null) {
            profile.setImage(newImage);
            profile.setStatus(newStatus);
            System.out.println("Profile updated.");
        } else {
            System.out.println("Profile not found.");
        }
    }

    public void addFriend(String name, String friendName) {
        Profile profile = profileDictionary.getValue(name);
        Profile friendProfile = profileDictionary.getValue(friendName);

        if (profile != null && friendProfile != null && !name.equals(friendName)) {
            if (!profile.getFriends().contains(friendProfile)) {
                profile.addFriend(friendProfile);
                friendProfile.addFriend(profile);
                System.out.println("Friend added.");
            } else {
                System.out.println("Friend already added.");
            }
        } else {
            System.out.println("Profile(s) not found.");
        }
    }

    public void removeFriend(String name, String friendName) {
        Profile profile = profileDictionary.getValue(name);
        Profile friendProfile = profileDictionary.getValue(friendName);

        if (profile != null && friendProfile != null && !name.equals(friendName)) {
            if (profile.getFriends().contains(friendProfile)) {
                profile.removeFriend(friendProfile);
                friendProfile.removeFriend(profile);
                System.out.println("Friend removed.");
            } else {
                System.out.println("Friend not found.");
            }
        } else {
            System.out.println("Profile(s) not found.");
        }
    }

    public void searchProfile(String name) {
        Profile profile = profileDictionary.getValue(name);
        if (profile != null) {
            System.out.println("Profile found:");
            System.out.println("Name: " + profile.getName());
            System.out.println("Image: " + profile.getImage());
            System.out.println("Status: " + profile.getStatus());

            ArrayList<Profile> friendList = profile.getFriends();
            System.out.print("Friends: ");
            for (int i = 0; i < friendList.size(); i++) {
                Profile friend = friendList.get(i);
                System.out.print(friend.getName());
                if (i < friendList.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            System.out.println("Profile not found.");
        }
    }

    public void displayNetwork() {
        System.out.println("Network Members:");
        for (Profile profile : profiles) {
            System.out.println(profile.getName());
        }
    }

    public void displayFriends(String name) {
        Profile profile = profileDictionary.getValue(name);
        if (profile != null) {
            System.out.println("Friends:");
            ArrayList<Profile> friendList = profile.getFriends();
            for (Profile friend : friendList) {
                System.out.println(friend.getName());
            }
        } else {
            System.out.println("Profile not found.");
        }
    }
}
