
# Echonet

Team 7's CS-321 Group Project. EchoNet is a simple social media platform where you can interact with your freinds, post statuses, and send messages easily. 


## Disclaimer
**This Project was made for UAH's CS-321 Sememster project, and should never be used to store actual user informtaion! Usernames, Passwords, and anything written to our database is NOT encrypted for the purposes of this project.**

## Group Members
- **Sam Perry**
- **Caleb Blair**
- **Josh Johnson**
- **Sidney Howard**

## Description 
Echonet is a simple social media platform where you can send messages, make posts, and view your profile. This is only a sample, and as such has no network capabilities. All interactions are had with bots, who give automatic responses where appropriate. 
## Build

### Dependacies
- Maven: [Download Maven](https://maven.apache.org/download.cgi)
- Java Version 22 or Later: [Download Java](https://www.oracle.com/java/technologies/downloads/)
- Apache NetBeans: [Download Netbeans](https://netbeans.apache.org/front/main/download/)

### How to Build 

**Using the Jar File**
- navigate to the <insert path here> and click the <insert name of JAR here>

**Using Netbeans** 
- Download NetBeans (see dependancy section)
- Go to File -> Open Project and open Echonet 
- Go to Run -> Clean and Build Project (Echonet)
- Go to Run -> Run Project 

**Command Line and Maven** 
- Open up either the CMD or any bash terminal (preferred)
- `cd` into the projects main Directory (../Echonet) and run the following:
    - `mvn clean install`
    - `mvn exec:java` 


## How To Use

To get started with Echonet, you can either use the pre-compiled JAR file located in <insert JAR dir here>, or build the project yourself using the instruction in the Build section. 

### Logging In 
Upon opening Echonet, you are greeted with an login page. You can either register as a new user, or use one of the following logins:

    - username: `test123`
    - password: `123test` 

    - username: `test321`
    - password: `test321`

    - username: `test456`
    - password: `test456` 

From here, you will be enter Echonet's homepage 

### Registration 
If you'd like to register, simply click the register button, and enter in all the required fields. Be sure not to leave any blank, or the system will not let you register. Once all registration information is validated, you will be brought to Echonet's homepage 

### Messaging 
From Echonet's homepage, click the echochambers button to go to you're messages. From here, you should see this window: 

<insert pic here>

In the center, it should show your message history. If you are using a pre-registered user, you may already see a few messages. If you registered you're own account, you'll see your message history is empty. To send a message, simply click the Send Message button. 

<Insert the message composer pic>

In the top bar, enter the username of the person you wish to send a message to (in this case it can only be one of the ones mentioned in the Login section, or any accounts you've made). In the contents box, you can send any text-based message you'd like. 

If you've sent a message to one of the pre-made accounts, they may decide to message you back. Go back to the main page, and click the update message button. You may have a new message - just an automatic response sent to simulate real messaging. 

