package lambdaLearn;

public class Reward {
    private String id;
    private String reward;

    public Reward(String id, String reward) {
        this.id = id;
        this.reward = reward;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

}
