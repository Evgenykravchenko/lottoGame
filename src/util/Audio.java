package util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {
    private String track;
    private File file;
    private AudioInputStream audioInputStream = null;
    private static Clip clip = null;
    private static FloatControl volumeC = null;

    public void setWt(double wt) {
        Audio.wt = wt;
    }

    public double getWt() {
        return Audio.wt;
    }

    public static double wt;
    private static boolean pl_audio;

    public Audio(double wt, String track) {
        Audio.wt = wt;
        this.track = track;
        pl_audio = false;
        this.file = new File(track);

    }

    public Audio(double wt) {
        Audio.wt = wt;
        pl_audio = false;
    }

    public void sound() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
        }

        try {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.setFramePosition(0);
            clip.start();
        } catch (LineUnavailableException | IOException e) {

        }
    }

    public void setVolume() {
        if (Audio.wt < 0) Audio.wt = 0;
        if (Audio.wt > 1) Audio.wt = 1;
        float min = Audio.volumeC.getMinimum();
        float max = Audio.volumeC.getMaximum();
        Audio.volumeC.setValue((max - min) * (float)Audio.wt + min);
    }

    public void play() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
        }

        try {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            Audio.volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            if (!pl_audio) {
                clip.setFramePosition(0);
                clip.start();
                pl_audio = true;
            }

        } catch (LineUnavailableException | IOException e) {

        }
    }

    public void stop() {
        clip.stop();
        clip.close();
        pl_audio = false;
    }

    public void repeat() {
        if (pl_audio) {
            clip.loop(Integer.MAX_VALUE);
        }
    }
}
