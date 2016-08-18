package org.braidner.runner.domain;

import java.util.List;

/**
 * Created by KuznetsovNE on 05.08.2016.
 */
public class RunInfo {

    private Score score;
    private float distance;
    private List<Item> droppedItems;
    private float energy;
    private float gold;

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public List<Item> getDroppedItems() {
        return droppedItems;
    }

    public void setDroppedItems(List<Item> droppedItems) {
        this.droppedItems = droppedItems;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getGold() {
        return gold;
    }

    public void setGold(float gold) {
        this.gold = gold;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
