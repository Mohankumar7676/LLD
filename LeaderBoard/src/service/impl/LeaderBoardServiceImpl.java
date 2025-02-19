import java.util.*;
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final Map<String,Players> players;
    private final TreeSet<Players> sortedPlayers;

    public LeaderBoardServiceImpl() {
        this.players = new HashMap<>();
        sortedPlayers = new TreeSet<>((a,b) -> b.getScore() != a.getScore() ? 
            Integer.compare(b.getScore(), a.getScore()) :
            a.getId().compareTo(b.getId()));
    }

    @Override
    public void submitScore(String id, Integer score) {
        if(players.containsKey(id)) {
            Players c = players.get(id);
            sortedPlayers.remove(c);
            c.setScore(score);
            sortedPlayers.add(c);
            return;
        }
        Players p = new Players(id,score);
        players.put(id,p);
        sortedPlayers.add(p);
    }

    @Override
    public List<Players> getTopNPlayer(Integer n) {
        List<Players> listPlayers = new ArrayList<>();
        int count=0;
       for(Players p :sortedPlayers) {
        if(count>=n) break;
        listPlayers.add(p);
        count++;
       }
       return listPlayers;
    }

    @Override
    public Integer getRank(String id) {
        int rank=1;
       for(Players p :sortedPlayers) {
        if(p.getId().equals(id)) {
            return rank;
        }
        rank++;
       }
       return -1;
    }
}
