package ru.bardinpetr.itmo.lab_4.story;

import ru.bardinpetr.itmo.lab_4.story.stories.MainStory;

public class App {
    public static void main(String[] args) {
        var story = (new MainStory()).compile();
        System.out.println(story.tell());
    }
}
