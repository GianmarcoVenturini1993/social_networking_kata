package com.kata.socialnetworking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.ocpsoft.prettytime.PrettyTime;

public class SocialUser {

    private String name;
    private ArrayList<HashMap<String, String>> timeline;

    public SocialUser(String str) {
        this.name = str;
        this.timeline = new ArrayList<>();
    }


    public void updateTimeline(String input) {

        Date date = new Date();

        HashMap<String, String> post = new HashMap<>();
        post.put("body", input);
        post.put("date", date.toString());

        this.timeline.add(post);

    }

    public String getTimeline() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date now = new Date();
        Date curr_date = formatter.parse(now.toString());

        StringBuilder result = new StringBuilder();

        for (int i = timeline.size() - 1; i >= 0; i--) {
            Date postTime = formatter.parse(timeline.get(i).get("date"));
            System.out.println();
            result.append(timeline.get(i).get("body") + " (" + TimeUnit.MILLISECONDS.toMinutes(curr_date.getTime() - postTime.getTime()) + " minutes ago)" + "\n");
        }

        return result.toString();
    }
}

