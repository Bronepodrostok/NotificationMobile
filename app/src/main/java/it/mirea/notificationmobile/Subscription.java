package it.mirea.notificationmobile;

public class Subscription {
    private int ID;
    private String name;
    private String description;
    private Integer month;
    private Integer day;


    public Subscription(int id, String name, String description, Integer day, Integer month) {
        this.ID = id;
        this.name = name;
        this.description = description;
        this.day = day;
        this.month = month;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMonth() {
        return month;
    }

    public void setPrice(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
