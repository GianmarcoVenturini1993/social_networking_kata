package com.kata.socialnetworking;


import org.junit.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.*;


public class TestClass {

    private SocialUser alice = new SocialUser("Alice");;
    private SocialUser bob = new SocialUser("Bob");
    private SocialUser charlie = new SocialUser("Charlie");;

    @Test
    public void postingOwnTimelineTest() throws ParseException {

        String input = "I love the weather today";
        alice.updateTimeline(input);

        assertThat(alice.printTimeline()).isEqualTo("I love the weather today (0 seconds ago)\n");
    }

    @Test
    public void postingOwnTimelineMultipleTest() throws ParseException {

        String input = "I love the weather today";
        String input2 = "This is a great day";
        alice.updateTimeline(input);
        alice.updateTimeline(input2);

        assertThat(alice.printTimeline()).isEqualTo("This is a great day (0 seconds ago)\nI love the weather today (0 seconds ago)\n");
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

        assertThat(alice.printTimeline()).isEqualTo("This is a great day (0 seconds ago)\nI love the weather today (0 seconds ago)\n");
        assertThat(bob.printTimeline()).isEqualTo("Good game though. (0 seconds ago)\nDamn! We lost! (0 seconds ago)\n");
    }

    @Test
    public void followingAndprintingWallTest() throws ParseException, InterruptedException {

        String inputAlice = "I love the weather today";
        String inputCharlie = "I'm in New York today! Anyone wants to have a coffee?";

        alice.updateTimeline(inputAlice);
        Thread.sleep(4000);
        charlie.updateTimeline(inputCharlie);
        Thread.sleep(4000);
        charlie.follows(alice);


        assertThat(alice.printTimeline()).isEqualTo("I love the weather today (8 seconds ago)\n");
        assertThat(charlie.printTimeline()).isEqualTo("I'm in New York today! Anyone wants to have a coffee? (4 seconds ago)\n");
        assertThat(charlie.printWall()).isEqualTo("Charlie - I'm in New York today! Anyone wants to have a coffee? (4 seconds ago)\nAlice - I love the weather today (8 seconds ago)\n");
        }


}
