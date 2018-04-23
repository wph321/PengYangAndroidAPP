package com.draw.heart;

/**
 * Created by admin on 2018/3/23.
 */

public class LastHeart {

    private float startx,starty,stopx,stopy;

    public LastHeart(float startx, float starty, float stopx, float stopy) {
        this.startx = startx;
        this.starty = starty;
        this.stopx = stopx;
        this.stopy = stopy;
    }

    public LastHeart() {
    }


    public float getStartx() {
        return startx;
    }

    public void setStartx(float startx) {
        this.startx = startx;
    }

    public float getStarty() {
        return starty;
    }

    public void setStarty(float starty) {
        this.starty = starty;
    }

    public float getStopx() {
        return stopx;
    }

    public void setStopx(float stopx) {
        this.stopx = stopx;
    }

    public float getStopy() {
        return stopy;
    }

    public void setStopy(float stopy) {
        this.stopy = stopy;
    }
}
