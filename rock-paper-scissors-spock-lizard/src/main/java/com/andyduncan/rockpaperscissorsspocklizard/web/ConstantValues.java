package com.andyduncan.rockpaperscissorsspocklizard.web;

public class ConstantValues {
    final int ROCK = 1;
    final int PAPER = 2;
    final int SCISSORS = 3;
    final int LIZARD = 4;
    final int SPOCK = 5;

    final int PLAYER_WINS = 6;
    final int COMPUTER_WINS = 7;
    final int TIE = 8;

    public ConstantValues() {
    }

    // can't set these, so only getters

    public int getROCK() {
        return ROCK;
    }

    public int getPAPER() {
        return PAPER;
    }

    public int getSCISSORS() {
        return SCISSORS;
    }

    public int getLIZARD() {
        return LIZARD;
    }

    public int getSPOCK() {
        return SPOCK;
    }

    public int getPLAYER_WINS() {
        return PLAYER_WINS;
    }

    public int getCOMPUTER_WINS() {
        return COMPUTER_WINS;
    }

    public int getTIE() {
        return TIE;
    }
}
