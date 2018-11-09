package top.zloop.mobile.biz.article.websocket;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class SocketIOClient {

    private static final String TAG = "websocket-SocketIOClient";

    public static final String SOCKET_URL = "https://socket-io-chat.now.sh/";

    private static SocketIOClient instance;
    private static Socket mSocket;
    static {
        try {
            IO.Options opts = new IO.Options();
//                opts.path = "/notify";
//                opts.query = "scanId=a3f453c91f4bb12ddfd963a277670c33";
            opts.transports = new String[]{"websocket"};
            opts.timeout = 10 * 1000;
//                opts.reconnection = true;
//                opts.reconnectionAttempts = 5;
//                opts.reconnectionDelayMax = 1000; //重连等待时间
//                opts.secure = true;
//                opts.forceNew = true;
//                opts.hostnameVerifier = new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return true;
//                    }
//                };
//                mSocket = IO.socket(SOCKET_URL);
            mSocket = IO.socket(SOCKET_URL, opts);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private SocketIOClient() {}

    public static SocketIOClient getInstance() {
        if (instance == null) {
            instance = new SocketIOClient();
        }
        return instance;
    }

    public static Socket getSocket(){
        return mSocket;
    }

}
