package com.g_draflab.orderit.Models;

import com.stripe.android.model.Card;

public class StripeResponse {
    String id, object;
    Card card;
    String client_id;
    long created;
    boolean livemode;
    String type;
    boolean used;

    public StripeResponse(String id, String object, Card card, String client_id, long created, boolean livemode, String type, boolean used) {
        this.id = id;
        this.object = object;
        this.card = card;
        this.client_id = client_id;
        this.created = created;
        this.livemode = livemode;
        this.type = type;
        this.used = used;
    }

    public StripeResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public boolean isLivemode() {
        return livemode;
    }

    public void setLivemode(boolean livemode) {
        this.livemode = livemode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
