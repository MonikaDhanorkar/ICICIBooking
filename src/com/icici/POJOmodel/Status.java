package com.icici.POJOmodel;


public class Status
{
private String errorType;

private String errorDetails;

private String code;

public String getErrorType ()
{
return errorType;
}

public void setErrorType (String errorType)
{
this.errorType = errorType;
}

public String getErrorDetails ()
{
return errorDetails;
}

public void setErrorDetails (String errorDetails)
{
this.errorDetails = errorDetails;
}

public String getCode ()
{
return code;
}

public void setCode (String code)
{
this.code = code;
}

@Override
public String toString()
{
return "ClassPojo [errorType = "+errorType+", errorDetails = "+errorDetails+", code = "+code+"]";
}
}

