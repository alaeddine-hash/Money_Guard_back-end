package com.project.un_site_de_planification_et_de_suivi_de_projets.payload.request;

public class ChatBotRequest {
    private String input;
    private int max_length = 50;

    public ChatBotRequest(String input, int max_length) {
        this.input = input;
        this.max_length = max_length;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getMax_length() {
        return max_length;
    }

    public void setMax_length(int max_length) {
        this.max_length = max_length;
    }
}
