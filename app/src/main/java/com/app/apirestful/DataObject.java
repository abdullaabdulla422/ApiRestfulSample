package com.app.apirestful;

public class DataObject {
int id;
String name;
String category;
String categoryid;
String address;
String description;
String contact;
String empcode;
String image;

    public DataObject(int id, String name, String category, String categoryid, String address, String description, String contact, String empcode, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.categoryid = categoryid;
        this.address = address;
        this.description = description;
        this.contact = contact;
        this.empcode = empcode;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
