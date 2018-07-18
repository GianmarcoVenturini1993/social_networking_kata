package com.kata.socialnetworking.command;

import com.kata.socialnetworking.Users;

import java.text.ParseException;

public class PrintUserWall implements CommandInterface {
    @Override
    public void doCommand(String cmd, String delimiter) {
        String[] param = cmd.split(delimiter);

        try {
            System.out.println(Users.getUser(param[0]).printWall());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
