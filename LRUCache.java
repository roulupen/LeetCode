public class LRUCache {	
	class DLinkedNode {
		int key; // This is required while addding new element when the cache is full
		int value; // Storing the actual value
		DLinkedNode prev, next;
	}
	
	DLinkedNode head;
    DLinkedNode tail;
	
	private void addNodeAthead(DLinkedNode node) {
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}
	
	private void removeNode(DLinkedNode node) {
		DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;
		prev.next = next;
		next.prev = prev;
	}
	
	private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNodeAthead(node);
    }
	
	private DLinkedNode popTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
	
	Map<Integer, DLinkedNode> map;
	int capacity;
	
	public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        
        // Head and tail are two dummy nodes all real nodes will be in-between them
        this.head = new DLinkedNode();
        this.tail = new DLinkedNode();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
	
	public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        
        DLinkedNode node = map.get(key);
        moveToHead(node);
        return node.value;
    }
	
	public void put(int key, int value) {
		if(map.containsKey(key)) {
			DLinkedNode node = map.get(key);
			node.value = value;
			moveToHead(node);
		} else {
			DLinkedNode node = new DLinkedNode();
			node.value = value;
            node.key = key;
			
			map.put(key, node);
			addNodeAthead(node);
			
			if(map.size() > capacity) {
				DLinkedNode nodeToEvict = popTail();
                map.remove(nodeToEvict.key);
			}
		}
	}
}
