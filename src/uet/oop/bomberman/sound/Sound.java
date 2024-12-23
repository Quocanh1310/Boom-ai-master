package uet.oop.bomberman.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    private static Clip currentClip;

    public static void play(String sound) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    URL soundURL = Sound.class.getResource("/sound/" + sound + ".wav");
                    if (soundURL == null) {
                        System.err.println("Không tìm thấy file âm thanh: " + sound);
                        return;
                    }

                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundURL);
                    Clip clip = AudioSystem.getClip();
                    clip.open(inputStream);
                    clip.start();

                    currentClip = clip;
                } catch (Exception e) {
                    System.err.println("Lỗi khi phát âm thanh: " + e.getMessage());
                }
            }
        }).start();
    }

    public static void stop() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    if (currentClip != null && currentClip.isRunning()) {
                        currentClip.stop(); 
                    }
                } catch (Exception e) {
                    System.err.println("Lỗi khi dừng âm thanh: " + e.getMessage());
                }
            }
        }).start();
    }
}
