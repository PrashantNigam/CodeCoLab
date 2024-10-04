package Hashing;

import java.util.HashMap;
import java.util.Map;

public class DividePlayersIntoTeamsOfEqualSkill {
    public static void main(String[] args) {
        var ob = new DividePlayersIntoTeamsOfEqualSkill();
        System.out.println(ob.dividePlayers(new int[]{3, 2, 5, 1, 3, 4}) == 22);
        System.out.println(ob.dividePlayers(new int[]{3, 4}) == 12);
        System.out.println(ob.dividePlayers(new int[]{1, 1, 2, 3}) == -1);
    }

    public long dividePlayers(int[] skills) {
        var skillCount = new HashMap<Integer, Integer>();
        var teamSkill = getTeamSkill(skills, skillCount);

        if (teamSkill == -1)
            return -1;

        return getSumOfChemistry(skills, skillCount, teamSkill);
    }

    /**
     * @param skills     skills of players
     * @param skillCount a map that counts the occurrences of each skill in {@code skills}
     *                   The keys are skill levels, and the values are the counts of those skills.
     * @return the average skill level of each team if the total skill is divisible by the number
     * of team members; otherwise, returns -1.
     */
    private int getTeamSkill(int[] skills, Map<Integer, Integer> skillCount) {
        var totalSkill = 0;
        var teamCount = skills.length >> 1;

        for (var skill : skills) {
            totalSkill += skill;
            skillCount.compute(skill, (_, v) -> v == null ? 1 : ++v);
        }

        return totalSkill % teamCount == 0
             ? totalSkill / teamCount
             : -1;
    }

    private long getSumOfChemistry(int[] skills, HashMap<Integer, Integer> skillCount, int teamSkill) {
        var sumOfChemistry = 0L;

        for (var skill : skills) {
            if (!decrement(skillCount, skill))
                continue;// skill already paired

            var targetSkill = teamSkill - skill;

            if (!decrement(skillCount, targetSkill))
                return -1; // no pairable skill found

            sumOfChemistry += skill * targetSkill;
        }

        return sumOfChemistry;
    }

    /**
     * Decreases the value of {@code key} by 1 in {@code map}.
     * If the value becomes zero, {@code key} is removed from {@code map}.
     *
     * @param <U> the type of keys maintained by the map
     * @param map the map containing the key-value pairs
     * @param key the key whose count is to be decremented
     * @return {@code true} if the key existed and was decremented successfully,
     * {@code false} if the key did not exist in the map
     */
    private <U> boolean decrement(Map<U, Integer> map, U key) {
        if (!map.containsKey(key))
            return false;
        if (map.compute(key, (_, v) -> --v) == 0)
            map.remove(key);
        return true;
    }
}
