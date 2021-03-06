package gamejam.model.utils;

import gamejam.model.interfaces.Drawable;
import gamejam.ui.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;

public class Background implements Drawable {

    private Image backgroundImage;

    public Background(FileInputStream path) {
        this.backgroundImage = new Image(path, Main.CANVAS_WIDTH,Main.CANVAS_HEIGHT,false,true);
    }

    @Override
    public <T> void draw(T... obj) {
        GraphicsContext graphicsContext = (GraphicsContext) obj[0];
        graphicsContext.drawImage(backgroundImage, 0, 0);
    }
}
