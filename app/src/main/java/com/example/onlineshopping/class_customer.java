package com.example.onlineshopping;

class class_customer {
    public int CustID;
    public int Gender;
    public String CutName;
    public String Password;
    public String Birthdate;
    public String Job;

    public class_customer() {
    }

    public class_customer(int custid, int gender, String custname, String pass, String birth, String job) {
        this.Birthdate = birth;
        this.CustID = custid;
        this.CutName = custname;
        this.Gender = gender;
        this.Password = pass;
        this.Job = job;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int custID) {
        CustID = custID;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getCutName() {
        return CutName;
    }

    public void setCutName(String cutName) {
        CutName = cutName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(String birthdate) {
        Birthdate = birthdate;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }
}
