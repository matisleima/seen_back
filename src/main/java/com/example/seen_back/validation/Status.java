package com.example.seen_back.validation;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("A"),
    DEACTIVATED("D");

    private final String letter;

    Status(String letter) {
        this.letter = letter;
    }
}
