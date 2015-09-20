package gasengine.scene;

import gasengine.messages.MessageReceiver;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class Component extends MessageReceiver
{
    public static class Message
    {
        public static final String DESTROYED = "OnDestroyed";
    }


    private Entity mEntity;
    private boolean mValid;

    public Component(Entity ent)
    {
        super(); // process message annotations

        mEntity = ent;
        mValid = true;
    }

    public final void destroy()
    {
        if (!mValid)
            return;

        handleMessage(Message.DESTROYED);

        mEntity.removeComponent(this);

        mValid = false;
    }

    public final Entity getEntity()
    {
        return mEntity;
    }

    public final boolean isValid() // whether this component is still attached to an entity
    {
        return mValid;
    }


    public void sendMessage(String name, Object data)
    {
        mEntity.sendMessage(name, data);
    }

    public void sendMessage(String name)
    {
        sendMessage(name, null);
    }
}
