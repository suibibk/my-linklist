package cn.myforever.mylist;

public class Test {

	public static void main(String[] args) {
		//List<String> list = new LinkedList<String>();
		//list.add("a");
		MyLinkedList<String> list = new MyLinkedList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add(2, "E");
		System.out.println(list);
		list.remove(2);
		list.remove("B");
		System.out.println(list);
	}

}
