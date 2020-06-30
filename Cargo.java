package com.example.drn20;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class Cargo {


    private String cargoNo;
    private int cargoPic;
    private String cargoCo;
    private String cargoDes;
    private boolean isArchived;
    private String cargoMemo;

    public Cargo(){

    }


    public Cargo(int cargoPic ,String cargoNo , String cargoCo , String cargoDes , boolean isArchived , String cargoMemo){

        this.cargoPic = cargoPic;
        this.cargoNo = cargoNo;
        this.cargoCo = cargoCo;
        this.cargoDes = cargoDes;
        this.isArchived = isArchived;
        this.cargoMemo = cargoMemo;
    }

    //Getters
    public String getCargoCo() {
        return cargoCo;
    }

    public String getCargoNo() {
        return cargoNo;
    }

    public String getCargoDes() {
        return cargoDes;
    }

    public int getCargoPic() {
        return cargoPic;
    }

    public boolean isArchived() { return isArchived; }

    public String getCargoMemo() { return cargoMemo; }

    //setters
    public void setCargoNo(String cargoNo) {
        this.cargoNo = cargoNo;
    }

    public void setCargoCo(String cargoCo) {
        this.cargoCo = cargoCo;
    }

    public void setCargoDes(String cargoDes) {
        this.cargoDes = cargoDes;
    }

    public void setCargoPic(int cargoPic) {
        this.cargoPic = cargoPic;
    }

    public void setArchived(boolean archived) { isArchived = archived; }

    public void setCargoMemo(String cargoMemo) { this.cargoMemo = cargoMemo; }
}
