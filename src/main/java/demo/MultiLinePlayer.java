package demo;

public class MultiLinePlayer {
	private int credits; //Number of credits that player has
	private int hotStreak = 0;
	private int[][] scores;

    public MultiLinePlayer(int credits){ //Returning player has a set of scores
		this.credits = credits;
		this.scores = new int[52][52];
		for (int i = 0; i < 52; i++){
			scores[i][0] = i; //Streak of i
			scores[i][1] = 0; //Number of rounds where player got a streak of i
		}
	}

	public void addCredits(int cred) {
		int toAddTo = this.getCredits();
		this.setCredits(toAddTo + cred);
	}

	public int getCredits(){
		return credits;
	}

	public void setCredits(int cred){
		credits = cred;
	}

	public int getHotStreak() {
		return hotStreak;
	}

	public void setHotStreak(int hotStreak) {
		this.hotStreak = hotStreak;
	}

	public void setStreak(int streak){ //New high score
		if (streak > hotStreak){
			hotStreak = streak;
		}
		scores[streak][1] += 1;
	}

    public void setNumberOfLines(int numberOfLines) {
		int[] streaks = new int[numberOfLines];
		for (int index = 0; index < numberOfLines; index++) {
			streaks[index] = 0; //For this game
		}
	}
}
