package me.ezb2661.ircserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientConnection
{
    private static int nextClientId = 0;

    private final Socket socket;
    private final OutputStream outStream;
    private final InputStream inStream;
    public final int clientId;

    private String clientName;

    public ClientConnection( Socket socket ) throws Exception
    {
        this.socket = socket;
        this.outStream = socket.getOutputStream( );
        this.inStream = socket.getInputStream( );
        this.clientId = nextClientId++;
    }

    public void writeString( String message ) throws IOException
    {
        this.outStream.write( message.getBytes( StandardCharsets.UTF_8 ) );
    }

    public String readString( ) throws IOException
    {
        StringBuilder result = new StringBuilder( );
        byte[] incomingData = new byte[1024];
        int readBytes = this.inStream.read( incomingData );
        for( int i = 0; i < readBytes; i++ )
        {
            result.append( incomingData[i] );
        }
        return result.toString( );
    }

    public String getClientName( )
    {
        return this.clientName;
    }

    public Socket getSocket( )
    {
        return this.socket;
    }

    public void setClientName( String name )
    {
        this.clientName = name;
    }

    @Override
    public String toString( )
    {
        return "(" + this.clientId + ") " + this.clientName;
    }

}