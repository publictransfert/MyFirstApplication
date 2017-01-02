package com.app.first.citysearch.model.beans;

public class ResultBean {

    private CityBean[] results;
    private int nbr;
    private ErrorBean errors;

    public ResultBean() {

    }

    public CityBean[] getResults() {
        return results;
    }

    public void setResults(CityBean[] results) {
        this.results = results;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public ErrorBean getErrors() {
        return errors;
    }

    public void setErrors(ErrorBean errors) {
        this.errors = errors;
    }
}
