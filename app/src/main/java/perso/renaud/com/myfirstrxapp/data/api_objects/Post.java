package perso.renaud.com.myfirstrxapp.data.api_objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renaud on 14/03/17.
 */

public class Post {

    @SerializedName("userId")
    public long userId;

    @SerializedName("id")
    public long id;

    @SerializedName("title")
    public String title;

    @SerializedName("body")
    public String body;

}
