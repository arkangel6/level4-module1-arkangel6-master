package _06_Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
	public static final Color SNAKE_COLOR = Color.BLUE;
	public static final int BODY_SIZE = 50;

	private SnakeSegment head;
	private SnakeSegment part;
	
	private ArrayList<SnakeSegment> snake;

	private Direction currentDirection;

	private boolean canMove = true;

	public Snake(Location location) {
		snake = new ArrayList<SnakeSegment>();
		head = new SnakeSegment(location, BODY_SIZE);
		snake.add(head);

		currentDirection = Direction.RIGHT;
	}
	
	int counter =0;
	public void feed(Location foodLocation) {
		//1. add a new SnakeSegment object to the snake
		
		
		Location l = new Location(foodLocation.x, foodLocation.y);
		switch(currentDirection) {
		case RIGHT:
			l  = new Location(snake.get(counter).getLocation().x+1, snake.get(counter).getLocation().y);
			break;
		case LEFT:
			l = new Location(snake.get(counter).getLocation().x-1, snake.get(counter).getLocation().y);
			break;
		case UP:
			l = new Location(snake.get(counter).getLocation().x, snake.get(counter).getLocation().y-1);
			break;
		case DOWN:
			l = new Location(snake.get(counter).getLocation().x, snake.get(counter).getLocation().y+1);
			break;
		
		}
	//	Location l = new Location(snake.get(0).getLocation().x+1, snake.get(0).getLocation().y);
		
		
		
		part = new SnakeSegment(l, BODY_SIZE);
		
		snake.add(part);
		
		
		counter++;
		
		
		//snake.add(new SnakeSegment(foodLocation, BODY_SIZE));
		
		//System.out.println(head.getLocation().x + " " + head.getLocation().y);
		//System.out.println(l.x + " " + l.y);
		
		
		//SnakeSegment snake2 = new SnakeSegment(snake.get(0).getLocation(), BODY_SIZE);
		//System.out.println(snake2.getLocation().x + " " + snake2.getLocation().y);
	}

	public Location getHeadLocation() {
		//2. return the location of the snake's head
		return head.getLocation();
	}

	public void update() {
		//1. use a switch statement to check on the currentDirection
		//   of the snake and calculate its next x and y position.
		
		

		
		for(int i = snake.size()-1; i > 0; i--) {
			
			//if(i == snake.size()-1) {
			//Location l = new Location(getHeadLocation().x, getHeadLocation().y);
			//snake.get(i).setLocation(l);	
		//}else {
			
			Location l2 = new Location(snake.get(i-1).getLocation().x, snake.get(i-1).getLocation().y);
			snake.get(i).setLocation(l2);
		//}
		}
		
		switch(currentDirection) {
		case RIGHT:
			head.getLocation().setX(head.getLocation().x +1);
			break;
		case LEFT:
			head.getLocation().setX(head.getLocation().x -1);
			break;
		case UP:
			head.getLocation().setY(head.getLocation().y -1);
			break;
		case DOWN:
			head.getLocation().setY(head.getLocation().y +1);
			break;
		
		}
		//2. Iterate through the SnakeSegments in reverse order
		//2a. Update each snake segment to the location of the segment 
		//    in front of it.
		
		
		//3. set the location of the head to the new location calculated in step 1
		//head.setLocation(head.getLocation());

		//4. set canMove to true
		canMove = true;
	}

	public void setDirection(Direction d) {
		//1. set the current direction equal to the passed in Direction only if canMove is true.
		//   set canMove equal to false.
		//   make sure the snake cannot completely reverse directions.
		if(canMove) {
		currentDirection = d;
		}else {
			canMove = false;
		}
	}

	public void reset(Location loc) {
		//1. clear the snake
		snake.clear();
		//2. set the location of the head
		head.setLocation(loc);
		//3. add the head to the snake
		snake.add(head);
	}

	public boolean isOutOfBounds() {
		//1. complete the method so it returns true if the head of the snake is outsize of the window
		//   and false otherwise
		
		if(head.getLocation().x > _00_SnakeGame.WINDOW_WIDTH) {
			return true;
		}
		if(head.getLocation().x < 0) {
			return true;
		}
		if(head.getLocation().y > _00_SnakeGame.WINDOW_HEIGHT) {
			return true;
		}
		if(head.getLocation().y < 0) {
			return true;
		}
		
		return false;
	}
	
	public boolean isHeadCollidingWithBody() {
		//1. complete the method so it returns true if the head is located
		//   in the same location as any other body segment
		
		for(int i = 1; i < snake.size(); i++) {
			if(snake.get(i).getLocation().x == head.getLocation().x && snake.get(i).getLocation().y == head.getLocation().y) {
				
				return true;
			}
		}
		
		
		return false;
	}

	public boolean isLocationOnSnake(Location loc) {
		//1. complete the method so it returns true if the passed in
		//   location is located on the snake
		
		for(int i = 1; i < snake.size(); i++) {
			if(snake.get(i).getLocation().x == loc.x && snake.get(i).getLocation().y == loc.y) {
				return true;
			}
		}
		
		return false;
	}

	public void draw(Graphics g) {
		for (SnakeSegment s : snake) {
			s.draw(g);
			System.out.println(snake.size());
		}
		
	}
}