package com.example.flashcardapp;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question;
    private String[] options;
    private String answer;

    public Question(String question, String[] options, String answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }

    public static List<Question> getSampleQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("What does JVM stand for?", new String[]{"Java Virtual Machine", "Java Verified Mode", "Just Virtual Memory", "Joint Value Model"}, "Java Virtual Machine"));
        questions.add(new Question("Which keyword is used to inherit a class in Java?", new String[]{"extends", "implements", "inherit", "super"}, "extends"));
        questions.add(new Question("Which data structure uses LIFO?", new String[]{"Queue", "Array", "Stack", "List"}, "Stack"));
        questions.add(new Question("Which method is the entry point in a Java application?", new String[]{"start()", "init()", "main()", "run()"}, "main()"));
        questions.add(new Question("Which one is not a Java access modifier?", new String[]{"protected", "default", "internal", "private"}, "internal"));
        questions.add(new Question("What does SQL stand for?", new String[]{"Structured Query Language", "Simple Query Logic", "Standard Query Language", "Sequence Query Language"}, "Structured Query Language"));
        questions.add(new Question("What is the result of 3 + 4 * 2?", new String[]{"14", "11", "10", "9"}, "11"));
        questions.add(new Question("Which component is responsible for garbage collection in Java?", new String[]{"JVM", "JDK", "JRE", "GC Thread"}, "JVM"));
        questions.add(new Question("Which version control system is widely used?", new String[]{"Docker", "Jenkins", "Git", "Maven"}, "Git"));
        questions.add(new Question("What does HTML stand for?", new String[]{"HyperText Markup Language", "Hyperlink and Text Markup Language", "Home Tool Markup Language", "Hyperlinking Text Management Language"}, "HyperText Markup Language"));

        return questions;
    }
}
