package perso.renaud.com.myfirstrxapp.data.api_objects;

import com.google.gson.annotations.SerializedName;
import perso.renaud.com.myfirstrxapp.data.repository.base_class.PrimaryKeyObject;

/**
 * Created by renaud on 14/03/17.
 */

public class JSPost implements PrimaryKeyObject {

    @SerializedName("userId") public int userId;

    @SerializedName("id") public int id;

    @SerializedName("title") public String title;

    @SerializedName("body") public String body;

    public String toLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("id : ").append('\u0009').append('\u0009').append(id).append('\n');
        sb.append("userName : ").append('\u0009').append(userId).append('\n');
        sb.append("title : ").append('\u0009').append(title).append('\n');
        sb.append("body : ").append('\n').append(body);
        return sb.toString();
    }

    @Override public int getPrimaryKey() {
        return id;
    }
}
