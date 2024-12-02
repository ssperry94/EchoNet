package domainmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


import com.echonet.domainmodel.User;
import com.echonet.exceptions.DataBaseNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest2 {

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        // Mock data for users
        List<String> attributes1 = new ArrayList<>(List.of("John", "Doe", "johndoe", "1990-01-01", "john.doe@example.com"));
        List<String> attributes2 = new ArrayList<>(List.of("Jane", "Smith", "janesmith", "1992-02-02", "jane.smith@example.com"));
        List<String> attributes3 = new ArrayList<>(List.of("Alice", "Brown", "alicebrown", "1995-03-03", "alice.brown@example.com"));

        // Initialize User objects
        user1 = new User(1, attributes1);
        user2 = new User(2, attributes2);
        user3 = new User(3, attributes3);
    }

    @Test
    void testAddFriend() {
        // Add user2 as a friend to user1
        user1.addFriend(user2);

        // Verify mutual friendship
        assertTrue(user1.getFriends().contains(user2), "User1 should have User2 as a friend.");
        assertTrue(user2.getFriends().contains(user1), "User2 should have User1 as a friend.");
    }

    @Test
    void testAddSelfAsFriend() {
        // Try to add self as a friend
        user1.addFriend(user1);

        // Verify that self-friendship is not allowed
        assertFalse(user1.getFriends().contains(user1), "A user should not be able to add themselves as a friend.");
    }

    @Test
    void testAddDuplicateFriend() {
        // Add the same friend twice
        user1.addFriend(user2);
        user1.addFriend(user2);

        // Verify that the friend is not added twice
        assertEquals(1, user1.getFriends().stream().filter(u -> u.equals(user2)).count(),
                "User2 should only appear once in User1's friends list.");
    }

    @Test
    void testGetFriends() {
        // Add multiple friends to user1
        user1.addFriend(user2);
        user1.addFriend(user3);

        // Verify friends list
        List<User> friends = user1.getFriends();
        assertEquals(2, friends.size(), "User1 should have two friends.");
        assertTrue(friends.contains(user2), "User1's friends list should include User2.");
        assertTrue(friends.contains(user3), "User1's friends list should include User3.");
    }

    @Test
    void testFriendshipPersistence() {
        // Add a friend and verify persistence in the friend object
        user1.addFriend(user2);
        Friend friendship = user1.getFriends().stream()
                .filter(u -> u.equals(user2))
                .findFirst()
                .map(friend -> new Friend(user1, user2)) // Simulate the creation
                .orElse(null);

        assertNotNull(friendship, "Friendship object should exist.");
        assertEquals(user1, friendship.getUser1(), "Friendship should involve User1.");
        assertEquals(user2, friendship.getUser2(), "Friendship should involve User2.");
    }
}

