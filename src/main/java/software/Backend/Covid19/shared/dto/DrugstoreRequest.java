package software.Backend.Covid19.shared.dto;

public class DrugstoreRequest {
    private String nameCity;
    private String nameDrugstore;
    private Double latitude;
    private Double longitude;

    public DrugstoreRequest() {
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getNameDrugstore() {
        return nameDrugstore;
    }

    public void setNameDrugstore(String nameDrugstore) {
        this.nameDrugstore = nameDrugstore;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "DrugstoreRequest{" +
                "nameCity='" + nameCity + '\'' +
                ", nameDrugstore='" + nameDrugstore + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
