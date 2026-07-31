# CommandAPI

🚧 **Status:** Work in Progress  
The API is still under active development and features may change in future releases.

A flexible annotation-driven command framework for Bukkit.

The CommandAPI handles:
- Command structure through annotations
- Command registration
- Argument parsing and type conversion
- Tab completion
- Permission checks

## Example usage
### Creating an item command
We want a command that does something like this:
```
/item
/item get <material> <amount>
/item enchant <enchantment> <level>
```
We can achieve this using the annotations @CommandGroup, @Command, and @Argument.

```java
@CommandGroup(value = "item", aliases = {"i"})
public class ItemCommandTest {

    @Command("get <material> <amount>")
    public void itemCommand(Player player,
                              @Argument("material") Material mat,
                              @Argument("amount") int amount) {

        ItemStack item = new ItemStack(mat, amount);
        player.getInventory().addItem(item);
    }

    @Command(value = "enchant <enchantment> <level>", permission = "command.item.enchant")
    public void enchantCommand(Player player,
                              @Argument("enchantment") Enchantment enchantment,
                              @Argument("level") int level) {

        ItemStack item = player.getInventory().getItemInMainHand();
        item.addUnsafeEnchantment(enchantment, level);
    }
}
```

### Registering the item command
The simplest way is to use the CommandRegistryFactory with the provided default registry.
```java
 @Override
 public void onEnable() {
    final CommandRegistry registry = CommandRegistryFactory.defaultRegistry(this);

    registry.registerCommands(new ItemCommandTest());
}
```
## Maven Dependency
```maven
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
```
```maven
	<dependency>
	    <groupId>com.github.DasJava005</groupId>
	    <artifactId>CommandAPI</artifactId>
	    <version>v1.0.3</version>
	</dependency>
```
