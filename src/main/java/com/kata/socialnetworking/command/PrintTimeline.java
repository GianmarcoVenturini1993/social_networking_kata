package com.kata.socialnetworking.command;

import com.kata.socialnetworking.UserFactory;

import java.text.ParseException;

/*
Maybe I should insert here the createUser method in an if branch:
    if(UserFactory.getUser(cmd) == null)
        create user...
    else
        do print timeline

...but for now, it's not required...design for change

 */
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
