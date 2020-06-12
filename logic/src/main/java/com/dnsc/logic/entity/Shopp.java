package com.dnsc.logic.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shopp")
public class Shopp {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "spname")
    private String spName;
    @Column(name = "sptitle")
    private String spTitle;
    @Column(name = "sptext")
    private String spText;
    @Column(name = "images")
    private String images;
    @Column(name = "spprice")
    private double spPrice;
    @Column(name = "createdate")
    private Date createDate;


    public Shopp() {
        super();
    }

    public Shopp( int id, String spName, String spTitle, String spText, String images, double spPrice) {
        super();
        this.id = id;
        this.spName = spName;
        this.spTitle = spTitle;
        this.spText = spText;
        this.images = images;
        this.spPrice = spPrice;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getSpName(){
        return spName;
    }
    public void setSpName(String spName){
        this.spName = spName;
    }
    public String getSpTitle(){
        return spTitle;
    }
    public void setSpTitle(String spTitle){
        this.spTitle = spTitle;
    }
    public String getSpText(){
        return spText;
    }
    public void setSpText(String spText){
        this.spText = spText;
    }
    public String getImages(){
        return images;
    }
    public void setImages(String images){
        this.images = images;
    }
    public double getSpPrice(){
        return spPrice;
    }
    public void setSpPrice(double spPrice){
        this.spPrice = spPrice;
    }
    public Date getCreateDate(){
        return createDate;
    }
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }
}
