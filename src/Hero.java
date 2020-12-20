public class Hero {

    private int x;
    private int y;

    {
        x = 1;
        y = 1;
    }

    public Hero() {};

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
