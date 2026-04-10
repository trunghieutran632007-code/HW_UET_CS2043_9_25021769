public class Task implements Runnable {
    private String name;
    private long durationMs;

    public Task(String name, long durationMs) {
        this.name = name;
        this.durationMs = durationMs;
    }

    @Override
    public void run() {
        try {
            System.out.println("Start " + name);

            Thread.sleep(durationMs);
            System.out.println("End " + name);
        } catch (InterruptedException e) {
            System.out.println("Task " + name + " bi gian doan.");
        }
    }
}
