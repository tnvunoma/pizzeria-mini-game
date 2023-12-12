package indy;

/**
 * This is the Undoable interface. All undoable actions implement this interface.
 */
public interface Undoable {
    void undo();
    void redo();
}
