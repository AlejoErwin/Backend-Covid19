package software.Backend.Covid19.shared.model;

import java.util.Date;

public class CovidData {
    private Integer idCovidData;
    private Integer idPageUrl;

    private Integer confirmedCases;
    private Integer confirmedCumulative; //esto cambio nombre cumulativeCases

    private Integer deathCases;
    private Integer deathCumulative; //esto agrege

    private Integer recuperated;
    private Integer recuperatedCumulative; //esto agrege

    private Integer vaccinated;
    private Date date;
    private Integer status;
    private Transaction transaction;



    public CovidData(){
        transaction = new Transaction();
    }

    public CovidData(Integer idCovidData, Integer idPageUrl, Integer confirmedCases, Integer confirmedCumulative, Integer deathCases, Integer deathCumulative, Integer recuperated, Integer recuperatedCumulative, Integer vaccinated, Date date, Integer status, Transaction transaction) {
        this.idCovidData = idCovidData;
        this.idPageUrl = idPageUrl;
        this.confirmedCases = confirmedCases;
        this.confirmedCumulative = confirmedCumulative;
        this.deathCases = deathCases;
        this.deathCumulative = deathCumulative;
        this.recuperated = recuperated;
        this.recuperatedCumulative = recuperatedCumulative;
        this.vaccinated = vaccinated;
        this.date = date;
        this.status = status;
        this.transaction = transaction;
    }

    public Integer getDeathCumulative() {
        return deathCumulative;
    }

    public void setDeathCumulative(Integer deathCumulative) {
        this.deathCumulative = deathCumulative;
    }

    public Integer getRecuperatedCumulative() {
        return recuperatedCumulative;
    }

    public void setRecuperatedCumulative(Integer recuperatedCumulative) {
        this.recuperatedCumulative = recuperatedCumulative;
    }

    public Integer getIdCovidData() {
        return idCovidData;
    }

    public void setIdCovidData(Integer idCovidData) {
        this.idCovidData = idCovidData;
    }

    public Integer getIdPageUrl() {
        return idPageUrl;
    }

    public void setIdPageUrl(Integer idPageUrl) {
        this.idPageUrl = idPageUrl;
    }

    public Integer getDeathCases() {
        return deathCases;
    }

    public void setDeathCases(Integer deathCases) {
        this.deathCases = deathCases;
    }

    public Integer getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(Integer confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public Integer getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Integer vaccinated) {
        this.vaccinated = vaccinated;
    }

    public Integer getConfirmedCumulative() {
        return confirmedCumulative;
    }

    public void setConfirmedCumulative(Integer confirmedCumulative) {
        this.confirmedCumulative = confirmedCumulative;
    }

    public Integer getRecuperated() {
        return recuperated;
    }

    public void setRecuperated(Integer recuperated) {
        this.recuperated = recuperated;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "CovidData{" +
                "idCovidData=" + idCovidData +
                ", idPageUrl=" + idPageUrl +
                ", confirmedCases=" + confirmedCases +
                ", confirmedCumulative=" + confirmedCumulative +
                ", deathCases=" + deathCases +
                ", deathCumulative=" + deathCumulative +
                ", recuperated=" + recuperated +
                ", recuperatedCumulative=" + recuperatedCumulative +
                ", vaccinated=" + vaccinated +
                ", date=" + date +
                ", status=" + status +
                ", transaction=" + transaction +
                '}';
    }
}
