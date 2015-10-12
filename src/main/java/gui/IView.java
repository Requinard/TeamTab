package gui;

import javafx.scene.Scene;

/**
 * Created by david on 6-10-15.
 */
public interface IView {
    public boolean load();

    public boolean deload();

    public boolean pass(IView nextView);

    public boolean passScene(Scene scene);
}
