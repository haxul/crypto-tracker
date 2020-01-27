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
}
