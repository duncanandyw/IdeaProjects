package com.andyduncan.rockpaperscissorsspocklizard.web;

public class BodyResponse {
    private String text;

    public BodyResponse() {
        this.text = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
