package Simulation;

import GraphComponent.Edge;
import GraphComponent.Vertex;
import map.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepthFirst {
    //the id is array
    private static Graph G;
    private static int C;
    private static double tourCost;

    public static void run(Graph G, int C) {
        tourCost = 0;
        DepthFirst.G = G;
        DepthFirst.C = C;
        System.out.println("---DF Search---\n");
        long start = System.nanoTime();
        //String result = search();
        long end = System.nanoTime();
        System.out.println("Tour Cost: " + tourCost);
        //System.out.println(result);
        System.out.println("Execution time: " + (double) (end - start) * Math.pow(10, -6) + "ms\n");
        int[] vertexID = new int[G.size()-1];

        for(int i=1;i<G.size();i++){
            vertexID[i-1] = i;
        }
        List<List<Integer>> result1 = new DepthFirst().permute(vertexID);
        result1 = removeDuplicates(result1);
        System.out.println("\nPossible permutations of the said array:");
        result1.forEach(System.out::println);


    }


    //permutation of all integer(ID)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Permutation(0, nums, result);
        return result;
    }
    private void Permutation(int i, int[] nums, List<List<Integer>> result) {
        if (i == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            int currentCapacity = 0;
            for (int j=0;j< nums.length;j++){
                int ID = nums[j]; // will store the ID
                currentCapacity+= G.getVertex(ID).capacity;
                if(currentCapacity<C) list.add(ID);
                else{
                    currentCapacity-=G.getVertex(ID).capacity;
                }
            }
            result.add(list);

        } else {
            for (int j = i, l = nums.length; j < l; j++) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                Permutation(i + 1, nums, result);
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }
    }
    public static List<List<Integer>> removeDuplicates(List<List<Integer>> list)
    {

        // Create a new ArrayList
        List<List<Integer>> newList = new ArrayList<>();

        // Traverse through the first list
        for (List<Integer> element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }

}
