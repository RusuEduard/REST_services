package atl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Proba implements Serializable, Comparable<Proba> {

    private Long id;
    private String descriere;
    private Integer varstaMinima;
    private Integer varstaMaxima;

    public Proba(){

    }

    public Proba(String descriere, Integer varstaMinima, Integer varstaMaxima) {
        this.descriere = descriere;
        this.varstaMinima = varstaMinima;
        this.varstaMaxima = varstaMaxima;
    }

    public Proba(String descriere, Integer varstaMinima, Integer varstaMaxima, Long id) {
        this.descriere = descriere;
        this.varstaMinima = varstaMinima;
        this.varstaMaxima = varstaMaxima;
        this.setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Integer getVarstaMinima() {
        return varstaMinima;
    }

    public void setVarstaMinima(Integer varstaMinima) {
        this.varstaMinima = varstaMinima;
    }

    public Integer getVarstaMaxima() {
        return varstaMaxima;
    }

    public void setVarstaMaxima(Integer varstaMaxima) {
        this.varstaMaxima = varstaMaxima;
    }

    @Override
    public String toString() {
        return "Proba{" +
                "descriere='" + descriere + '\'' +
                ", varstaMinima=" + varstaMinima +
                ", varstaMaxima=" + varstaMaxima +
                '}';
    }

    @Override
    public int compareTo(Proba o) {
        return o.descriere.compareTo(this.descriere);
    }
}
