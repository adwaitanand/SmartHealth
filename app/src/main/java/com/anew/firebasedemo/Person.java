package com.anew.firebasedemo;

/**
 * Created by Adwaitanand on 09-02-2018.
 */

public class Person {
    String pname;
    String pnumber;
    String page;
    String pblood;

    public Person()
    {

    }
    public  Person(String pname,String pnumber,String page,String pblood)
    {
       this.pname=pname;
        this.pnumber=pnumber;
        this.page=page;
        this.pblood=pblood;
    }



    public String getPname() {
        return pname;
    }
    public String getPnumber(){ return pnumber;}
    public String getPage(){ return page;}
    public String getPblood(){ return  pblood;}


}
