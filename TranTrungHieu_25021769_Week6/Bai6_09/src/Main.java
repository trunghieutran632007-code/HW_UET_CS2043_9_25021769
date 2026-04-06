package Bai6_09.src;

public class Main {
    public static void main(String[] args) {
        // Tao cac player cu the
        AudioPlayable myAudio = new AudioPlayer();
        VideoPlayable myVideo = new VideoPlayer();

        // Truyen vao MediaPlayer thong qua constructor
        MediaPlayer player = new MediaPlayer(myAudio, myVideo);

        // Goi cac ham phat
        player.playAudio("audio.mp3");
        player.playVideo("video.mp4");
    }
}
