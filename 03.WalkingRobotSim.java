/*
A robot on an infinite XY-plane starts at point (0, 0) facing north. The robot can receive a sequence of these three possible types of commands:

-2: Turn left 90 degrees.
-1: Turn right 90 degrees.
1 <= k <= 9: Move forward k units, one unit at a time.
Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi). 
If the robot runs into an obstacle, then it will instead stay in its current location and move on to the next command.
Return the maximum Euclidean distance that the robot ever gets from the origin squared (i.e. if the distance is 5, return 25).

Note:
North means +Y direction.
East means +X direction.
South means -Y direction.
West means -X direction.
There can be obstacle in [0,0].
 
Example 1:
Input: commands = [4,-1,3], obstacles = []
Output: 25
Explanation: The robot starts at (0, 0):
1. Move north 4 units to (0, 4).
2. Turn right.
3. Move east 3 units to (3, 4).
The furthest point the robot ever gets from the origin is (3, 4), which squared is 32 + 42 = 25 units away.

Example 2:
Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
Output: 65
Explanation: The robot starts at (0, 0):
1. Move north 4 units to (0, 4).
2. Turn right.
3. Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
4. Turn left.
5. Move north 4 units to (1, 8).
The furthest point the robot ever gets from the origin is (1, 8), which squared is 12 + 82 = 65 units away.

Example 3:
Input: commands = [6,-1,-1,6], obstacles = []
Output: 36
Explanation: The robot starts at (0, 0):
1. Move north 6 units to (0, 6).
2. Turn right.
3. Turn right.
4. Move south 6 units to (0, 0).
The furthest point the robot ever gets from the origin is (0, 6), which squared is 62 = 36 units away.
 
Constraints:
1 <= commands.length <= 104
commands[i] is either -2, -1, or an integer in the range [1, 9].
0 <= obstacles.length <= 104
-3 * 104 <= xi, yi <= 3 * 104
*/
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Initialize the direction vectors for North, East, South, and West
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // N, E, S, W
        int directionIndex = 0; // Start facing North
        int x = 0, y = 0; // Start at the origin (0, 0)
        int maxDistanceSquared = 0; // To keep track of the maximum distance squared
        // Convert the obstacles to a set of strings for quick lookup
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }
        // Iterate through each command
        for (int command : commands) {
            if (command == -2) {
                // Turn left (counter-clockwise)
                directionIndex = (directionIndex + 3) % 4; // Equivalent to -1 mod 4
            } else if (command == -1) {
                // Turn right (clockwise)
                directionIndex = (directionIndex + 1) % 4;
            } else {
                // Move forward k steps in the current direction
                int dx = directions[directionIndex][0];
                int dy = directions[directionIndex][1];
                // Move one step at a time to check for obstacles
                for (int step = 0; step < command; step++) {
                    int newX = x + dx;
                    int newY = y + dy;
                    // Check if the new position is an obstacle
                    if (obstacleSet.contains(newX + "," + newY)) {
                        // If it is an obstacle, stop moving in this direction
                        break;
                    }
                    // Update the robot's position
                    x = newX;
                    y = newY;
                    // Calculate the squared distance from the origin
                    maxDistanceSquared = Math.max(maxDistanceSquared, x * x + y * y);
                }
            }
        }
        return maxDistanceSquared;
    }
}
