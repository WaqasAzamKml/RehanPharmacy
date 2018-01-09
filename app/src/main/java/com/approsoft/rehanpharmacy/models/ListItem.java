package com.approsoft.rehanpharmacy.models;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Approsoft on 1/9/2018.
 */

public class ListItem {
    private String itemName;
    private String companyName;

    public ListItem(){
        this.itemName = "";
        this.companyName = "";
    }

    public ListItem(String itemName, String companyName) {
        this.itemName = itemName;
        this.companyName = companyName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getBackgroundColor(){
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return Color.rgb(r,g,b);
    }
}
