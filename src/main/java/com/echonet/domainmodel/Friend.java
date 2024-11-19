package com.echonet.domainmodel;

public class Friend extends User{
    private final User user1;
    private final User user2;

    public Friend(User user1, User user2) {
        super(user1.getID());
        this.user1 = user1;
        this.user2 = user2;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

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

