package exceptions;

import byu.Instruction;

/**
 * An exception that indicates the description of an instruction is invalid.
 */
public class InvalidDescriptionException extends DukeException {

    private static final String NOT_AN_INTEGER_MESSAGE = "Description must be an integer!";
    private static final String WRONG_EVENT_FORMAT_MESSAGE =
            "Description of EVENT must be in the format (EVENT /at YYYY/MM/DD TT:TT)!";
    private static final String WRONG_DEADLINE_FORMAT_MESSAGE =
            "Description of DEADLINE must be in the format (DEADLINE /by YYYY/MM/DD TT:TT)!";

    private final Instruction instruction;

    /**
     * Creates an exception that indicates the description of an instruction is invalid.
     * @param instruction the instruction type of the user input.
     */
    public InvalidDescriptionException(Instruction instruction) {
        this.instruction = instruction;
    }

    @Override
    public String getMessage() {
        String message;
        switch (instruction) {
        case MARK:
        case UNMARK:
        case DELETE:
            message = NOT_AN_INTEGER_MESSAGE;
            break;
        case EVENT:
            message = WRONG_EVENT_FORMAT_MESSAGE;
            break;
        case DEADLINE:
            message = WRONG_DEADLINE_FORMAT_MESSAGE;
            break;
        default:
            message = "ERROR!!";
        }
        return message;
    }

}

