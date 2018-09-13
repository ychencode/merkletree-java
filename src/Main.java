import java.util.Arrays;
import java.util.List;

/**
 * @author: shadows 
 * @date 2018/9/13 上午10:54
 */
public class Main {
	
	public static void main(String[] args) {
		
		List<String> txList = Arrays.asList("a", "b", "c", "d", "e");
		MerkleTrees merkleTrees = new MerkleTrees(txList);
		merkleTrees.merkle_tree();
		
		System.out.println(merkleTrees.getRoot());
	}
}
