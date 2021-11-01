public class Exercise9_3 {
    public static void main(String[] args) {
        var start = new ListNode();
        start.item = 0;
        var list = start;

        for (int i = 1; i < 10; i++) {
            list.next = new ListNode();
            list = list.next;
            list.item = i;
        }

        System.out.println("Original order:");
        print(start);
        System.out.println("Reversed order:");
        print(reverse(start));
    }

    private static class ListNode {
        int item;
        ListNode next;
    }

    private static ListNode reverse(ListNode list) {
        ListNode reverse = null;
        var runner = list;

        while (runner != null) {
            var newNode = new ListNode();
            newNode.item = runner.item;
            newNode.next = reverse;
            reverse = newNode;
            runner = runner.next;
        }

        return reverse;
    }

    private static void print(ListNode list) {
        var runner = list;

        while (runner != null) {
            System.out.print(runner.item + " ");
            runner = runner.next;
        }

        System.out.println();
    }
}
