package Bai6_09.src;

class AudioPlayer implements AudioPlayable {
    @Override
    public void playAudio(String file) {
        System.out.println("Dang phat am thanh tu file: " + file);
    }
}
