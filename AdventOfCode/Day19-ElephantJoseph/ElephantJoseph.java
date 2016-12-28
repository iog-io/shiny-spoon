import java.util.LinkedList;

public class ElephantJoseph
{
    private int mTotal;
    private LinkedList<Integer> L = new LinkedList<>();
    private LinkedList<Integer> M = new LinkedList<>();
    public ElephantJoseph(int _total)
    {
        mTotal = _total;
        int half = mTotal/2;
        for (int i = 1; i < half; ++i) L.add(i);
        for (int i = half; i <= _total; ++i) M.add(i);
    }
    public void solve()
    {
		int diff;
        do {
            M.addLast(L.pop());
            
            diff = mTotal/2 - L.size();
			assert diff >= 0;
			
			if (diff > 0) {
				while (diff-- > 1) {
					L.addLast(M.pop());
				}
				M.pop();
			}
			else {
				L.removeLast();
			}
			
            if (L.isEmpty()) {
                L = M;
                M = new LinkedList<>();
            }
		} while (--mTotal > 1);		
		System.out.println("The dang winner is: "+L.pop());
    }
    public static void main(String[] args)
    {
		final int elves = 3001330;
		new ElephantJoseph(elves).solve();
    }

}
