import commands.BalanceCommandTest;import commands.MessageCommandTest;import de.dasjava.commandAPI.command.CommandRegistry;
import de.dasjava.commandAPI.parser.Parser;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Parser parser = Parser.createDefaultBukkitParser();
        CommandRegistry registry = new CommandRegistry(this, parser);

        registry.registerCommands(new BalanceCommandTest());
        registry.registerCommands(new MessageCommandTest());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
