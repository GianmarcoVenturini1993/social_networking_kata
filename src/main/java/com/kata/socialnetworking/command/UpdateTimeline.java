package com.kata.socialnetworking.command;

import com.kata.socialnetworking.Users;
import com.kata.socialnetworking.command.CommandInterface;

public class UpdateTimeline implements CommandInterface {
    @Override
    public void doCommand(String cmd, String delimiter) {
        String[] param = cmd.split(delimiter);
        String user = param[0];
        String post = param[1];

        Users.getUser(user).updateTimeline(post);
    }
}
