package com.example.final_p.classes;

public class WorkingHours {
    private String OpeningTime;
    private String ClosingTime;

    public WorkingHours(String closingTime,String openingTime) {
        this.ClosingTime = closingTime;
        this.OpeningTime = openingTime;

    }


    public String getOpeningTime() {
        return OpeningTime;
    }

    public void setOpeningTime(String openingTime) {
        OpeningTime = openingTime;
    }

    public String getClosingTime() {
        return ClosingTime;
    }

    public void setClosingTime(String closingTime) {
        ClosingTime = closingTime;
    }
}
