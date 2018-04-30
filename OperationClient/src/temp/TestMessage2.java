package temp;

import javax.swing.JFrame;

import com.operation.common.Message;
import com.operation.common.Worker;
import com.operation.mainframe.MainFrame;
import com.operation.rpc.RPCHelper;

public class TestMessage2 {
	public static void main(String[] args) {
		RPCHelper helper = null;
		Worker worker = null;
		JFrame f = null;

		helper = new RPCHelper();
		helper.login("w1001", "123");
		worker = helper.selectWorkerById("w1001");
		f=new MainFrame(helper,worker);
		
	}
}