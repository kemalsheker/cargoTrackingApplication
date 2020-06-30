package com.example.drn20;

public class VerifyLogin {

    //this class is response of Login_V1
    private String SessionID;
    private int ErrorCode;
    private String ErrorDefiniton;

    public VerifyLogin() {
    }

    public VerifyLogin(String SessionId, int ErrorCode,
                       String ErrorDefiniton ) {
        super();
        this.SessionID = SessionId;
        this.ErrorCode = ErrorCode;
        this.ErrorDefiniton = ErrorDefiniton;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String sessionID) {
        SessionID = sessionID;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorDefiniton() {
        return ErrorDefiniton;
    }

    public void setErrorDefiniton(String errorDefiniton) {
        ErrorDefiniton = errorDefiniton;
    }
}


