package com.tcrypto.dto.response.DaData;

import com.tcrypto.dto.response.DaData.Data;

public class Location {
    private String value;
    private String unrestricted_value;
    Data DataObject;


    // Getter Methods

    public String getValue() {
        return value;
    }

    public String getUnrestricted_value() {
        return unrestricted_value;
    }

    public Data getData() {
        return DataObject;
    }

    // Setter Methods

    public void setValue( String value ) {
        this.value = value;
    }

    public void setUnrestricted_value( String unrestricted_value ) {
        this.unrestricted_value = unrestricted_value;
    }

    public void setData( Data dataObject ) {
        this.DataObject = dataObject;
    }
}
