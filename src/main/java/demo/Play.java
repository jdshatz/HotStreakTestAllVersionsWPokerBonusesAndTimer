package demo;

import java.io.IOException;

public class Play {
	public static void main(String[] args) throws IOException, InterruptedException {
		    MultiLinePlayer player = new MultiLinePlayer(10000);
		   // MainMenu menu = new MainMenu(player);
			MultiLineGameAllVersions game = new MultiLineGameAllVersions(player);
		    game.play(player);
	}
}