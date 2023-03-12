/**
 * Takes in function and command
 * Identifies the function called and executes the appropriate function class
 *
 * @param command
 * @param toDo
 */

package seedu.badMaths;

public class Command {

    protected String command;
    protected String toDo;
    private Notes notes = new Notes(toDo);

    public Command(String command, String toDo) {
        this.command = command;
        this.toDo = toDo;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setToDo(String toDo){
        this.toDo = toDo;
    }

    public void executeCommand() {
        switch (command) {
        case "Bye":
            System.out.println("Goodbye!");
            break;
        case "Graph":
            TrigoGraph trigoGraph = new TrigoGraph(toDo);
            trigoGraph.startGraphAnalysis();
            break;
        case "Notes":
            notes.setToDo(toDo);
            notes.handleCache(command);
            break;
        case "List":
            notes.handleCache(command);
            break;
        case "Help":
            HelpManual.readHelpManual();
            break;
        default:
            break;
        }
    }
}
