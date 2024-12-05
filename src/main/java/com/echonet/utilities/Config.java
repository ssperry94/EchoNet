package com.echonet.utilities;

import com.echonet.domainmodel.User;

/**
 * A class containing all public static final variables that represent constants
 */
public class Config {
    //connecting to databases
    public static final String DATABASE_INIT = "jdbc:sqlite:echodata.db";
    public static final String DATABASE_PATH = "echodata.db";
    public static final String DATABASE_DRIVER = "org.sqlite.JDBC";
    public static final String TEST_DATABASE_INIT = "jdbc:sqlite:src/test/echodatatest.db";

    //stores actual table names
    public static final String LOGIN_TABLE = "RegistrationInfo";
    public static final String USER_TABLE = "UserInformation";
    public static final String POSTS_TABLE = "Posts";
    public static final String MESSAGES_TABLE = "Messages";
    public static final User LOGGED_IN_USER = null; 
}