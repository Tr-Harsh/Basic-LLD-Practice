package com.CRED.Strategies;

import java.util.ArrayList;

public class Game {
    private Alley alley;
    private Integer totalLanes;

    public Game(Integer totalLanes){
        this.totalLanes = totalLanes;
    }
    public void init(Integer laneId){
        alley = new Alley(totalLanes);
        Lane lane = alley.getLanes().get(laneId);
        lane.getPlayers().add(new Player(1, "Harsh", new ArrayList<>(),0));
        lane.getPlayers().add(new Player(2, "Shakti", new ArrayList<>(),0));
        lane.getPlayers().add(new Player(3, "Neha", new ArrayList<>(),0));
        lane.getPlayers().add(new Player(4, "Aditi", new ArrayList<>(),0));
    }

    public void start(Integer laneId){
        Lane lane = alley.getLanes().get(laneId);
        for(int round = 1; round <= 10; round++){
            for(Player player : lane.getPlayers()){
                lane.calculateScore(player, round);
                System.out.println(player.getName() + " : " + player.getTotalScore());
            }
            System.out.println("-------------------------------------");
        }
    }

    public Player declareWinner(Integer laneId){
        Integer maxScore = Integer.MIN_VALUE;
        Player winner = new Player();
        Lane lane = alley.getLanes().get(laneId);
        for(Player player : lane.getPlayers()){
            if(player.getTotalScore() > maxScore){
                maxScore = player.getTotalScore();
                winner = player;
            }
        }
        return winner;
    }
}
