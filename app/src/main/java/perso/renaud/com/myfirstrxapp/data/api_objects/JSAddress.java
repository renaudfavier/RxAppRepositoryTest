package perso.renaud.com.myfirstrxapp.data.api_objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renaud on 14/07/17.
 */

public class JSAddress {

    @SerializedName("street")
    public String street;

    @SerializedName("suite")
    public String suite;

    @SerializedName("city")
    public String city;

    @SerializedName("zipcode")
    public String zipcode;

    @SerializedName("geo")
    public JSGeo geo;

}
