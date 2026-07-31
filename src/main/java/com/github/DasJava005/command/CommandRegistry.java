package com.github.DasJava005.command;

import com.github.DasJava005.command.annotations.Argument;
import com.github.DasJava005.command.annotations.Command;
import com.github.DasJava005.command.annotations.CommandGroup;
import com.github.DasJava005.command.executor.ApiCommandExecutor;
import com.github.DasJava005.command.executor.DefaultCommandExecutor;
import com.github.DasJava005.command.executor.feedback.CommandFeedbackProvider;
import com.github.DasJava005.command.tab.ApiTabCompleter;
import com.github.DasJava005.parser.Parser;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class CommandRegistry {

    private final JavaPlugin plugin;

    private final ApiCommandExecutor executor;
    private final ApiTabCompleter completer;

    private final Map<String, List<ApiCommand>> commandMap = new HashMap<>();

    public CommandRegistry(JavaPlugin plugin, Parser parser, CommandFeedbackProvider feedbackProvider, ApiTabCompleter completer) {
        this.plugin = plugin;
        this.executor = new DefaultCommandExecutor(parser, feedbackProvider);
        this.completer = completer;
    }

    public void registerCommands(Object obj) throws IllegalArgumentException {
       if(!obj.getClass().isAnnotationPresent(CommandGroup.class)){
           System.err.println("Could not register commands in class: " + obj.getClass().getName() + " ->Class must be annotated with @CommandGroup");
           return;
       }
       
       final String commandName = obj.getClass().getAnnotation(CommandGroup.class).value();

       methods: for(Method method : obj.getClass().getDeclaredMethods()) {
           if(!method.isAnnotationPresent(Command.class)) continue;

           final String syntax = method.getAnnotation(Command.class).value();
           final String[] arguments =  syntax.isBlank() ? new String[0] : syntax.split(" ");
           final Class<?>[] parameterType = new Class<?>[arguments.length];

           Parameter[] parameters = method.getParameters();

           if(parameters.length == 0){
               System.err.println("Could not register command. REASON: Method body is empty.");
               continue;
           }

           final SenderType senderType;
           final Optional<SenderType> optionalSenderType = SenderType.getFromClass(parameters[0].getType());
           if(optionalSenderType.isEmpty()){
               System.err.println("Could not register command. REASON: First Parameter must extend CommandSender.");
               continue;
           }
           senderType = optionalSenderType.get();

           final Map<String, Parameter> annotatedParameters = getArgumentParameters(parameters);

           for(int i =0; i < arguments.length; i++){
               if(!(arguments[i].startsWith("<") && arguments[i].endsWith(">"))) continue;
               final String paramName = arguments[i].substring(1, arguments[i].length() - 1);
               final Parameter p =  annotatedParameters.get(paramName);

               if(p == null) { // parameter does not exist in method body
                   System.err.println("Could not register command: [" + commandName + " " + syntax + "]" + "\n Parameter '" + paramName + "' does not exist in method body. Create parameter and/or annotate it with @Argument(\"" + paramName + "\")");
                   continue methods;
               }else{
                   parameterType[i] = p.getType();
               }
           }

           //success - register the commands
           final String permission = method.getAnnotation(Command.class).permission();
           final String description = obj.getClass().getAnnotation(CommandGroup.class).description();
           final String[] aliases = obj.getClass().getAnnotation(CommandGroup.class).aliases();
           if (!this.commandMap.containsKey(commandName)) {
               this.registerBukkitCommand(commandName, description, aliases);
           }
           registerApiCommand(commandName, obj, method, senderType, permission, arguments, parameterType, description, aliases);

           System.out.println("Registered new command: " + commandName + " " + syntax);
       }

    }

    private Map<String, Parameter> getArgumentParameters(Parameter[] parameters) {
        HashMap<String, Parameter> map = new HashMap<>();
        for (Parameter parameter : parameters) {
            Argument annotation = parameter.getAnnotation(Argument.class);
            if(annotation != null) {
                map.put(annotation.value(), parameter);
            }
        }
        return map;
    }

    private void registerApiCommand(String commandName,
                                    Object instance,
                                    Method method,
                                    SenderType senderType,
                                    String permission,
                                    String[] arguments,
                                    Class<?>[] parameterTypes,
                                    String description,
                                    String[] aliases) {

        method.setAccessible(true);
        ApiCommand apiCommand = new ApiCommand(instance, method, senderType, permission, arguments, parameterTypes, description, aliases);
        this.commandMap.computeIfAbsent(commandName, str -> new ArrayList<>()).add(apiCommand);

        for(String alias : aliases){ // register aliases of the command
            this.commandMap.computeIfAbsent(alias, str -> new ArrayList<>()).add(apiCommand);
        }
    }

    /**
     * Register a command in the Bukkit API.
     * @param name
     * @param description
     */
    private void registerBukkitCommand(String name, String description, String[] aliases) {
        try {
            final CommandMap commandMap =  getBukkitCommandMap();
            final org.bukkit.command.Command bukkitCommand = createBukkitCommand(name, description, aliases);
            commandMap.register(plugin.getName(), bukkitCommand);
        }catch (ReflectiveOperationException e) {
            System.err.println("Could not register command in Bukkit. REASON: " + e.getMessage());
        }
    }

    private CommandMap getBukkitCommandMap() throws ReflectiveOperationException {
        Class<?> craftServerClass = Bukkit.getServer().getClass();
        Field bukkitCommandMap = craftServerClass.getDeclaredField("commandMap");

        bukkitCommandMap.setAccessible(true);

        return (CommandMap) bukkitCommandMap.get(plugin.getServer());
    }

    private org.bukkit.command.Command createBukkitCommand(String namespace, String description, String[] aliases) {
        org.bukkit.command.Command command = new org.bukkit.command.Command(namespace) {

            @Override
            public boolean execute(@NonNull CommandSender commandSender, @NonNull String s, String @NonNull [] strings) {
                List<ApiCommand> commands = getCommands(s);
                if(commands.isEmpty()) return false;
                return executor.execute(commandSender, s, commands, strings);
            }

            @NonNull
            @Override
            public List<String> tabComplete(@NonNull CommandSender sender, @NonNull String alias, String @NonNull [] args) {
                if(completer == null) return List.of();
                final List<ApiCommand> commands = getCommands(alias);
                if(commands.isEmpty()) return List.of();
                return completer.complete(sender, alias, commands ,args);
            }
        };

        command.setDescription(description);
        command.setAliases(Arrays.asList(aliases));

        return command;
    }

    public List<ApiCommand> getCommands(String commandName) {
        return new ArrayList<>(this.commandMap.get(commandName));
    }

}
