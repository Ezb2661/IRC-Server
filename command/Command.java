package me.ezb2661.ircserver.command;

public abstract class Command
{
    public final String name;
    public final String description;
    public final String syntax;
    public abstract void execute( String[] arguments );

    public Command( String name, String description, String syntax )
    {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
    }

}
