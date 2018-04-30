package temp;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.operation.common.Message;
import com.operation.common.Worker;
import com.operation.mainframe.MainFrame;
import com.operation.rpc.RPCHelper;

public class TestMessage {
	public static void main(String[] args) {
		RPCHelper helper = null;
		Worker worker = null;
		JFrame f = null;

		helper = new RPCHelper();
		helper.login("w0001", "123");
		worker = helper.selectWorkerById("w0001");
		f=new MainFrame(helper,worker);
		//发送消息,消息类型见Message类定义的int参数
		helper.sendMessage("w1001", "o0001", Message.CHOOSE);
	}
}
