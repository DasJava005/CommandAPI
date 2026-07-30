import de.dasjava.commandAPI.parser.ParseException;
import de.dasjava.commandAPI.parser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestParser {

    @Test
    public void parseIntegerTest() {
        Parser parser = Parser.createDefaultParser();

        String number = "5";

        int result = (int) parser.parse(Integer.class, number);

        Assertions.assertEquals(5, result);
    }

    @Test
    public void parseCharTest() {
        Parser parser = Parser.createDefaultParser();

        String character = "a";

        char result = (char) parser.parse(Character.class, character);

        Assertions.assertEquals('a', result);
    }

    @Test
    public void parseBoolean(){
        Parser parser = Parser.createDefaultParser();

        String bool = "false";

        boolean result = (boolean) parser.parse(Boolean.class, bool);

        Assertions.assertFalse(result);

        Assertions.assertThrows(ParseException.class, () ->{
            parser.parse(Boolean.class, "fls");
        });
    }

}
