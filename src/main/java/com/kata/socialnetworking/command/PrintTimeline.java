package com.kata.socialnetworking.command;

import com.kata.socialnetworking.Users;

import java.text.ParseException;

public class PrintTimeline implements CommandInterface {
    @Override
    public void doCommand(String cmd, String delimiter) {
        try {
            System.out.println(Users.getUser(cmd).printTimeline());
        } catch (ParseException f) {
            f.printStackTrace();
        }
    }
}
