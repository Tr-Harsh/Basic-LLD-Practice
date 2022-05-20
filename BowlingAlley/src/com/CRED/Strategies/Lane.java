package com.CRED.Strategies;

import com.CRED.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lane {
    private List<Player> players;

    public Lane() {
        players = new ArrayList<>();
    }

    public Lane(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void calculateScore(Player player, Integer round){
        Random random = new Random();
        Window window = new Window();
        Strategy strategy;

        Frame set1 = new Frame(random.nextInt(11));
        if(set1.getFrameScore()==10){
            strategy = new Strike();
            if(round==10){
                Frame set3 = new Frame(random.nextInt(11));
                if(set3.getFrameScore()==10){
                    set3.setFrameScore(set3.getFrameScore()+10);
                    window.getFrames().add(set3);
                }
            }
        }
        else{
            Frame set2 = new Frame(random.nextInt(11-set1.getFrameScore()));
            if(set1.getFrameScore() + set2.getFrameScore()==10){
                strategy = new Spare();
                window.getFrames().add(set2);
                if(round==10){
                    Frame set3 = new Frame(random.nextInt(11));
                    if(set3.getFrameScore()==10){
                        set3.setFrameScore(set3.getFrameScore()+10);
                        window.getFrames().add(set3);
                    }
                }
            }
            else{
                strategy = new Strategy();
            }
        }
        window.setStrategy(strategy);
        Integer total = 0;
        for(Frame frame : window.getFrames()){
            total += frame.getFrameScore();
        }
        window.setTotalScore(total);
        player.setTotalScore(player.getTotalScore()+ window.getTotalScore());
    }
}
