package org.braidner.runner.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Braidner
 * Date: 05/08/2016
 * Time: 14:04
 */
public class Score {
    private User user;
    private float score;
    private float distance;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
