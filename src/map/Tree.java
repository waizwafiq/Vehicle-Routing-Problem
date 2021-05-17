package map;

import Simulation.DepthFirst;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private static Graph G;
    private static int C;
    private static double tourCost;
    private static List<List<Integer>> tree;

    public static void run(Graph G, int C) {

        Tree.G = G;
        Tree.C = C;
        System.out.println("---Generating tree---\n");


        int[] vertexID = new int[G.size()-1];
        for(int i=1;i<G.size();i++){
            vertexID[i-1] = i;
        }
        tree = new Tree().permute(vertexID);
        tree = removeDuplicates(tree); //remove any duplicate list
        System.out.println("\nPossible tree : ");
        tree.forEach(System.out::println);





    }

    public static List<List<Integer>> getTree() {
        return tree;
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
