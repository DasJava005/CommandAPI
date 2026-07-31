package com.github.DasJava005.command;

import com.github.DasJava005.command.executor.feedback.CommandFeedbackProvider;
import com.github.DasJava005.command.executor.feedback.DefaultFeedbackProvider;
import com.github.DasJava005.command.tab.ApiTabCompleter;
import com.github.DasJava005.command.tab.DefaultTabCompleter;
import com.github.DasJava005.parser.Parser;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandRegistryFactory {

    public static CommandRegistry defaultRegistry(JavaPlugin plugin) {
        return CommandRegistryFactory.create(plugin)
                .tabCompleter(new DefaultTabCompleter())
                .build();
    }

    public static CommandRegistryFactory create(JavaPlugin plugin) {
        return new CommandRegistryFactory(plugin);
    }

    private final JavaPlugin plugin;
    private Parser parser;
    private CommandFeedbackProvider feedbackProvider;
    private ApiTabCompleter tabCompleter;

    private CommandRegistryFactory(JavaPlugin plugin) {
        this.plugin = plugin;
        parser = Parser.createDefaultBukkitParser();
        feedbackProvider = new DefaultFeedbackProvider();
        tabCompleter = null;
    }

    public CommandRegistryFactory parser(Parser parser) {
        this.parser = parser;
        return this;
    }

    public CommandRegistryFactory feedbackProvider(CommandFeedbackProvider feedbackProvider) {
        this.feedbackProvider = feedbackProvider;
        return this;
    }

    public CommandRegistryFactory tabCompleter(ApiTabCompleter tabCompleter) {
        this.tabCompleter = tabCompleter;
        return this;
    }

    public CommandRegistry build(){
        return new CommandRegistry(plugin, parser, feedbackProvider, tabCompleter);
    }

}
