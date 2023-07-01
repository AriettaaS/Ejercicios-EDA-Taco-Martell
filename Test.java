public class Test {
    public static void main(String[] args) {

        System.out.println("=====================================");
        
        // test with big tree
        AVL<Integer> bigAvl = new AVL<Integer>();
        int[] elements = { 100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190 };
        try {
            // adding elements to the tree
            System.out.println("Adding elements to the tree");
            for (int element : elements) {
                bigAvl.insert(element);
            } 
            System.out.println(bigAvl.toString());
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
