package perso.renaud.com.myfirstrxapp.data.api_objects;

import perso.renaud.com.myfirstrxapp.data.repository.base_class.PrimaryKeyObject;

/**
 * Created by renaud on 14/07/17.
 */

public class JSUser implements PrimaryKeyObject {

    int id;

    @Override
    public int getPrimaryKey() {
        return id;
    }
}
