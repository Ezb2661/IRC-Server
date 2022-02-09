package me.ezb2661.ircserver.command.commands;

import me.ezb2661.ircserver.Logger;
import me.ezb2661.ircserver.Server;
import me.ezb2661.ircserver.command.Command;

public class Say extends Command
{
    public Say( )
    {
        super( "say", "Send a message to all connected clients.", "say <text>" );
    }

    @Override
    public void execute( String[] arguments )
    {
        StringBuilder messageBuilder = new StringBuilder( );
        for( String s : arguments )
        {
            if( s.equalsIgnoreCase( "say" ) ) { continue; }
            messageBuilder.append( s ).append( " " );
        }
        Logger.logMessage( "[~] admin: " + messageBuilder );
        Server.instance.sendMessageToClients( "admin: " + messageBuilder );
    }
}
