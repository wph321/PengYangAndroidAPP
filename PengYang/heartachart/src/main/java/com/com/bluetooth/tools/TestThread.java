package com.com.bluetooth.tools;

import java.util.Random;

/**
 * Created by admin on 2018/3/19.
 */

public class TestThread implements Runnable {

    int startx,starty,stopx,syopy;

    @Override
    public void run() {
        Random random = new Random();
        startx = random.nextInt();

    }
}
