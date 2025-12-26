package ru.otus.model;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Difficulty {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard"),
    VERY_HARD("very_hard");

    private static final Map<String, Difficulty> BY_DIFFICULTY =
            Arrays.stream(values())
                    .collect(Collectors.toMap(
                            difficulty -> normalizeInput(difficulty.code),
                            Function.identity()
                    ));
    private final String code;

    Difficulty(String code) {
        this.code = code;
    }

    public static Difficulty from(String difficultyString) {
        if (difficultyString == null) {
            throw new IllegalArgumentException("DifficultyString cannot be null");
        }
        Difficulty result = BY_DIFFICULTY.get(normalizeInput(difficultyString));
        if (result == null) {
            throw new IllegalArgumentException("Unknown difficulty name: '" + difficultyString + "'");
        }
        return result;
    }

    private static String normalizeInput(String input) {
        return input.trim().toLowerCase(Locale.ROOT);
    }
}