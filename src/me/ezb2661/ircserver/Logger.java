package me.ezb2661.ircserver;

import me.ezb2661.ircserver.gui.LogFrame;

public class Logger
{
    public static void logMessage( String msg )
    {
        LogFrame.instance.logMessage( msg );
    }

    public static void clearLog( )
    {
        LogFrame.instance.clearLog( );
    }

    public static void logError( Exception ex )
    {
        ex.printStackTrace( );
    }
}
