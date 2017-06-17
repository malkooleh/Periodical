package ua.nure.malko.Periodical.web.commands;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.
 */
public class CommandContainer {

    //private static final Logger LOG = Logger.getLogger( CommandContainer.class );

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        // common commands
        commands.put( "login", new LoginCommand() );
        commands.put( "logout", new LogoutCommand() );
        commands.put( "viewSettings", new ViewSettingsCommand() );
        commands.put( "noCommand", new NoCommand() );
        commands.put( "makeOrder", new MakeOrderCommand() );

        // client commands
        commands.put( "listPeriodicals", new PeriodicalsCommand() );

        // admin commands
        commands.put( "listSubscriptions", new ListSubscriptionsCommand() );

        //LOG.debug( "Command container was successfully initialized" );
        System.err.println( "Command container was successfully initialized" );
        //LOG.trace( "Number of commands --> " + commands.size() );
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey( commandName )) {
            //LOG.trace( "Command not found, name --> " + commandName );
            return commands.get( "noCommand" );
        }

        return commands.get( commandName );
    }

}