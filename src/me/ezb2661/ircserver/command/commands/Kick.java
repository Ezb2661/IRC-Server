package me.ezb2661.ircserver.command.commands;

import me.ezb2661.ircserver.Logger;
import me.ezb2661.ircserver.Server;
import me.ezb2661.ircserver.command.Command;

public class Kick extends Command
{
    public Kick( )
    {
        super( "kick", "Kicks a specified client.", "kick <client_id>" );
    }

    @Override
    public void execute( String[] arguments )
    {
        int idToKick = -1;
        try
        {
            idToKick = Integer.parseInt( arguments[1] );
        }
        catch( Exception ex )
        {
            Logger.logMessage( "[!] Please enter a valid client ID." );
            return;
        }

        Server.instance.kickUserId( idToKick );
    }
}
