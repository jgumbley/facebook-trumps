package com.facebooktrumps.client.widgets;

import com.facebooktrumps.client.Controller;
import com.facebooktrumps.client.EventFriendly;
import com.facebooktrumps.client.Initializable;

public interface View extends Initializable, EventFriendly
{
    void setController(Controller controller);
    Controller getController();

    void putChildInMap(String uniqueID, View view);

    String getUniqueID();

    View getChild(String id);


    void dispose();
    boolean isDisposed();
}
