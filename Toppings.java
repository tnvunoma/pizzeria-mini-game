package indy;

/**
 * This is the Toppings interface. All translatable toppings implement this interface.
 */
public interface Toppings {
    void addToPane();
    boolean toppingClicked(double x, double y);
    void select();
    boolean isSelected();
    void deselect();
    void translate(double x, double y);
    double getX();
    double getY();
    void delete();
    String parseToppings();
}
