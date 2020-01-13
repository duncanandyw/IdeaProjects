package com.andyduncan.rockpaperscissorsspocklizard.web;

public final class SingletonScore {
    private static SingletonScore INSTANCE;
    public static int playerWins;
    public static int computerWins;
    public static int ties;

    public SingletonScore() {
    }

    public static SingletonScore getInstance() {
        if (null == INSTANCE) {
            reset();

            INSTANCE = new SingletonScore();
        }

        return INSTANCE;
    }

    public static void reset() {
        playerWins = 0;
        computerWins = 0;
        ties = 0;
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public void setPlayerWins(int playerWins) {
        this.playerWins = playerWins;
    }

    public int getComputerWins() {
        return computerWins;
    }

    public void setComputerWins(int computerWins) {
        this.computerWins = computerWins;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }
}
