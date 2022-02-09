package me.ezb2661.ircserver.gui;

import me.ezb2661.ircserver.ClientConnection;
import me.ezb2661.ircserver.Server;
import me.ezb2661.ircserver.command.CommandManager;
import me.ezb2661.ircserver.gui.elements.BaseFrame;
import me.ezb2661.ircserver.gui.elements.TitledList;
import me.ezb2661.ircserver.gui.elements.TitledTextArea;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatFrame extends BaseFrame
{
    public static ChatFrame instance;

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 800;

    private TitledTextArea logTextArea;
    private TitledList<ClientConnection> connectedClientsList;

    public ChatFrame( )
    {
        super( "IRC Server v" + Server.SERVER_VERSION, FRAME_WIDTH, FRAME_HEIGHT );
        this.setResizable( false );
    }

    public void logMessage( String msg )
    {
        this.logTextArea.setText( this.logTextArea.getText( ) + msg + "\n" );
    }

    public void clearLog( ) { this.logTextArea.setText( "" ); }

    public void addClientConnection( ClientConnection connection )
    {
        this.connectedClientsList.addItem( connection );
    }

    public void removeClientConnection( ClientConnection connection )
    {
        this.connectedClientsList.removeItem( connection );
    }

    @Override
    public void initializeComponents( )
    {
        JPanel contentPanel = new JPanel( );
        contentPanel.setBounds( 0, 0, FRAME_WIDTH, FRAME_HEIGHT );
        contentPanel.setBackground( Colors.BACKGROUND );
        contentPanel.setLayout( null );

        logTextArea = new TitledTextArea( "Server Log", 0, 5, FRAME_WIDTH - 200, FRAME_HEIGHT - 80, Colors.FOREGROUND );
        logTextArea.setBackground( Colors.BACKGROUND );
        logTextArea.setEditable( false );
        logTextArea.setForeground( Colors.FOREGROUND );
        contentPanel.add( logTextArea );

        JTextField commandField = new JTextField( );
        commandField.setBackground( Colors.BACKGROUND );
        commandField.setForeground( Colors.FOREGROUND );
        commandField.setBounds( 5, FRAME_HEIGHT - 75, FRAME_WIDTH - 25, 30 );
        commandField.addKeyListener( new KeyListener( )
            {
                @Override
                public void keyTyped( KeyEvent e )
                {
                }

                @Override
                public void keyPressed( KeyEvent e )
                {
                    if( e.getKeyCode( ) == 10 )
                    {
                        CommandManager.instance.processCommand( commandField.getText( ) );
                        commandField.setText( "" );
                    }
                }

                @Override
                public void keyReleased( KeyEvent e )
                {
                }
            }
        );
        contentPanel.add( commandField );

        connectedClientsList = new TitledList<>( "Connected Clients", logTextArea.getWidth( ) + 10, 0, 174, FRAME_HEIGHT - 75, Colors.FOREGROUND );
        connectedClientsList.setBackground( Colors.BACKGROUND );
        connectedClientsList.setForeground( Colors.FOREGROUND );
        contentPanel.add( connectedClientsList );

        this.add( contentPanel );
    }
}
