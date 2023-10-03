package stack;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Jul 25,2023 - 8:40 AM
 */

import java.util.Arrays;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 *
 *
 * Example 1:
 *
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 * Example 2:
 *
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 * Example 3:
 *
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 *
 * Constraints:
 *
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 */
public class AsteroidCollision {

    public static void main(String[] args) {
        AsteroidCollision o = new AsteroidCollision();
        System.out.println();
    }

    public int[] asteroidCollision(int[] asteroids) {

        int j = 0;
        for (int i = 0; i < asteroids.length; i++) {

            while (j > 0 && asteroids[j-1] > 0 && asteroids[i] < 0 && asteroids[j-1] < -asteroids[i]) {
                j--;
            }

            if (j == 0 || asteroids[j-1] < 0 || asteroids[i] > 0) {
                asteroids[j++] = asteroids[i];
            } else if (asteroids[j-1] == -asteroids[i]) {
                j--;
            }

        }

        return Arrays.copyOf(asteroids, j);
    }
}
