import java.util.*;

public class RetailInventorySystem {

    static final int INF = 99999;

    static String[] locations = {
            "Warehouse",
            "Store A",
            "Store B",
            "Store C",
            "Store D"
    };

    static void dijkstra(int graph[][], int src) {

        int V = graph.length;
        int dist[] = new int[V];
        boolean visited[] = new boolean[V];

        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {

            int min = INF;
            int u = -1;

            for (int j = 0; j < V; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    u = j;
                }
            }

            visited[u] = true;

            for (int v = 0; v < V; v++) {

                if (!visited[v] &&
                        graph[u][v] != 0 &&
                        dist[u] != INF &&
                        dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("\nUsing Dijkstra Algorithm");

        for (int i = 0; i < V; i++)
            System.out.println(locations[src] + " -> " + locations[i] + " = " + dist[i] + " km");
    }

    static void bellmanFord(int edges[][], int V, int E, int src) {

        int dist[] = new int[V];

        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int i = 1; i < V; i++) {

            for (int j = 0; j < E; j++) {

                int u = edges[j][0];
                int v = edges[j][1];
                int w = edges[j][2];

                if (dist[u] != INF && dist[u] + w < dist[v])
                    dist[v] = dist[u] + w;
            }
        }

        System.out.println("\nUsing Bellman-Ford Algorithm");

        for (int i = 0; i < V; i++)
            System.out.println(locations[src] + " -> " + locations[i] + " = " + dist[i] + " km");
    }

    static void floydWarshall(int graph[][]) {

        int V = graph.length;
        int dist[][] = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {

                if (graph[i][j] == 0 && i != j)
                    dist[i][j] = INF;
                else
                    dist[i][j] = graph[i][j];
            }
        }

        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];

        System.out.println("\nAll-Pairs Shortest Distances (Floyd-Warshall)");

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {

                if (dist[i][j] == INF)
                    System.out.print("INF\t");
                else
                    System.out.print(dist[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int graph[][] = {
                {0, 10, 15, 0, 20},
                {10, 0, 35, 25, 0},
                {15, 35, 0, 30, 10},
                {0, 25, 30, 0, 15},
                {20, 0, 10, 15, 0}
        };

        int edges[][] = {
                {0,1,10},
                {0,2,15},
                {0,4,20},
                {1,2,35},
                {1,3,25},
                {2,3,30},
                {2,4,10},
                {3,4,15}
        };

        int V = 5;
        int E = edges.length;

        System.out.println("==========================================");
        System.out.println(" RETAIL INVENTORY ROUTING SYSTEM ");
        System.out.println("==========================================");

        System.out.println("\nWarehouse distributes products to stores.");

        dijkstra(graph, 0);

        bellmanFord(edges, V, E, 0);

        floydWarshall(graph);

        System.out.println("\nInventory Delivery Completed Successfully.");
    }
}