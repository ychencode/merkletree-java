import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: shadows
 * @date 2018/9/13 上午10:38
 */
public class MerkleTrees {
	
	// transactions list
	private List<String> txList;
	// Merkle root;
	private String root;
	
	public List<String> getTxList() {
		return txList;
	}
	
	public void setTxList(List<String> txList) {
		this.txList = txList;
	}
	
	public void setRoot(String root) {
		this.root = root;
	}
	
	/**
	 * constructor
	 * @param txList
	 */
	public MerkleTrees(List<String> txList) {
		this.txList = txList;
		this.root = "";
	}
	
	/**
	 * execute merkle_tree and set root
	 */
	public void merkle_tree() {
		List<String> tempTxList = new ArrayList<>();
		for (int i = 0; i < txList.size(); i++) {
			// copy the txList
			tempTxList.add(txList.get(i));
		}
		List<String> newTxList = getNewTxList(tempTxList);
		while (newTxList.size() != 1) {
			newTxList = getNewTxList(newTxList);
		}
		this.root = newTxList.get(0);
	}
	
	private List<String> getNewTxList(List<String> tempTxList) {
		List<String> newTxList = new ArrayList<>();
		int index = 0;
		while (index < tempTxList.size()) {
			// left
			String left = tempTxList.get(index);
			index++;
			// right
			String right = "";
			if (index != tempTxList.size()) {
				right = tempTxList.get(index);
			}
			// sha2 hex value
			String sha2HexValue = getSHA2HexValue(left + right);
			newTxList.add(sha2HexValue);
			index++;
		}
		return newTxList;
	}
	
	/**
	 * Return hex value string
	 * @param str
	 * @return
	 */
	private String getSHA2HexValue(String str) {
		byte[] cipher_byte;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			cipher_byte = md.digest();
			StringBuilder stringBuilder = new StringBuilder(2 * cipher_byte.length);
			for (byte b : cipher_byte) {
				stringBuilder.append(String.format("%02x", b & 0xff));
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Get Merkle root
	 * @return
	 */
	public String getRoot() {
		return this.root;
	}
}
