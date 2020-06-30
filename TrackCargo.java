package com.example.drn20;

public class TrackCargo {

    private String TrackingNumber;
    private String ProcessTimeStamp;
    private String OperationBranchName;
    private int StatusCode;
    private String ExceptionCode;
    private String ProcessDescription1;
    private String ProcessDescription2;
    private long RecordId;
    private int InformationLevel;
    private int ErrorCode;
    private String ErrorDefiniton;

    public TrackCargo() {
    }

    public TrackCargo(String TrackingNumber , String ProcessTimeStamp , String OperationBranchName , int StatusCode , String ExceptionCode,
                      String ProcessDescription1, String ProcessDescription2, long RecordId, int InformationLevel, int ErrorCode , String ErrorDefiniton ) {
        super();
        this.TrackingNumber = TrackingNumber;
        this.ProcessTimeStamp = ProcessTimeStamp;
        this.OperationBranchName = OperationBranchName;
        this.StatusCode = StatusCode;
        this.ExceptionCode = ExceptionCode;
        this.ProcessDescription1 = ProcessDescription1;
        this.ProcessDescription2 = ProcessDescription2;
        this.RecordId = RecordId;
        this.InformationLevel = InformationLevel;
        this.ErrorCode = ErrorCode;
        this.ErrorDefiniton = ErrorDefiniton;
    }



    public String getTrackingNumber() {
        return TrackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        TrackingNumber = trackingNumber;
    }

    public String getProcessTimeStamp() {
        return ProcessTimeStamp;
    }

    public void setProcessTimeStamp(String processTimeStamp) {
        ProcessTimeStamp = processTimeStamp;
    }

    public String getOperationBranchName() {
        return OperationBranchName;
    }

    public void setOperationBranchName(String operationBranchName) {
        OperationBranchName = operationBranchName;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getExceptionCode() {
        return ExceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        ExceptionCode = exceptionCode;
    }

    public String getProcessDescription1() {
        return ProcessDescription1;
    }

    public void setProcessDescription1(String processDescription1) {
        ProcessDescription1 = processDescription1;
    }

    public String getProcessDescription2() {
        return ProcessDescription2;
    }

    public void setProcessDescription2(String processDescription2) {
        ProcessDescription2 = processDescription2;
    }

    public long getRecordId() {
        return RecordId;
    }

    public void setRecordId(long recordId) {
        RecordId = recordId;
    }

    public int getInformationLevel() {
        return InformationLevel;
    }

    public void setInformationLevel(int informationLevel) {
        InformationLevel = informationLevel;
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

    ///////

    ////////


}
