package com.tcrypto.models;

import javax.persistence.Embeddable;

@Embeddable
public class SocialNetworks {

    public SocialNetworks() {}
    public SocialNetworks(String github, String telegram, String vkontakte) {
        this.github = github;
        this.telegram = telegram;
        this.vkontakte = vkontakte;
    }
    private String github;
    private String telegram;
    private String vkontakte;

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getVkontakte() {
        return vkontakte;
    }

    public void setVkontakte(String vkontakte) {
        this.vkontakte = vkontakte;
    }
}
