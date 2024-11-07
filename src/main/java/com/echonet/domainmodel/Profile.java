package com.echonet.domainmodel;

import java.sql.ResultSet;
import java.util.Set;

public class Profile extends User{
    private Set<Profile> friends;

    public Profile(final int ID, final ResultSet rs) {
        super(ID);
    }

    // Friend manegement
    public Set<Profile> getFriends() {
        return friends;
    }

    public boolean addFriend(Profile friend){
        if(friend == null){             // checks if friend exists
            return false;
        }
        if(friend == this){             // checks that friend is not yourself
            return false;
        }
        if(friends.contains(friend)){   // check that you are not already friends
            return false;
        }

        friends.add(friend);            // adds requested profile to your freinds list
        friend.friends.add(this);       // adds you to profiles freinds list
        return true;
    }   

    public boolean removeFriend(Profile friend){
        if(friend == null || !friends.contains(friend)){
            return false;
        }

        friends.remove(friend);         // removes friend from friend lsit
        friend.friends.remove(this);    // removes you from friends list
        return true;
    }
}
