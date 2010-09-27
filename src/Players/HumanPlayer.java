package Players;

import Game.Board;
import Game.Game;

public class HumanPlayer implements Player {

	private Game game;
	private Board board;
	private int playerId;
	private int[] nextMove;
	
	public synchronized int[] getNextMove() {
		try {
			this.wait();
		} catch (Exception e) {
			System.err.println("Gotta run, can't wait!");
		}
		int[] move = {nextMove[0], nextMove[1]}; 
		this.nextMove = null;
		return move;
	}
	
	public synchronized boolean setNextMove(int[] nextMove) {
		
		if (game.getBoard().getField(nextMove[0], nextMove[1]) == 0) {
			this.nextMove = nextMove;
			
			this.notify();
			return true;
		}
		
		return false;
	}

	public void setGame(Game theGame) {
		this.game = theGame;
	}
	
	public void setPlayerId(int thePlayer) {
		this.playerId = thePlayer;
	}
	
	public int getPlayerId() {
		return this.playerId;
	}

}