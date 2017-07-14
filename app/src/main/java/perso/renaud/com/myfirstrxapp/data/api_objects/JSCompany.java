package perso.renaud.com.myfirstrxapp.data.api_objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renaud on 14/07/17.
 */

public class JSCompany {

    @SerializedName("name")
    public String name;

    @SerializedName("catchPhrase")
    public String catchPhrase;

    @SerializedName("bs")
    public String bs;
}
