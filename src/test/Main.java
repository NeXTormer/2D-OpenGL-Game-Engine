package test;

import engine.graphics.Screen;

/**
 * Created by Felix on 17.07.2016.
 */
public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        Screen s = new Screen(600, 600, "peta", new Panel());

    }

}
