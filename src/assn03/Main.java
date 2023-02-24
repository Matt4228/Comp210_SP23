package assn03;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList<Character>();
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');
        list.add('e');

        LinkedList list2 = new LinkedList<Character>();
        list2.add('3');
        list2.add('4');
        list.merge2(list2);



        System.out.println(list);
    }
}
