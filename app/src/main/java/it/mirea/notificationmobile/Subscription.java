package it.mirea.notificationmobile;

public class Subscription {
    private int ID;
    private String name;
    private Integer day;

    public Subscription(int id,String name, Integer day) {
        this.ID = id;
        this.name = name;
        this.day = day;
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

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
