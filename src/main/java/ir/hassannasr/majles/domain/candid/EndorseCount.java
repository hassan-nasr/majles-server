package ir.hassannasr.majles.domain.candid;

import javax.persistence.Embeddable;

/**
 * Created by hassan on 20/12/2015.
 */
@Embeddable
public class EndorseCount {
    public Long eghtesad = 0L;
    public Long farhang = 0L;
    public Long siasat = 0L;
    public Long peygiriMotalebatMardom = 0L;
    public Long ertebatBaMardom = 0L;
    public Long din = 0L;
    public Long sadehZisti = 0L;
    public Long biganehSetizi = 0L;
    public Long sedaghatDarGoftar = 0L;
    public Long modiriyat = 0L;
    public Long basirat = 0L;
    public Long shenakhtNiazHa = 0L;

    public Long getBasirat() {
        ;
        return basirat;
    }

    public void setBasirat(Long basirat) {
        this.basirat = basirat;
    }

    public Long getBiganehSetizi() {
        return biganehSetizi;
    }

    public void setBiganehSetizi(Long biganehSetizi) {
        this.biganehSetizi = biganehSetizi;
    }

    public Long getDin() {
        return din;
    }

    public void setDin(Long din) {
        this.din = din;
    }

    public Long getEghtesad() {
        return eghtesad;
    }

    public void setEghtesad(Long eghtesad) {
        this.eghtesad = eghtesad;
    }

    public Long getErtebatBaMardom() {
        return ertebatBaMardom;
    }

    public void setErtebatBaMardom(Long ertebatBaMardom) {
        this.ertebatBaMardom = ertebatBaMardom;
    }

    public Long getFarhang() {
        return farhang;
    }

    public void setFarhang(Long farhang) {
        this.farhang = farhang;
    }

    public Long getModiriyat() {
        return modiriyat;
    }

    public void setModiriyat(Long modiriyat) {
        this.modiriyat = modiriyat;
    }

    public Long getPeygiriMotalebatMardom() {
        return peygiriMotalebatMardom;
    }

    public void setPeygiriMotalebatMardom(Long peygiriMotalebatMardom) {
        this.peygiriMotalebatMardom = peygiriMotalebatMardom;
    }

    public Long getSadehZisti() {
        return sadehZisti;
    }

    public void setSadehZisti(Long sadehZisti) {
        this.sadehZisti = sadehZisti;
    }

    public Long getSedaghatDarGoftar() {
        return sedaghatDarGoftar;
    }

    public void setSedaghatDarGoftar(Long sedaghatDarGoftar) {
        this.sedaghatDarGoftar = sedaghatDarGoftar;
    }

    public Long getShenakhtNiazHa() {
        return shenakhtNiazHa;
    }

    public void setShenakhtNiazHa(Long shenakhtNiazHa) {
        this.shenakhtNiazHa = shenakhtNiazHa;
    }

    public Long getSiasat() {
        return siasat;
    }

    public void setSiasat(Long siasat) {
        this.siasat = siasat;
    }
}
