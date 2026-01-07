package ru.otus.web;

import java.util.List;

public record TestDto(String name, int age, List<String> moods) {}
