package com.icici.POJOmodel;

public class Parameters
{
    private String endtime;

    private String username;

    private String starttime;

    private String date;

    private String participants;

    public String getEndtime ()
    {
        return endtime;
    }

    public void setEndtime (String endtime)
    {
        this.endtime = endtime;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getStarttime ()
    {
        return starttime;
    }

    public void setStarttime (String starttime)
    {
        this.starttime = starttime;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getParticipants ()
    {
        return participants;
    }

    public void setParticipants (String participants)
    {
        this.participants = participants;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [endtime = "+endtime+", username = "+username+", starttime = "+starttime+", date = "+date+", participants = "+participants+"]";
    }
}
