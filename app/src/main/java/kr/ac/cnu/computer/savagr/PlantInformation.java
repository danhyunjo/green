package kr.ac.cnu.computer.savagr;

public class PlantInformation {
    public String plants_name;
    public String growth;
    public String pest_control;
    public String seasonResult;
    public String lightResult;
    public String soil_waterResult;
    public String locationResult;

    public PlantInformation() {

    }

    public PlantInformation(String plants_name, String growth, String pest_control, String seasonResult, String lightResult, String locationResult, String soil_waterResult) {
        this.plants_name = plants_name;
        this.growth = growth;
        this.pest_control = pest_control;
        this.seasonResult = seasonResult;
        this.locationResult = locationResult;
        this.soil_waterResult = soil_waterResult;
        this.lightResult = lightResult;
    }
}