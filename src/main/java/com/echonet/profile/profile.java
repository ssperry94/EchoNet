package com.echonet.profile;

import java.util.HashSet;
import java.util.Set;

public class profile {
    private String name;
    private int age;
    private String birthday;
    private String email;
    private Set<profile> friends;

    public void Profile(String name, int age, String birthday, String email) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.email = email;
    }

    // name managment 
    public String getName(){
        return name;
    }

    public void setName(String name){
        if(name != null && !name.trim().isEmpty()){
            this.name = name;
        }
        else{
            System.out.println("Missing Field: Name"); //Placeholder for gui error message or gui interaction
        }
    }

    // age management
    public int getAge(){
        return age;
    }

    public void setAge(int age){
        if(age > 0){
            this.age = age;
        }
        else{
            System.out.println("Missing Field: Age"); // Placeholder for gui stuff
        }
    }

    // birthday manegment
    public String getBirthday(){
        return birthday;
    }
    
    public void setBirthday(String birthday){
        if(birthday !=null && !birthday.trim().isEmpty()){
            this.birthday = birthday;
        }
        else{
            System.out.println("Missing field: birthday"); // Placeholder
        }
    }

    // email maegegment
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        if(email !=null && !email.trim().isEmpty()){
            this.email = email;
        }
        else{
            System.out.println("Missing field: email"); // Placeholder
        }
    }

    // Friend manegement
    public Set<profile> getFriends() {
        return friends;
    }

    public boolean addFriend(profile friend){
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

    public boolean removeFriend(profile friend){
        if(friend == null || !friends.contains(friend)){
            return false;
        }

        friends.remove(friend);         // removes friend from friend lsit
        friend.friends.remove(this);    // removes you from friends list
        return true;
    }
}
