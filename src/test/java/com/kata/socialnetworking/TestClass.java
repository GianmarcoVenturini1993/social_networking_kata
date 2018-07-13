package com.kata.socialnetworking;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;


public class TestClass {

    private SocialUser alice = new SocialUser("Alice");;
    private SocialUser bob = new SocialUser("Bob");
    private SocialUser charlie = new SocialUser("Charlie");;

    @Test
    public void postingOwnTimelineTest() throws ParseException {

        String input = "I love the weather today";
        alice.updateTimeline(input);

        assertThat(alice.getTimeline()).isEqualTo("I love the weather today (0 seconds ago)\n");
    }

    @Test
    public void postingOwnTimelineMultipleTest() throws ParseException {

        String input = "I love the weather today";
        String input2 = "This is a great day";
        alice.updateTimeline(input);
        alice.updateTimeline(input2);

        assertThat(alice.getTimeline()).isEqualTo("This is a great day (0 seconds ago)\nI love the weather today (0 seconds ago)\n");
    }

    @Test
    public void postingOwnTimelineMultipleUserTest() throws ParseException {

        String input = "I love the weather today";
        String input2 = "This is a great day";
        String inputBob = "Damn! We lost!";
        String inputBob2 = "Good game though.";

        alice.updateTimeline(input);
        alice.updateTimeline(input2);
        bob.updateTimeline(inputBob);
        bob.updateTimeline(inputBob2);

        assertThat(alice.getTimeline()).isEqualTo("This is a great day (0 seconds ago)\nI love the weather today (0 seconds ago)\n");
        assertThat(bob.getTimeline()).isEqualTo("Good game though. (0 seconds ago)\nDamn! We lost! (0 seconds ago)\n");
    }

    @Test
    public void followingAndprintingWallTest() throws ParseException {

        String input = "I'm in New York today! Anyone wants to have a coffee?";
        String inputAlice = "I love the weather today";

        alice.updateTimeline(inputAlice);
        charlie.updateTimeline(input);
        charlie.follows(alice);


        assertThat(alice.getTimeline()).isEqualTo("I love the weather today (0 seconds ago)\n");
        assertThat(charlie.getTimeline()).isEqualTo("I'm in New York today! Anyone wants to have a coffee? (0 seconds ago)\n");
        assertThat(charlie.printWall()).isEqualTo("Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)\nAlice - I love the weather today (5 minutes ago)\n");
        }


}
