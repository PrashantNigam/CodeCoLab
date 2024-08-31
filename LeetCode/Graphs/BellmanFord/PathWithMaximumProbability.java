package Graphs.BellmanFord;

// Google
public class PathWithMaximumProbability {
    public static void main(String[] args) {
        var ob = new PathWithMaximumProbability();
        System.out.println(ob.maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.2},
                0, 2) == 0.25);
        System.out.println(ob.maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.3},
                0, 2) == 0.3);
        System.out.println(ob.maxProbability(3, new int[][]{{0, 1}}, new double[]{0.5}, 0, 2) == 0);
    }
    
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        var probability = new double[n];
        probability[start] = 1;

        for (var i = n - 2; i >= 0; i--) {
            var probabilityUpdated = false;

            for (var j = 0; j < edges.length; j++) {
                var u = edges[j][0];
                var v = edges[j][1];
                
                if (updateProbability(succProb, probability, u, v, j) 
                 || updateProbability(succProb, probability, v, u, j))
                    probabilityUpdated = true;
            }

            if (!probabilityUpdated)
                break;
        }

        return probability[end];
    }

    private boolean updateProbability(double[] succProb, double[] probability, int from, int to, int j) {
        var newProbability = probability[to] * succProb[j];

        if (probability[from] >= newProbability)
            return false;

        probability[from] = newProbability;
        return true;
    }
}
