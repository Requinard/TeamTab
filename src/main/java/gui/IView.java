package gui;

/**
 * Created by david on 6-10-15.
 */
public interface IView {
    boolean load();

    boolean deload();

    boolean pass(IView nextView);
}
