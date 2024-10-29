package com.echonet.profile;

public class profile {
    private String name;
    private int age;
    private String birthday;
    private String email;

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
}
