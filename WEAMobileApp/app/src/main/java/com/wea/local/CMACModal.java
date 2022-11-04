package com.wea.local;

public class CMACModal {
    private String cmacMessageNo;
    private String capID;
    private String cmacSender;
    private String cmacDateTime;
    private String messageType;

    public CMACModal(String cmacMessageNo, String capID, String cmacSender, String cmacDatetime, String messageType) {
        this.cmacMessageNo = cmacMessageNo;
        this.capID = capID;
        this.cmacSender = cmacSender;
        this.cmacDateTime = cmacDatetime;
        this.messageType = messageType;
    }
}
