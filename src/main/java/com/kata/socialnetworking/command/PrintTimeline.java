package com.kata.socialnetworking.command;

import com.kata.socialnetworking.UserFactory;

import java.text.ParseException;

public class PrintTimeline implements CommandInterface {
    @Override
    public void doCommand(String cmd, String delimiter) {
        try {
            System.out.println(UserFactory.getUser(cmd).printTimeline());
        } catch (ParseException f) {
            f.printStackTrace();
        }
    }
}
