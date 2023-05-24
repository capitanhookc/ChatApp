import java.util.HashMap;
import java.util.Map;

class ChatRoom {
    private String name;
    private Map<String, User> users;

    public ChatRoom(String name) {
        this.name = name;
        this.users = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public void removeUser(User user) {
        users.remove(user.getUsername());
    }

    public void sendMessage(User sender, String message) {
        for (User user : users.values()) {
            if (!user.equals(sender)) {
                user.receiveMessage(name, sender.getUsername(), message);
            }
        }
    }
}

class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void receiveMessage(String roomName, String sender, String message) {
        System.out.println("[" + roomName + "] " + sender + ": " + message);
    }

    public void sendMessage(ChatRoom room, String message) {
        room.sendMessage(this, message);
    }
}

public class ChatApp {
    public static void main(String[] args) {
        // Create chat rooms
        ChatRoom room1 = new ChatRoom("Room 1");
        ChatRoom room2 = new ChatRoom("Room 2");

        // Create users
        User user1 = new User("User1");
        User user2 = new User("User2");
        User user3 = new User("User3");

        // Add users to chat rooms
        room1.addUser(user1);
        room1.addUser(user2);
        room2.addUser(user2);
        room2.addUser(user3);

        // Send messages
        user1.sendMessage(room1, "Hello, everyone!");
        user2.sendMessage(room1, "Hi User1!");
        user3.sendMessage(room2, "Hey there!");

        // Remove user2 from room1
        room1.removeUser(user2);

        // Send messages after user2 is removed
        user1.sendMessage(room1, "User2 has left the room.");
        user3.sendMessage(room1, "Goodbye, User2!");

        // User2 tries to send a message to room1 (will not be received)
        user2.sendMessage(room1, "This message won't be received.");

        // User3 sends a message to room2
       
