import java.util.Arrays;
import java.util.Scanner;

public class ToyRobotApplication {
	public static void main(String[] args) {
		ToyRobot robot = new ToyRobot();
		int tableSize = 5;
		Scanner input = new Scanner(System.in);
		while (true) {
			String command = input.nextLine();
			String[] commandWithArgs = command.split(" ");

			if (command.startsWith("PLACE")) {
				if (commandWithArgs.length > 1) {
					String commandArgs = commandWithArgs[1];
					String[] pArgs = commandArgs.split(",");
					robot.setX(Integer.valueOf(pArgs[0]));
					robot.setY(Integer.valueOf(pArgs[1]));
					robot.setFacing(pArgs[2]);
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
					if (robot.getY() > 0)
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
		private String facing;

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

	}
}