package me.ezb2661.ircserver.command.commands;

import me.ezb2661.ircserver.Logger;
import me.ezb2661.ircserver.command.Command;

public class Clear extends Command
{
    public Clear( )
    {
        super( "clear", "Clears the log viewer.", "clear" );
    }

    @Override
    public void execute( String[] arguments )
    {
        Logger.clearLog( );
    }
}
