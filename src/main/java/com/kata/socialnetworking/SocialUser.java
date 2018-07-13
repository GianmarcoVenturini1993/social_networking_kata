package com.kata.socialnetworking;

public class SocialUser {

    private String name;
    private StringBuilder timeline;

    public SocialUser(String str) {
        this.name = str;
        this.timeline = new StringBuilder();
    }


    public void updateTimeline(String input) {
        this.timeline.append(input + "\n"); //no date for now
    }

    public String getTimeline() {

        return timeline.toString();
    }
}

