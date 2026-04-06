package Bai6_09.src;

public class MediaPlayer implements AudioPlayable, VideoPlayable {
    private AudioPlayable audio;
    private VideoPlayable video;

    public MediaPlayer(AudioPlayable audio, VideoPlayable video) {
        this.audio = audio;
        this.video = video;
    }

    @Override
    public void playAudio(String file) {
        if (this.audio != null) {
            this.audio.playAudio(file);
        } else {
            System.out.println("Loi: Khong co trinh phat am thanh!");
        }
    }

    @Override
    public void playVideo(String file) {
        if (this.video != null) {
            this.video.playVideo(file);
        } else {
            System.out.println("Loi: Khong co trinh phat video!");
        }
    }

}
