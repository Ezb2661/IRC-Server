package me.ezb2661.ircserver.command.commands;

import me.ezb2661.ircserver.Logger;
import me.ezb2661.ircserver.command.Command;
import me.ezb2661.ircserver.command.CommandManager;

public class Help extends Command
{
    public Help( )
    {
        super( "help", "Displays this menu", "help" );
    }

    @Override
    public void execute( String[] arguments )
    {
        for( Command command : CommandManager.instance.commands )
        {
            Logger.logMessage("[+] " + command.name + " - " + command.description);
            Logger.logMessage( "    Usage: " + command.syntax );
        }
    }
}
