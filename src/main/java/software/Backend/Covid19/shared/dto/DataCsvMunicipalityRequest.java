package software.Backend.Covid19.shared.dto;

import java.util.Date;

public class DataCsvMunicipalityRequest {
    private String municipality;
    private Date date;
    private Integer confirmedTotal;
    private Integer assetsTotal;
    private Integer recoveredTotal;
    private Integer DeceasedTotal;

    public DataCsvMunicipalityRequest() {
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getConfirmedTotal() {
        return confirmedTotal;
    }

    public void setConfirmedTotal(Integer confirmedTotal) {
        this.confirmedTotal = confirmedTotal;
    }

    public Integer getAssetsTotal() {
        return assetsTotal;
    }

    public void setAssetsTotal(Integer assetsTotal) {
        this.assetsTotal = assetsTotal;
    }

    public Integer getRecoveredTotal() {
        return recoveredTotal;
    }

    public void setRecoveredTotal(Integer recoveredTotal) {
        this.recoveredTotal = recoveredTotal;
    }

    public Integer getDeceasedTotal() {
        return DeceasedTotal;
    }

    public void setDeceasedTotal(Integer deceasedTotal) {
        DeceasedTotal = deceasedTotal;
    }

    @Override
    public String toString() {
        return "DataCsvMunicipalityRequest{" +
                "municipality='" + municipality + '\'' +
                ", date=" + date +
                ", confirmedTotal=" + confirmedTotal +
                ", assetsTotal=" + assetsTotal +
                ", recoveredTotal=" + recoveredTotal +
                ", DeceasedTotal=" + DeceasedTotal +
                '}';
    }
}
