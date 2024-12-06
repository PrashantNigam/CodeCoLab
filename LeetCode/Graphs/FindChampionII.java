package Graphs;

public class FindChampionII {
    public static void main(String[] args) {
        var ob = new FindChampionII();
        System.out.println(ob.findChampion(3, new int[][]{{0, 1}, {1, 2}}) == 0);
        System.out.println(ob.findChampion(4, new int[][]{{0, 2}, {1, 3}, {1, 2}}) == -1);
    }

    public int findChampion(int n, int[][] edges) {
        var champion = -1;
        var championCount = 0;
        var inDegrees = initInDegrees(n, edges);

        for (var i = 0; i < n; i++) {
            if (inDegrees[i] > 0)
                continue;
            if (++championCount > 1)
                return -1;
            champion = i;
        }

        return champion;
    }

    private int[] initInDegrees(int n, int[][] edges) {
        var inDegrees = new int[n];
        Arrays.stream(edges)
              .forEach(edge -> inDegrees[edge[1]]++);        
        return inDegrees;
    }
}
