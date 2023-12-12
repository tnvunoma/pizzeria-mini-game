package indy;

public class TranslateTopping implements Undoable{
    private Toppings topping;
    private double xPos;
    private double yPos;
    public TranslateTopping(Toppings topping, double xPos, double yPos) {
        this.topping = topping;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void undo() {
        this.topping.translate(this.xPos, this.yPos);
    }

    @Override
    public void redo() {
        this.topping.translate(this.xPos, this.yPos);
    }
}
