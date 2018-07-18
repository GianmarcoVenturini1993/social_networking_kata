package com.kata.socialnetworking.command;

import com.kata.socialnetworking.Users;

public class FollowUser implements CommandInterface {
    @Override
    public void doCommand(String cmd, String delimiter) {
        String[] param = cmd.split(delimiter);
        String user1 = param[0];
        String user2 = param[1];

        Users.getUser(user1).follows(Users.getUser(user2));
    }
}
