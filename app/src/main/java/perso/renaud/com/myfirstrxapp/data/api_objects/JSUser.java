package perso.renaud.com.myfirstrxapp.data.api_objects;

import com.google.gson.annotations.SerializedName;
import perso.renaud.com.myfirstrxapp.data.repository.base_class.PrimaryKeyObject;

/**
 * Created by renaud on 14/07/17.
 */

public class JSUser implements PrimaryKeyObject {

    @SerializedName("id") public int id;

    @SerializedName("name") public String name;

    @SerializedName("username") public String username;

    @SerializedName("email") public String email;

    @SerializedName("address") public JSAddress address;

    @SerializedName("phone") public String phone;

    @SerializedName("website") public String website;

    @SerializedName("company") public JSCompany company;

    @Override public int getPrimaryKey() {
        return id;
    }

    public String toLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("id : ").append('\u0009').append('\u0009').append(id).append('\n');
        sb.append("name : ").append('\u0009').append(name).append('\n');
        sb.append("username : ").append('\u0009').append(username).append('\n');
        return sb.toString();
    }
}
