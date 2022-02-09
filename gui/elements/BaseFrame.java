package me.ezb2661.ircserver.gui.elements;

import me.ezb2661.ircserver.gui.Colors;

import javax.swing.*;

public abstract class BaseFrame extends JFrame
{
    public BaseFrame( String title, int width, int height )
    {
        super( );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        this.setLayout( null );
        this.setSize( width, height );
        this.setTitle( title );

        this.initializeComponents( );
        this.setVisible( true );
    }

   abstract protected void initializeComponents( );
}
