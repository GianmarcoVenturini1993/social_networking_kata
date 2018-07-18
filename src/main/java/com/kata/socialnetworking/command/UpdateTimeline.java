package com.kata.socialnetworking.command;

import com.kata.socialnetworking.UserFactory;

public class UpdateTimeline implements CommandInterface {
    @Override
    public void doCommand(String cmd, String delimiter) {
        String[] param = cmd.split(delimiter);
        String user = param[0];
        String post = param[1];

        UserFactory.getUser(user).updateTimeline(post);
    }
}
