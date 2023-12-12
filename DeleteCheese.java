package indy;

/**
 * This DeleteCheese class implements the Undoable interface, allowing the action of deleting cheese to be undoable
 * and redoable.
 */
public class DeleteCheese implements Undoable {
    Cheese cheese;
    public DeleteCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    /**
     * This method fulfills the contract of the Undoable interface. Undoing deleting cheese would make the cheese
     * visible.
     */
    @Override
    public void undo() {
        this.cheese.showCheese(true);
    }

    /**
     * This method fulfills the contract of the Undoable interface. Redoing deleting cheese would make the cheese
     * invisible.
     */
    @Override
    public void redo() {
        this.cheese.showCheese(false);
    }
}
