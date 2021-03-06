package gamejam.ui;

import gamejam.model.interfaces.Drawable;
import gamejam.model.utils.Background;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EscapeMenu implements Drawable {

    // TODO: PATH AND IMG
    private static final String ESCAPE_MENU_BACKGROUND_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/background_darkmode.png";

    // TODO: PATHS
    private static final String RESUME_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/RESUMEBUTTON.png";
    private static final String RESTART_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/RESTARTBUTTON.png";
    private static final String QUIT_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/QUITBUTTON.png";

    private static final String MUSIC_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/note.png";

    private static final String MUTE_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/note_not.png";

    private static final double MUSIC_BUTTON_X = 18;
    private static final double MUSIC_BUTTON_Y = Main.CANVAS_HEIGHT - 103;

    private static final double BUTTON_WIDTH = 200;
    private static final double BUTTON_HEIGHT = 75;

    private static final double RESUME_BUTTON_X = Main.CANVAS_WIDTH / 2 - (BUTTON_WIDTH / 2);
    private static final double RESUME_BUTTON_Y = Main.CANVAS_HEIGHT / 5;

    private static final double RESTART_BUTTON_X = Main.CANVAS_WIDTH / 2 - (BUTTON_WIDTH / 2);
    private static final double RESTART_BUTTON_Y = Main.CANVAS_HEIGHT / 5 + BUTTON_HEIGHT + 50;

    private static final double QUIT_BUTTON_X = Main.CANVAS_WIDTH / 2 - (BUTTON_WIDTH / 2);
    private static final double QUIT_BUTTON_Y = Main.CANVAS_HEIGHT / 5 + 2 * BUTTON_HEIGHT + 2*50;

    private Background background;

    private Runnable resumeRunnable;
    private Runnable restartRunnable;
    private Runnable quitRunnable;

    private Image resumeButtonImage;
    private Image restartButtonImage;
    private Image quitButtonImage;

    private Image musicButtonImage;
    private Image muteButtonImage;

    private MediaPlayer mediaPlayer;

    public EscapeMenu(Runnable resumeRunnable,
                      Runnable restartRunnable,
                      Runnable quitRunnable, MediaPlayer mediaPlayer) throws FileNotFoundException {
        this.resumeRunnable = resumeRunnable;
        this.restartRunnable = restartRunnable;
        this.quitRunnable = quitRunnable;
        this.background = new Background(new FileInputStream(ESCAPE_MENU_BACKGROUND_PATH));
        this.resumeButtonImage =
                new Image(new FileInputStream(RESUME_BUTTON_PATH),
                        BUTTON_WIDTH, BUTTON_HEIGHT,
                        false, false);
        this.restartButtonImage =
                new Image(new FileInputStream(RESTART_BUTTON_PATH),
                        BUTTON_WIDTH, BUTTON_HEIGHT,
                        false, false);
        this.quitButtonImage =
                new Image(new FileInputStream(QUIT_BUTTON_PATH),
                        BUTTON_WIDTH, BUTTON_HEIGHT,
                        false, false);

        this.musicButtonImage =
                new Image(new FileInputStream(MUSIC_BUTTON_PATH),
                        50, 50,
                        false, false);

        this.muteButtonImage =
                new Image(new FileInputStream(MUTE_BUTTON_PATH),
                        50, 50,
                        false, false);

        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];

        drawBackground(gc);
        drawResumeButton(gc);
        drawRestartButton(gc);
        drawQuitButton(gc);

        if (mediaPlayer.isMute()) {
            gc.drawImage(muteButtonImage, MUSIC_BUTTON_X, MUSIC_BUTTON_Y);
        } else {
            gc.drawImage(musicButtonImage, MUSIC_BUTTON_X, MUSIC_BUTTON_Y);
        }

    }

    private <T> void drawBackground(T ... obj) {
        background.draw(obj);
    }

    private <T> void drawResumeButton(T ... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(resumeButtonImage, RESUME_BUTTON_X, RESUME_BUTTON_Y);
    }

    private <T> void drawRestartButton(T ... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(restartButtonImage, RESTART_BUTTON_X, RESTART_BUTTON_Y);
    }

    private <T> void drawQuitButton(T ... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(quitButtonImage, QUIT_BUTTON_X, QUIT_BUTTON_Y);
    }

    public void onClick(double mouseX,double mouseY){
        if (isOnResumeButton(mouseX, mouseY)) {
            // TODO: Resume button is clicked
            resumeRunnable.run();
        } else if (isOnRestartButton(mouseX, mouseY)) {
            // TODO: Restart button is clicked
            System.out.println("restrat");
            restartRunnable.run();
        } else if (isOnQuitButton(mouseX, mouseY)) {
            // TODO: Quit button is clicked
            quitRunnable.run();
        } else if (isOnMusicButton(mouseX, mouseY)) {
            if (this.mediaPlayer.isMute()) {
                this.mediaPlayer.setMute(false);
            } else {
                this.mediaPlayer.setMute(true);
            }
        }
    }

    private boolean isOnResumeButton(double mX, double mY) {
        return (mX >= RESUME_BUTTON_X && mX <= RESUME_BUTTON_X + resumeButtonImage.getWidth())
                && (mY >= RESUME_BUTTON_Y && mY <= RESUME_BUTTON_Y + resumeButtonImage.getHeight());
    }

    private boolean isOnRestartButton(double mX, double mY) {
        return (mX >= RESTART_BUTTON_X && mX <= RESTART_BUTTON_X + restartButtonImage.getWidth())
                && (mY >= RESTART_BUTTON_Y && mY <= RESTART_BUTTON_Y + restartButtonImage.getHeight());
    }

    private boolean isOnQuitButton(double mX, double mY) {
        return (mX >= QUIT_BUTTON_X && mX <= QUIT_BUTTON_X + quitButtonImage.getWidth())
                && (mY >= QUIT_BUTTON_Y && mY <= QUIT_BUTTON_Y + quitButtonImage.getHeight());
    }

    private boolean isOnMusicButton(double mX, double mY) {
        return (mX >= MUSIC_BUTTON_X && mX <= MUSIC_BUTTON_X + musicButtonImage.getWidth())
                && (mY >= MUSIC_BUTTON_Y && mY <= MUSIC_BUTTON_Y + musicButtonImage.getHeight());
    }
}
