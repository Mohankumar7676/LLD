public class Players {
    
    private final String id;
    private Integer score;

    public Players(String id, Integer score) {
        this.id = id;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
