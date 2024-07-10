import java.util.Arrays;
import java.util.Scanner;

public class ToyRobotApplication {
	public static void main(String[] args) {
		System.out.println("=====Welcome to Toy Robot Code Challenge!!!!=====");
		System.out.println("-----Available Commands-----\n1.PLACE X,Y,F\n2.MOVE\n3.LEFT\n4.RIGHT\n5.REPORT");
		ToyRobot robot = new ToyRobot();
		int tableSize = 5;
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.print(">");
			String command = input.nextLine();
			String[] commandWithArgs = command.split(" ");

			//COMMANDS
			if (command.startsWith("PLACE")) { //PLACE
				if (commandWithArgs.length > 1) {
					String commandArgs = commandWithArgs[1];
					String[] pArgs = commandArgs.split(",");
					int x = Integer.valueOf(pArgs[0]);
					int y = Integer.valueOf(pArgs[1]);
					if (x > 5 && y > 5) {
						System.out.println("Can't place the robot....");
					}
					else {
						robot.setX(x);
						robot.setY(y);
					}
					
					String facing = pArgs[2];
					if (robot.isValidFacing(facing)) {
						robot.setFacing(facing);
					}
					else {
						System.out.println("Invalid Facing....");
					}
					
				}
			}

			else if (command.startsWith("MOVE")) {
				switch (robot.facing) {
				case "NORTH":
					if (robot.getY() < tableSize)
						robot.moveUp();
					break;
				case "SOUTH":
					if (robot.getY() > 0)
						robot.moveDown();
					break;
				case "EAST":
					if (robot.getX() < tableSize)
						robot.moveRight();
					break;
				case "WEST":
					if (robot.getX() > 0)
						robot.moveLeft();
					break;
				}
			}

			else if (command.startsWith("LEFT")) {
				robot.turnLeft();
			}

			else if (command.startsWith("RIGHT")) {
				robot.turnRight();
			}

			else if (command.startsWith("REPORT")) {
				System.out.format("Output: %s,%s,%s", robot.x, robot.y, robot.facing);
				System.out.println();
			}

			if ("exit".equals(command)) {
				input.close();
				break;
			}
		}

		System.out.println("Bye bye....");
		System.exit(1);
	}

	static class ToyRobot {
		private int x, y;
		private String facing; //current robot facing

		private String[] facings = { "SOUTH", "WEST", "NORTH", "EAST" };

		public void moveUp() {
			y++;
		}

		public void moveDown() {
			y--;
		}

		public void moveLeft() {
			x--;
		}

		public void moveRight() {
			x++;
		}

		public void turnLeft() {
			int facingIndex = Arrays.asList(facings).indexOf(facing) - 1;
			facingIndex = facingIndex < 0 ? 3 : facingIndex;
			this.facing = facings[facingIndex];
		}

		public void turnRight() {
			int facingIndex = Arrays.asList(facings).indexOf(facing) + 1;
			facingIndex = facingIndex > 3 ? 0 : facingIndex;
			this.facing = facings[facingIndex];
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public String getFacing() {
			return facing;
		}

		public void setFacing(String facing) {
			this.facing = facing;
		}

		@Override
		public String toString() {
			return "ToyRobot [x=" + x + ", y=" + y + ", facing=" + facing + "]";
		}

		boolean isValidFacing(String facing) {
			for (String string : facings) {
				if (facing.equals(string)) {
					return true;
				}
			}
			
			return false;
		}
	}
}