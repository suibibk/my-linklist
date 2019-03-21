package cn.myforever.mylist;

/**
 * 手写linkedlist
 * 原理：双向链表
 * @author forever
 *
 * @param <E>
 */
public class MyLinkedList<E> {
	/**
	 * 定义一个静态内部类做节点
	 * @author forever
	 *
	 * @param <E>
	 * 
	 */
	private static class Node<E>{
		E e;
		Node<E> next;
		Node<E> prev;
		Node(Node<E> prev,E e,Node<E> next){
			this.e = e;
			this.next  = next;
			this.prev = prev;
		} 
	}
	
	//定义第一个节点的引用,作用是用于查询，跟第一个节点一起指向第一个节点的堆地址
	private Node<E> first;
	//定义第最后一个节点的引用,作用是用于插入，跟第最后节点一起指向最后一个节点的堆地址
	private Node<E> last;
	private int size;//大小
	/**
	 * 添加节点都是在最后一个节点添加
	 * @param e
	 * @return
	 */
	public Boolean add(E e) {
		//获取最后一个节点
		final Node<E> l = last;
		//添加节点都是在最后一个节点添加的，所以该节点的上一个节点就是最后一个节点，下一个节点为null
		final Node<E> newNode = new Node<E>(l, e, null);
		//把新节点变为最后一个节点
		last  = newNode;
		if(l==null) {
			//这里就表明还没有开始和结束节点，这个节点就是第一个节点
			first = newNode;
		}else {
			//这里就表明已经有节点了，那么这个就是最后一个节点
			l.next = newNode;
		}
		size++;
		return true;
	}
	public Boolean add(int index,E e) {
		checkIndex(index);
		//然后获取这个位置的及诶单
		Node<E> node = getNode(index);
		if(index == size) {
			//在最后一个插入即可
			add(e);
		}else {
			//插入在node之前
			final Node<E> prev = node.prev;
			final Node<E> newNode = new Node<E>(prev, e, node);
			node.prev=newNode;
			if(prev==null) {
				first = newNode;
			}else{
				prev.next = newNode;
			}
		}
		size++;
		return true;
	}
	/**
	 * 获取集合的大小
	 * @return
	 */
	public int size() {
		return size;
	}
	private void checkIndex(int index) {
		if(index>=size||index<0) {
			throw new IndexOutOfBoundsException("下标越界："+index);
		}
	}
	/**
	 * 获取值，这个方法相对于ArrayList来就比较慢了哦
	 * @param index
	 * @return
	 */
	public E get(int index) {
		//检查有没有越界
		checkIndex(index);
		Node<E> node = getNode(index);
		return node.e;
	}
	/**
	 * 移除
	 * @param index
	 * @return
	 */
	public E remove(int index) {
		//先判断越界
		checkIndex(index);
		//获取这一个节点
		//查询只能先从first开始查起
		final Node<E> node =getNode(index);
		final E e = node.e;
		final Node<E> next = node.next;
		final Node<E> prev =node.prev;
		//如果上一个节点为null，就表明是第一个节点
		if(prev==null) {
			//指定下一个节点为第一个节点即可
			first =next;
		}else{
			prev.next=next;
			node.prev =null;
		}
				
		if(next==null) {
			last = prev;
		}else {
			next.prev=prev;
			node.next=null;
		}
		node.e=null;
		size--;
		return e;
	}
	/**
	 * 移除元素这个方法因为不知到下标，效率太低，只能从一开始找起
	 * @param e
	 */
	public void remove(E e) {
		//获取元素下标
		int index = -1;
		Node<E> x = first;
		if(x!=null) {
			if(e.equals(x.e)) {
				index = 0;
			}
		}
		for(int i=0;i<size-1;i++) {
			x =x.next;
			if(e.equals(x.e)) {
				index =i+1;
				break;
			}
		}
		//这里就获取了下标，直接移除即可
		if(index!=-1) {
			remove(index);
		}
	}
	
	/**
	 * 根据下标去获取
	 * @param index
	 * @return
	 */
	private Node<E> getNode(int index){
		//简单的话就只需要从最开始查找即可，但是这样的话如果要查找的内容在最后一个，可能会效率很低
		//所以这里按位置来判断查找的方向
		if(index<(size>>1)) {
			Node<E> x = first;
			//因为用的是next，所以这里就直接index
			for (int i = 0; i <index; i++) {
				x = x.next;
			}
			return x;
		}else {
			Node<E> x = last;
			for (int i = size - 1; i > index; i--)
	                x = x.prev;
	        return x;
		}
	}
	//清除
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.e = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }
	@Override
	public String toString() {
		String str = "";
		Node<E> x = first;
		str=x.e+";";
		//因为是next，所以就用size-1
		for (int i = 0; i <size-1; i++) {
			x = x.next;
			str+=x.e+";";
		}
		System.out.println(str);
		return "";
	}
}
