package assn03;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        LinkedList list2 = new LinkedList<Integer>();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list.merge(list2);



        System.out.println(list);
    }
}
