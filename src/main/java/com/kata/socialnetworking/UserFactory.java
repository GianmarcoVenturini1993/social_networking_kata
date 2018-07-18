package com.kata.socialnetworking;

import java.util.HashMap;

/*
A base cluster of users is shown here. A map was created to facilitate user search in main method

NB: although user creation is not concerned in the exercise draft/theme, it's implemented here for future upgrades:
    It's very likely to think of a new command for user creation, so that I can call this basic factory and add a user
    to the cluster.
 */

public class UserFactory {

    private static HashMap<String, SocialUser> MAPPING;
    static {
        MAPPING = new HashMap<>();
        MAPPING.put("Alice", new SocialUser(("Alice")));
        MAPPING.put("Bob", new SocialUser("Bob"));
        MAPPING.put("Charlie", new SocialUser("Charlie"));
    }

    public static SocialUser getUser(String name) {
        try {
            return MAPPING.get(name);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static SocialUser createUser(String name) {
        SocialUser user = new SocialUser(name);

        if (getUser(name) == null) {
            MAPPING.put(name, user);
            return user;
        } else
            return null;
    }
}

