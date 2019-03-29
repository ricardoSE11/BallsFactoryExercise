package sample;

import  java.util.ArrayList;

public class BallListImpl implements IPrototype<BallListImpl>{

    private String name;
    private ArrayList<Ball> balls = new ArrayList<>();

    public BallListImpl(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    @Override
    public BallListImpl clone(){
        return deepClone();
    }

    @Override
    public BallListImpl deepClone(){
        ArrayList<Ball> cloneBalls = new ArrayList<>();
        for(int i = 0; i < this.balls.size(); i++){
            Ball cloneBall = this.balls.get(i).clone();
            cloneBalls.add(cloneBall);
        }
        BallListImpl cloneBallList = new BallListImpl();
        cloneBallList.setName(this.name);
        cloneBallList.setBalls(cloneBalls);
        return cloneBallList;
    }

    public void addBall(Ball ball){
        this.balls.add(ball);
    }

}