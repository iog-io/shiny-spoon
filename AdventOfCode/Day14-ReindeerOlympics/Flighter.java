/**
 * Created by zeno on 25.09.2016.
 */
public class Flighter {
    private String name;
    private boolean flying;
    private int velocity;
    private int flyingTime;
    private int restingTime;
    private int distant;
    private int timeline;

    // part 2
    private int point;

    public Flighter(String name, int vel, int fly, int rest) {
        flying = true;
        distant = 0;
        timeline = 0;
        point = 0;
        this.name = name;
        velocity = vel;
        flyingTime = fly;
        restingTime = rest;
    }

    public void score() {
        point++;
    }

    public void proceed() {
        timeline++;
        if (flying) {
            timeline %= flyingTime;
            distant += velocity;
        }
        else {
            timeline %= restingTime;
        }
        if (timeline == 0) {
            flying = !flying;
        }
    }

    public int getDistant() {
        return distant;
    }

    public int getPoint() {
        return point;
    }
}
