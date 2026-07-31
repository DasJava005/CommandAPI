import commands.BalanceCommandTest;
import commands.ItemCommandTest;
import commands.MessageCommandTest;
import de.dasjava.commandAPI.command.CommandRegistry;
import de.dasjava.commandAPI.command.CommandRegistryFactory;
import de.dasjava.commandAPI.command.executor.feedback.CommandFeedbackProvider;
import de.dasjava.commandAPI.command.executor.feedback.DefaultFeedbackProvider;
import de.dasjava.commandAPI.command.tab.ApiTabCompleter;
import de.dasjava.commandAPI.command.tab.DefaultTabCompleter;
import de.dasjava.commandAPI.parser.Parser;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        Parser parser = Parser.createDefaultBukkitParser();

        CommandFeedbackProvider feedbackProvider = new DefaultFeedbackProvider();

        ApiTabCompleter completer = new DefaultTabCompleter();

        final CommandRegistry registry = new CommandRegistry(this, parser, feedbackProvider, completer);
        registry.registerCommands(new BalanceCommandTest());
        registry.registerCommands(new MessageCommandTest());
        registry.registerCommands(new ItemCommandTest());

        /*
            The below code does the exact same thing as the one above
         */

        final CommandRegistry registry2 = CommandRegistryFactory.defaultRegistry(this);

        /*
            Or for more customization one can use the following
         */
        /*
        final CommandRegistry registry3 = CommandRegistryFactory.create(this)
                .parser(new MyCustomParser())
                .tabCompleter(new MyTabCompleter())
                .feedbackProvider(new MySpanishFeedbackProvider())
                .build();
        */
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
