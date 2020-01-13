package com.andyduncan.rockpaperscissorsspocklizard.web;

public class BodyRequest {
    private String text;

    public BodyRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
