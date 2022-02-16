package me.ezb2661.ircserver.gui.elements;

import java.awt.*;

public class ListComponent<T> extends Component
{
    private T data;

    public ListComponent( String name, T data )
    {
        this.setName( name );
        this.data = data;
    }

    public T getData( )
    {
        return this.data;
    }
}
