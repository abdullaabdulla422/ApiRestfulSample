package com.app.apirestful;

public class SessionData {
    private static SessionData instance = null;

    private  int strId;
    private  String strname;
    private  String strcategory;
    private  String strcategoryId;
    private  String strdescription;
    private  String strempCode;
    private  String strcontact;
    private  String straddress;
    private  String strImage;

    public static SessionData getInstance() {
        if (instance == null) {
            instance = new SessionData();
        }
        return instance;
    }

    public int getStrId() {
        return strId;
    }

    public void setStrId(int strId) {
        this.strId = strId;
    }

    public String getStrname() {
        return strname;
    }

    public void setStrname(String strname) {
        this.strname = strname;
    }

    public String getStrcategory() {
        return strcategory;
    }

    public void setStrcategory(String strcategory) {
        this.strcategory = strcategory;
    }

    public String getStrcategoryId() {
        return strcategoryId;
    }

    public void setStrcategoryId(String strcategoryId) {
        this.strcategoryId = strcategoryId;
    }

    public String getStrdescription() {
        return strdescription;
    }

    public void setStrdescription(String strdescription) {
        this.strdescription = strdescription;
    }

    public String getStrempCode() {
        return strempCode;
    }

    public void setStrempCode(String strempCode) {
        this.strempCode = strempCode;
    }

    public String getStrcontact() {
        return strcontact;
    }

    public void setStrcontact(String strcontact) {
        this.strcontact = strcontact;
    }

    public String getStraddress() {
        return straddress;
    }

    public void setStraddress(String straddress) {
        this.straddress = straddress;
    }

    public String getStrImage() {
        return strImage;
    }

    public void setStrImage(String strImage) {
        this.strImage = strImage;
    }
}
