package gui;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Frank on 2-11-2015.
 */
public class AudioPlayer extends PlaybackListener implements Runnable {
    private String filePath;
    private AdvancedPlayer player;
    private Thread playerThread;
    private BufferedInputStream bufferedInputStream;

    public AudioPlayer(String filePath)
    {
        this.filePath = filePath;
    }

    public void play()
    {
        try
        {

            try {
                File file = new File(filePath);
                FileInputStream fis = new FileInputStream(file);
                bufferedInputStream = new BufferedInputStream(fis);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                player = new AdvancedPlayer(bufferedInputStream);

            }

            catch (JavaLayerException e) {
                e.printStackTrace();
            }

            this.player.setPlayBackListener(this);

            this.playerThread = new Thread(this, "AudioPlayerThread");

            this.playerThread.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }



    // Runnable members

    public void run()
    {
        try
        {
            this.player.play();
        }
        catch (javazoom.jl.decoder.JavaLayerException ex)
        {
            ex.printStackTrace();
        }

    }
}
