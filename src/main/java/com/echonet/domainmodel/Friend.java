package com.echonet.domainmodel;

/**
 * Represents a friendship between two users in the system.
 * This class extends {@code User} to encapsulate a relationship between two users.
 * The order of users does not matter.
 * @Author Caleb Blair
 */
public class Friend extends User{
    private final User user1;
    private final User user2;
    /**
     * Constructs a {@code Friend} object that represents a relationship between two users.
     * 
     * @param user1 the first user in the friendship
     * @param user2 the second user in the friendship
     * @author Caleb Blair
     */
    public Friend(User user1, User user2) {
        super(user1.getID());
        this.user1 = user1;
        this.user2 = user2;
    }

    /**
     * Returns the first user in the friendship.
     * 
     * @return the first user
     * @author Caleb Blair
     */
    public User getUser1() {
        return user1;
    }

    /**
     * Returns the second user in the friendship.
     * 
     * @return the second user
     * @author Caleb Blair
     */
    public User getUser2() {
        return user2;
    }

    /**
     * Checks if this {@code Friend} object is equal to another object.
     * Two {@code Friend} objects are considered equal if they are the same users,
     * regardless of the order of the users.
     * 
     * @param obj the object to compare
     * @return {@code true} if the objects are equal, {@code false} otherwise
     * @author Caleb Blair
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Friend that = (Friend) obj;

        // Friendships are bidirectional, so order shouldn't matter
        return (user1.equals(that.user1) && user2.equals(that.user2)) ||
               (user1.equals(that.user2) && user2.equals(that.user1));
    }

    @Override
    public int hashCode() {
        return user1.hashCode() + user2.hashCode(); // Order-independent hash
    }

    public Object involves(User friend) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'involves'");
    }
}

