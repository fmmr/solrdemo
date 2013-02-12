package utils;

/**
* User: frerodla
* Date: 12.02.13
* Time: 12:32
*/
public final class Timer {
    private final long start;
    private long stop = 0L;

    public Timer() {
        start = System.currentTimeMillis();
    }

    public long stop() {
        if (stop == 0L) {
            stop = System.currentTimeMillis();
        }
        return stop - start;
    }
}
