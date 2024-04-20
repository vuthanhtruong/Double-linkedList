package DNode;

public class DoubleList {
	DNode Phead;

    public DoubleList() {
        Phead = null;
    }

    public void Initialize() {
        Phead = null;
    }

    public DNode CreateNode(int d) {
        return new DNode(d);
    }

    public void PrintList() {
        if (Phead == null) {
            System.out.println("Null");
        } else {
            DNode Node = Phead;
            while (Node != null) {
                System.out.print(Node.data + " ");
                Node = Node.PPhai;
            }
        }
    }

    public int Size() {
        DNode Node = Phead;
        int count = 0;
        while (Node != null) {
            count++;
            Node = Node.PPhai;
        }
        return count;
    }

    public void InsertFirst(int d) {
        DNode Node = CreateNode(d);
        if (Phead == null) {
            Phead = Node;
        } else {
            Node.PPhai = Phead;
            Phead.PTrai = Node;
            Phead = Node;
        }
    }

    public void InsertLast(int d) {
        DNode Node = CreateNode(d);
        DNode pTmp = Phead;
        if (Phead == null) {
            Phead = Node;
        } else {
            while (pTmp.PPhai != null) {
                pTmp = pTmp.PPhai;
            }
            pTmp.PPhai = Node;
            Node.PTrai = pTmp;
        }
    }

    public void InsertIndex(int d, int pos) {
        if (pos < 0 || pos > Size() - 1) {
            System.out.println("Error");
        } else if (pos == 0) {
            InsertFirst(d);
        } else if (pos == Size() - 1) {
            InsertLast(d);
        } else {
            DNode Node = CreateNode(d);
            DNode truoc = Phead;
            DNode sau = null;
            int i = 0;
            while (i < pos) {
                sau = truoc;
                truoc = truoc.PPhai;
                i++;
            }
            sau.PPhai = Node;
            Node.PTrai = sau;
            Node.PPhai = truoc;
            truoc.PTrai = Node;
        }
    }

    public void RemoveIndex(int pos) {
        if (Phead == null) {
            System.out.println("If you don't have any Node, why delete it?");
        } else if (pos == 0) {
            DNode Node = Phead;
            Phead = Phead.PPhai;
            if (Phead != null) {
                Phead.PTrai = null;
            }
            Node = null;
        } else if (pos == Size() - 1) {
            DNode NodeCanXoa = Phead;
            while (NodeCanXoa.PPhai != null) {
                NodeCanXoa = NodeCanXoa.PPhai;
            }
            DNode sau = NodeCanXoa.PTrai;
            sau.PPhai = null;
            NodeCanXoa = null;
        } else if (pos > 0 && pos < Size() - 1) {
            DNode NodeCanXoa = Phead;
            int i = 0;
            while (i < pos) {
                NodeCanXoa = NodeCanXoa.PPhai;
                i++;
            }
            DNode truoc = NodeCanXoa.PTrai;
            DNode sau = NodeCanXoa.PPhai;
            truoc.PPhai = sau;
            sau.PTrai = truoc;
            NodeCanXoa = null;
        } else {
            System.out.println("Error");
        }
    }
    
    public void MergeSort() {
        if (Phead != null) {
            Phead = mergeSort(Phead);
        }
    }

    private DNode mergeSort(DNode head) {
        if (head == null || head.PPhai == null) {
            return head;
        }
        
        // Tìm điểm giữa của danh sách
        DNode middle = getMiddle(head);
        DNode nextToMiddle = middle.PPhai;
        
        // Ngắt danh sách thành hai nửa
        middle.PPhai = null;
        
        // Gọi đệ quy cho cả hai nửa
        DNode left = mergeSort(head);
        DNode right = mergeSort(nextToMiddle);
        
        // Hợp nhất hai danh sách đã sắp xếp thành một
        return merge(left, right);
    }

    private DNode merge(DNode left, DNode right) {
        DNode result = null;

        // Cơ sở: nếu một trong hai danh sách rỗng
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        // Chọn node đầu tiên từ một trong hai danh sách
        if (left.data <= right.data) {
            result = left;
            result.PPhai = merge(left.PPhai, right);
            result.PPhai.PTrai = result;
        } else {
            result = right;
            result.PPhai = merge(left, right.PPhai);
            result.PPhai.PTrai = result;
        }

        return result;
    }

    private DNode getMiddle(DNode head) {
        if (head == null) {
            return head;
        }

        DNode slow = head;
        DNode fast = head;

        // Dùng phương pháp two pointers để tìm điểm giữa
        while (fast.PPhai != null && fast.PPhai.PPhai != null) {
            slow = slow.PPhai;
            fast = fast.PPhai.PPhai;
        }

        return slow;
    }
    public void DoubleSort() {
        if (Phead != null) {
            Phead = doubleSort(Phead);
        }
    }

    private DNode doubleSort(DNode head) {
        if (head == null || head.PPhai == null) {
            return head;
        }

        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            DNode current = head;
            
            // Duyệt qua danh sách
            while (current.PPhai != null) {
                if (current.data > current.PPhai.data) {
                    // Nếu thứ tự không đúng, hoán đổi giá trị
                    int temp = current.data;
                    current.data = current.PPhai.data;
                    current.PPhai.data = temp;
                    sorted = false;
                }
                current = current.PPhai;
            }
        }

        return head;
    }
    

}
