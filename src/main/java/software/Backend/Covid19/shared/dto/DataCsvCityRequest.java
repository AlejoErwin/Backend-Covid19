package software.Backend.Covid19.shared.dto;

import java.util.Date;

public class DataCsvCityRequest {
    private Date date;
    private Integer confirmed;
    private Integer confirmedCumulative;
    private Integer deaths;
    private Integer deathsCumulative;
    private Integer recovered;
    private Integer recoveredCumulative;
    private Integer vaccinated;

    public DataCsvCityRequest() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getConfirmedCumulative() {
        return confirmedCumulative;
    }

    public void setConfirmedCumulative(Integer confirmedCumulative) {
        this.confirmedCumulative = confirmedCumulative;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getDeathsCumulative() {
        return deathsCumulative;
    }

    public void setDeathsCumulative(Integer deathsCumulative) {
        this.deathsCumulative = deathsCumulative;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getRecoveredCumulative() {
        return recoveredCumulative;
    }

    public void setRecoveredCumulative(Integer recoveredCumulative) {
        this.recoveredCumulative = recoveredCumulative;
    }

    public Integer getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Integer vaccinated) {
        this.vaccinated = vaccinated;
    }

    @Override
    public String toString() {
        return "DataCsvCityRequest{" +
                "date=" + date +
                ", confirmed=" + confirmed +
                ", confirmedCumulative=" + confirmedCumulative +
                ", deaths=" + deaths +
                ", deathsCumulative=" + deathsCumulative +
                ", recovered=" + recovered +
                ", recoveredCumulative=" + recoveredCumulative +
                ", vaccinated=" + vaccinated +
                '}';
    }
}
