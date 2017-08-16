package com.icici.POJOmodel;


public class Result
{
private String actionIncomplete;

private String source;

private Fulfillment fulfillment;

private String score;

private String action;

private String resolvedQuery;

private String[] contexts;

private Parameters parameters;

private Metadata metadata;

public String getActionIncomplete ()
{
return actionIncomplete;
}

public void setActionIncomplete (String actionIncomplete)
{
this.actionIncomplete = actionIncomplete;
}

public String getSource ()
{
return source;
}

public void setSource (String source)
{
this.source = source;
}

public Fulfillment getFulfillment ()
{
return fulfillment;
}

public void setFulfillment (Fulfillment fulfillment)
{
this.fulfillment = fulfillment;
}

public String getScore ()
{
return score;
}

public void setScore (String score)
{
this.score = score;
}

public String getAction ()
{
return action;
}

public void setAction (String action)
{
this.action = action;
}

public String getResolvedQuery ()
{
return resolvedQuery;
}

public void setResolvedQuery (String resolvedQuery)
{
this.resolvedQuery = resolvedQuery;
}

public String[] getContexts ()
{
return contexts;
}

public void setContexts (String[] contexts)
{
this.contexts = contexts;
}

public Parameters getParameters ()
{
return parameters;
}

public void setParameters (Parameters parameters)
{
this.parameters = parameters;
}

public Metadata getMetadata ()
{
return metadata;
}

public void setMetadata (Metadata metadata)
{
this.metadata = metadata;
}

@Override
public String toString()
{
return "ClassPojo [actionIncomplete = "+actionIncomplete+", source = "+source+", fulfillment = "+fulfillment+", score = "+score+", action = "+action+", resolvedQuery = "+resolvedQuery+", contexts = "+contexts+", parameters = "+parameters+", metadata = "+metadata+"]";
}
}