import java.util.*;

class FriendNode {
    int friendID;
    FriendNode next;

    FriendNode(int friendID) {
        this.friendID = friendID;
        this.next = null;
    }
}

class UserNode {
    int userID;
    String name;
    int age;
    FriendNode frListedHead;
    UserNode next;

    UserNode(int userID, String name, int age) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.frListedHead = null;
        this.next = null;
    }

    void addFriend(int friendID) {
        FriendNode newFriend = new FriendNode(friendID);
        newFriend.next = frListedHead;
        frListedHead = newFriend;
    }

    void removeFriend(int friendID) {
        FriendNode current = frListedHead, prev = null;
        while (current != null) {
            if (current.friendID == friendID) {
                if (prev == null) {
                    frListedHead = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    List<Integer> getFriendsIDs() {
        List<Integer> ids = new ArrayList<>();
        FriendNode current = frListedHead;
        while (current != null) {
            ids.add(current.friendID);
            current = current.next;
        }
        return ids;
    }

    int countFriends() {
        int count = 0;
        FriendNode current = frListedHead;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}

class SocialNetwork {
    private UserNode head;

    public void addUser(int userID, String name, int age) {
        UserNode newUser = new UserNode(userID, name, age);
        newUser.next = head;
        head = newUser;
    }

    public UserNode searchByID(int userID) {
        UserNode current = head;
        while (current != null) {
            if (current.userID == userID) return current;
            current = current.next;
        }
        return null;
    }

    public UserNode searchByName(String name) {
        UserNode current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) return current;
            current = current.next;
        }
        return null;
    }

    public void addFrConnection(int userID1, int userID2) {
        UserNode user1 = searchByID(userID1);
        UserNode user2 = searchByID(userID2);

        if (user1 != null && user2 != null) {
            user1.addFriend(userID2);
            user2.addFriend(userID1);
        }
    }

    public void removeFrConnection(int userID1, int userID2) {
        UserNode user1 = searchByID(userID1);
        UserNode user2 = searchByID(userID2);

        if (user1 != null && user2 != null) {
            user1.removeFriend(userID2);
            user2.removeFriend(userID1);
        }
    }

    public void displayFr(int userID) {
        UserNode user = searchByID(userID);
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        System.out.println(user.name + "'s Friends:");
        FriendNode friend = user.frListedHead;
        while (friend != null) {
            UserNode friendUser = searchByID(friend.friendID);
            System.out.println("- " + friendUser.name + " (ID = " + friend.friendID + ")");
            friend = friend.next;
        }
    }

    public void findMutualFr(int userID1, int userID2) {
        UserNode user1 = searchByID(userID1);
        UserNode user2 = searchByID(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found");
            return;
        }

        Set<Integer> user1Friends = new HashSet<>(user1.getFriendsIDs());
        List<Integer> mutuals = new ArrayList<>();

        FriendNode current = user2.frListedHead;
        while (current != null) {
            if (user1Friends.contains(current.friendID)) {
                mutuals.add(current.friendID);
            }
            current = current.next;
        }
        System.out.println("Mutual friends:");
        if (mutuals.isEmpty()) {
            System.out.println("None");
        } else {
            for (int id : mutuals) {
                UserNode mutual = searchByID(id);
                System.out.println("- " + mutual.name + " (ID = " + id + ")");
            }
        }
    }

    public void countAllFr() {
        UserNode current = head;
        while (current != null) {
            System.out.println(current.name + " has " + current.countFriends() + " friends");
            current = current.next;
        }
    }
}

public class SocialMedia {
    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();

        network.addUser(101, "Ruchi", 22);
        network.addUser(102, "Vandit", 14);
        network.addUser(103, "Bhadrik", 51);
        network.addUser(104, "Ami", 48);

        network.addFrConnection(101, 102);
        network.addFrConnection(101, 103);
        network.addFrConnection(102, 103);
        network.addFrConnection(103, 104);

        network.displayFr(101);

        network.findMutualFr(101, 102);

        network.countAllFr();

        network.removeFrConnection(101, 102);

        System.out.println("After removing:");
        network.displayFr(101);
        network.displayFr(102);
    }
}
