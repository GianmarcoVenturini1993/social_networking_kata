package com.kata.socialnetworking;


import org.junit.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.*;


public class TestClass {

    private SocialUser alice = new SocialUser("Alice");
    private SocialUser bob = new SocialUser("Bob");
    private SocialUser charlie = new SocialUser("Charlie");

    @Test
    public void postingOwnTimelineTest() throws ParseException, InterruptedException {

        String input = "I love the weather today";
        alice.updateTimeline(input);

        Thread.sleep(5000);
        assertThat(alice.printTimeline()).isEqualTo("I love the weather today (5 seconds ago)\n");
    }

    @Test
    public void postingOwnTimelineMultipleTest() throws ParseException, InterruptedException {

        String input = "I love the weather today";
        String input2 = "This is a great day";
        alice.updateTimeline(input);
        Thread.sleep(2000);
        alice.updateTimeline(input2);
        Thread.sleep(1000);

        assertThat(alice.printTimeline()).isEqualTo("This is a great day (1 seconds ago)\nI love the weather today (3 seconds ago)\n");
    }

    @Test
    public void postingOwnTimelineMultipleUserTest() throws ParseException, InterruptedException {

        String input = "I love the weather today";
        String input2 = "This is a great day";
        String inputBob = "Damn! We lost!";
        String inputBob2 = "Good game though.";

        alice.updateTimeline(input);
        Thread.sleep(1000);
        alice.updateTimeline(input2);
        Thread.sleep(1000);
        bob.updateTimeline(inputBob);
        Thread.sleep(2000);
        bob.updateTimeline(inputBob2);
        Thread.sleep(2000);

        assertThat(alice.printTimeline()).isEqualTo("This is a great day (5 seconds ago)\nI love the weather today (6 seconds ago)\n");
        assertThat(bob.printTimeline()).isEqualTo("Good game though. (2 seconds ago)\nDamn! We lost! (4 seconds ago)\n");
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

    @Test
    public void followingAndprintingmultipleUserWallTest() throws ParseException, InterruptedException {

        String inputAlice = "I love the weather today";
        String inputCharlie = "I'm in New York today! Anyone wants to have a coffee?";
        String inputBob = "Damn! We lost!";
        String inputBob2 = "Good game though.";

        alice.updateTimeline(inputAlice);
        Thread.sleep(4000);
        charlie.updateTimeline(inputCharlie);
        Thread.sleep(4000);
        charlie.follows(alice);

        bob.updateTimeline(inputBob);
        Thread.sleep(2000);
        bob.updateTimeline(inputBob2);
        Thread.sleep(1000);
        charlie.follows(bob);


        assertThat(alice.printTimeline()).isEqualTo("I love the weather today (11 seconds ago)\n");
        assertThat(charlie.printTimeline()).isEqualTo("I'm in New York today! Anyone wants to have a coffee? (7 seconds ago)\n");
        assertThat(charlie.printWall()).isEqualTo("Bob - Good game though. (1 seconds ago)\nBob - Damn! We lost! (3 seconds ago)\nCharlie - I'm in New York today! Anyone wants to have a coffee? (7 seconds ago)\nAlice - I love the weather today (11 seconds ago)\n");
    }

    @Test
    public void userCreation() {

        SocialUser jimmy = UserFactory.createUser("Jimmy");
        SocialUser timmy = UserFactory.createUser("Timmy");

        assertThat(jimmy.getName()).isEqualTo("Jimmy");
        assertThat(timmy.getName()).isEqualTo("Timmy");
        assertThat(UserFactory.getUser("Jimmy")).isEqualTo(jimmy);
        assertThat(UserFactory.getUser("Timmy")).isEqualTo(timmy);

    }


}
