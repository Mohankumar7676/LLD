public class LeaderBoardApplication {

    public static void main(String[] args) {
        LeaderBoardServiceImpl leaderboard = new LeaderBoardServiceImpl();
        leaderboard.submitScore("player1", 10);
        leaderboard.submitScore("player2", 11);
        leaderboard.submitScore("player3", 56);
        leaderboard.submitScore("player1", 12);

        System.out.println("Top Players: " + leaderboard.getTopNPlayer(2));

        System.out.println("Rank of player1: " + leaderboard.getRank("player1"));
        System.out.println("Rank of player3: " + leaderboard.getRank("player3"));

    }

}
