package seedu.badmaths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void getCommandShouldReturnCommandWithoutDot() {
        String userInput = "Store. store this";
        Parser parserTest = new Parser(userInput);
        String commandTest = parserTest.getCommand();
        assertEquals(commandTest, "Store");
    }

    @Test
    void getToDoShouldReturnInvalidForSingleWordInput() {
        String userInput = "Help.";
        Parser parserTest = new Parser(userInput);
        String toDoTest = parserTest.getToDo();
        assertEquals(toDoTest, "Invalid todo");
    }
}
