package me.ezb2661.ircserver.command;

import me.ezb2661.ircserver.Logger;
import me.ezb2661.ircserver.command.commands.*;

import java.util.ArrayList;

public class CommandManager
{
    public static CommandManager instance;
    public ArrayList<Command> commands = new ArrayList<>( );

    public CommandManager( )
    {
        commands.add( new Help( ) );
        commands.add( new Say( ) );
        commands.add( new Clear( ) );
        commands.add( new Kick( ) );
    }

    public void processCommand( String command )
    {
        String[] arguments = command.split( " " );
        for( Command c : this.commands )
        {
            if( c.name.equalsIgnoreCase( arguments[0] ) )
            {
                c.execute( arguments );
                return;
            }
        }

        Logger.logMessage("[!] Error: command \"" + arguments[0] + "\" not found!");
    }
}
