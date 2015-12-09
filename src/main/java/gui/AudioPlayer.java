//
//
//package gui;
//
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.advanced.AdvancedPlayer;
//import javazoom.jl.player.advanced.PlaybackListener;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//
///**
// * Created by Frank on 2-11-2015.
// */
//public class AudioPlayer extends PlaybackListener implements Runnable {
//    private String filePath;
//    private AdvancedPlayer player;
//    private BufferedInputStream bufferedInputStream;
//    private Thread playerThread;
//
//    /**
//     * @param filePath  Path to audio file
//     */
//    public AudioPlayer(String filePath)
//    {
//        this.filePath = filePath;
//    }
//
//    /**
//     * play the audio file
//     */
//    public boolean play()
//    {
//        try
//        {
//            try {
//                File file = new File(filePath);
//                FileInputStream fis = new FileInputStream(file);
//                bufferedInputStream = new BufferedInputStream(fis);
//            }
//            catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                player = new AdvancedPlayer(bufferedInputStream);
//
//            }
//
//            catch (JavaLayerException e) {
//                e.printStackTrace();
//            }
//
//            this.player.setPlayBackListener(this);
//
//            playerThread = new Thread(this, "AudioPlayerThread");
//
//            playerThread.start();
//            return true;
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return false;
//    }
//
//    public void stop() throws JavaLayerException {
//        player.stop();
//        player.close();
//        playerThread.interrupt();
//    }
//
//
//
//    // Runnable members
//
//    /**
//     * run audio for player
//     */
//    public void run()
//    {
//        try
//        {
//            this.player.play();
//
//        }
//        catch (javazoom.jl.decoder.JavaLayerException ex)
//        {
//            ex.printStackTrace();
//        }
//    }
//
//
//}
