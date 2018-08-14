package io.github.carlosthe19916.core.utils.threading;

public class WindupChildThread extends Thread {

    final Thread parentThread;

    public WindupChildThread(Thread parentThread, Runnable r) {
        super(r);
        this.parentThread = parentThread;
    }

    public Thread getParentThread() {
        return parentThread;
    }
}

