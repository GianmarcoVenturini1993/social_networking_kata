package com.kata.socialnetworking;

import java.util.HashMap;

/*
This class could be use to extend the concept of User creation and management. Maybe with a Factory pattern to leave the client
the possibility to create a new user by cli without explicitly invoke costructor: this feature is not required thou...
 */

class Users {

    private static SocialUser alice = new SocialUser("Alice");
    private static SocialUser bob = new SocialUser("Bob");
    private static SocialUser charlie = new SocialUser("Charlie");

    static HashMap<String, SocialUser> MAPPING;
    static {
        MAPPING = new HashMap<>();
        MAPPING.put("Alice", alice);
        MAPPING.put("Bob", bob);
        MAPPING.put("Charlie", charlie);
    }
}

