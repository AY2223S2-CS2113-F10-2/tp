/**
 * Takes in function and command
 * Identifies the function called and executes the appropriate function class
 *
 * @param command
 * @param toDo
 */

package seedu.badmaths;
import seedu.badmaths.trigograph.TrigoGraph;
import seedu.badmaths.ui.Ui;
import seedu.badmaths.matrix.Calculator;
import seedu.badmaths.Store;

public class Command {
    private static final String filePath = "data/notes.txt";
    protected String command;
    protected String toDo;

    public Command(String command, String toDo) {
        this.command = command;
        this.toDo = toDo;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        try {
            this.command = command;
        } catch (IllegalArgumentException e) {
            Ui.printIncorrectFormatEntered();
        }
    }

    public void setToDo(String toDo){
        this.toDo = toDo;
    }

    public boolean isInvalidTodo(String todo) {
        return todo.equals("Invalid todo");
    }

    public boolean isInvalidIndex(int index, NotesList notes) {
        return (index < 0 || index >= notes.getSize());
    }

    public boolean isAnInt(String todo) {
        try {
            Integer.parseInt(todo);
        } catch (NumberFormatException numberException) {
            Ui.printInvalidNumberEntered();
            return false;
        }
        return true;
    }

    public void executeCommand(NotesList notes) {
        TrigoGraph trigoGraph = new TrigoGraph(toDo);
        Calculator calculator = new Calculator();
        Quadratic quadratic = new Quadratic(toDo);

        try {
            //@@author WilsonLee2000
            assert (command.equals("Bye") || command.equals("Graph") || command.equals("Store") ||
                    command.equals("List") || command.equals("Delete") || command.equals("Mark") ||
                    command.equals("Unmark") || command.equals("Low") || command.equals("Medium") ||
                    command.equals("High") || command.equals("FindInfo") || command.equals("FindPrior") ||
                    command.equals("FindMark") || command.equals("FindUnmark") || command.equals("Rank") ||
                    command.equals("Clear") || command.equals("Help") || command.equals("Matrix") ||
                    command.equals("Quadratic")) : "input has incorrect format required";
            //@@author Khooyourun
            switch (command) {
            case "Bye":
                if(!isInvalidTodo(toDo)) {
                    throw new IllegalTodoException();
                }
                System.out.println("Goodbye!");
                break;
            case "Graph":
                trigoGraph.startGraphAnalysis();
                break;
            //@@author WilsonLee2000
            case "Store":
                Store store = new Store(notes, toDo);
                store.storeNotes();
                break;
            //@@author WilsonLee2000
            case "List":
                List lists = new List(notes,toDo);
                lists.listNotes();
                break;
            //@@author WilsonLee2000
            case "Delete":
                Delete.deleteNotes();
                break;
            //@@author ZiqiuZeng
            case "Mark":
                if (isAnInt(toDo) == false) {
                    break;
                }
                int markIndex = Integer.parseInt(toDo) - 1;
                if (isInvalidIndex(markIndex, notes)) {
                    throw new IllegalIndexException();
                }
                notes.markAsDone(markIndex);
                Ui.printMark(notes.getText(markIndex));
                Storage.saveFile(filePath, notes.getAll());
                break;
            //@@author ZiqiuZeng
            case "Unmark":
                if (isAnInt(toDo) == false) {
                    break;
                }
                int unmarkIndex = Integer.parseInt(toDo) - 1;
                if (isInvalidIndex(unmarkIndex, notes)) {
                    throw new IllegalIndexException();
                }
                notes.markAsUndone(unmarkIndex);
                Ui.printUnmark(notes.getText(unmarkIndex));
                Storage.saveFile(filePath, notes.getAll());
                break;
            //@@author ZiqiuZeng
            case "Low":
                int lowIndex = Integer.parseInt(toDo) - 1;
                if (isInvalidIndex(lowIndex, notes)) {
                    throw new IllegalIndexException();
                }
                notes.setPriority(lowIndex, NotePriority.Priority.LOW);
                Ui.printPriority(lowIndex, notes.getAll());
                Storage.saveFile(filePath, notes.getAll());
                break;
            //@@author ZiqiuZeng
            case "Medium":
                int mediumIndex = Integer.parseInt(toDo) - 1;
                if (isInvalidIndex(mediumIndex, notes)) {
                    throw new IllegalIndexException();
                }
                notes.setPriority(mediumIndex, NotePriority.Priority.MEDIUM);
                Ui.printPriority(mediumIndex, notes.getAll());
                Storage.saveFile(filePath, notes.getAll());
                break;
            //@@author ZiqiuZeng
            case "High":
                int highIndex = Integer.parseInt(toDo) - 1;
                if (isInvalidIndex(highIndex, notes)) {
                    throw new IllegalIndexException();
                }
                notes.setPriority(highIndex, NotePriority.Priority.HIGH);
                Ui.printPriority(highIndex, notes.getAll());
                Storage.saveFile(filePath, notes.getAll());
                break;
            //@@author ZiqiuZeng
            case "FindInfo":
                if (isInvalidTodo(toDo)) {
                    throw new IllegalTodoException();
                }
                String keyword = toDo;
                Ui.printFindNotes(notes.relevantInfo(keyword));
                Storage.saveFile(filePath, notes.getAll());
                break;
            //@@author ZiqiuZeng
            case "FindPrior":
                switch (toDo.toLowerCase()) {
                case "low":
                    Ui.printFindNotes(notes.relevantPriority(NotePriority.Priority.LOW.name()));
                    break;
                case "medium":
                    Ui.printFindNotes(notes.relevantPriority(NotePriority.Priority.MEDIUM.name()));
                    break;
                case "high":
                    Ui.printFindNotes(notes.relevantPriority(NotePriority.Priority.HIGH.name()));
                    break;
                default:
                    throw new IllegalTodoException();
                }
                break;
            //@@author ZiqiuZeng
            case "FindMark":
                Ui.printFindNotes(notes.relevantMarked());
                break;
            //@@author ZiqiuZeng
            case "FindUnmark":
                Ui.printFindNotes(notes.relevantUnmarked());
                break;
            //@@author ZiqiuZeng
            case "Rank":
                switch (toDo) {
                case "Review Count":
                    notes.rankByReviewCount();
                    Ui.printNotesByReviewCount(notes.getAll());
                    Storage.saveFile(filePath, notes.getAll());
                    break;
                case "Priority":
                    notes.rankByPriority();
                    Ui.printNotesByPriority(notes.getAll());
                    Storage.saveFile(filePath, notes.getAll());
                    break;
                default:
                    throw new IllegalTodoException();
                }
                break;
            /*
             * The command "Clear" will continue to execute for as long as it is being entered,
             * without any other command or input interrupting it.
             */
            case "Clear":
                notes.reset();
                Storage.clearFile(filePath);
                break;
            /*
             * The command "Help" will continue to execute for as long as it is being entered,
             * without any other command or input interrupting it.
             */
            case "Help":
                HelpManual.readHelpManual();
                break;
            //@@author 0nandon
            case "Matrix":
                calculator.run(toDo);
                break;
            //@@author Khooyourun
            case "Quadratic":
                quadratic.solveQuadratic();
                break;
            default:
                break;
            }
        } catch (IllegalIndexException e) {
            System.out.println("Oops! This note does not exist. Please try again.");
        } catch (IllegalTodoException e) {
            Ui.printIncorrectFormatEntered();
        }

    }
}

