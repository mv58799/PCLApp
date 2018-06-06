
package com.citibank.newcpb.bean;


public class ResultTableBean {

    private String resultCode;
    private String resultDescription;

    public ResultTableBean() {
        resultCode = new String();
        resultDescription = new String();
    }

    public ResultTableBean(String resultCode, String resultDescription) {
        super();
        this.resultCode = resultCode;
        this.resultDescription = resultDescription;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    @Override
    public String toString() {
        return "ResultTableBean [resultCode=" + resultCode + ", resultDescription=" + resultDescription + "]";
    }

}
