import java.util.*;
public interface LeaderBoardService {
    void submitScore(String id, Integer score);
    List<Players> getTopNPlayer(Integer n);
    Integer getRank(String id);
}
