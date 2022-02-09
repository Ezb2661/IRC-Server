package me.ezb2661.ircserver;

import me.ezb2661.ircserver.gui.ChatFrame;

public class Logger
{
    public static void logMessage( String msg )
    {
        ChatFrame.instance.logMessage( msg );
    }

    public static void clearLog( )
    {
        ChatFrame.instance.clearLog( );
    }

    public static void logError( Exception ex )
    {
        ex.printStackTrace( );
    }
}
