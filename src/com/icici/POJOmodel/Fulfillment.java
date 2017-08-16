package com.icici.POJOmodel;

public class Fulfillment
{
    private String speech;

    private String[] messages;

    public String getSpeech ()
    {
        return speech;
    }

    public void setSpeech (String speech)
    {
        this.speech = speech;
    }

    public String[] getMessages ()
    {
        return messages;
    }

    public void setMessages (String[] messages)
    {
        this.messages = messages;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [speech = "+speech+", messages = "+messages+"]";
    }
}
			