package edu.colorado.cs.alien.fixed;

import java.net.URL;

public class HttpDownload {

  public static void main(String[] args) throws Exception {

    URL from = new URL("http://www.cs.colorado.edu/~kena/index.html");

    final Downloader downloader = new Downloader(from, "index.html");

    final ProgressListener l = new ProgressListener() {

      public void onProgress(int n) {
        try {
          System.out.print("\n"+n);
          System.out.flush();

          AlienThread alien = new AlienThread(downloader, this);
          alien.start();
          alien.join();
        } catch (InterruptedException ex) {
        }
      }

      public void onComplete(boolean success) {}

    };

    downloader.addListener(l);
    downloader.start();
    downloader.join();

  }

}
