package com.idehgostar.makhsan.domain.init;

import javax.persistence.*;

@Entity
@Table(name = "filler_version")
public class FillerVersion {

    private int ver;
    private Long id;

    public FillerVersion() {
    }

    public FillerVersion(int ver) {
        this.ver = ver;
    }

    /**
     * get version
     *
     * @return verison
     */
    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
