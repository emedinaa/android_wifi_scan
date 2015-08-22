package com.emedinaa.wifiscanner.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by emedinaa on 22/08/15.
 */
@DatabaseTable(tableName = "wifitb")
public class WifiEntity implements Serializable {

    @DatabaseField
    private String SSID;

    @DatabaseField
    private String BSSID;

    @DatabaseField
    private int level;

    @DatabaseField
    private int frequency;

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
