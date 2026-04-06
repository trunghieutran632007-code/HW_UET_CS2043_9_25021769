package Bai6_09.src;

class VideoPlayer implements VideoPlayable {
    @Override
    public void playVideo(String file) {
        System.out.println("Dang phat video tu file: " + file);
    }
}
