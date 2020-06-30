package com.example.drn20;

public class CargoCompanies {

    //private int logoImage;
    private String companyName;

    public CargoCompanies(){
    }

    public CargoCompanies( String companyName){

        //this.logoImage = logoImage;
        this.companyName = companyName;
    }

    //getters

   // public int getLogoImage() {
    //    return logoImage;
   // }

    public String getCompanyName() {
        return companyName;
    }


    //setters

    //public void setLogoImage(int logoImage) {
    //    this.logoImage = logoImage;
  //  }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}

