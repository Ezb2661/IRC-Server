package me.ezb2661.ircserver;

import me.ezb2661.ircserver.gui.GUIManager;
import me.ezb2661.ircserver.gui.ChatFrame;

public class ServerMain
{
    public static void main( String[] args )
    {
        GUIManager.setup( );
        ChatFrame.instance = new ChatFrame( );

        int hostPort = -1;
        for( int i = 0; i < args.length; i++ )
        {
            if( !args[i].startsWith( "-" ) ) { continue; }
            if( args[i].equalsIgnoreCase( "-p" ) )
            {
                try
                {
                    hostPort = Integer.parseInt( args[i + 1] );
                }
                catch( Exception ex )
                {
                    Logger.logMessage( "[!] Please enter a valid port." );
                    System.exit( 0 );
                }
            }

        }

        if( hostPort == -1 )
        {
            Logger.logMessage( "[!] Please specify a port." );
            System.exit( 0 );
        }

        Server.instance = new Server( hostPort );
        Server.instance.start( );
    }
}
