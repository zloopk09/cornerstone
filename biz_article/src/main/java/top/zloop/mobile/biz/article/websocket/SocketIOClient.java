package top.zloop.mobile.biz.article.websocket;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class SocketIOClient {

    private final String TAG = this.getClass().getSimpleName();

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

    public static final String EVENT_ADD_USER = "add user";
    private String username="test-robot";

    private OnSocketIOConnectListener mOnSocketIOConnectListener;

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

    public Socket connect(OnSocketIOConnectListener listener){
        mOnSocketIOConnectListener = listener;
        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectTimeout);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        return mSocket;
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(TAG, "connected");
            Log.d(TAG, "mSocket.id()?:"+mSocket.id());
            Log.d(TAG, "mSocket.connected()?:"+mSocket.connected());
            mSocket.emit(EVENT_ADD_USER, username);
            if (mOnSocketIOConnectListener != null) mOnSocketIOConnectListener.onConnect();
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(TAG, "diconnected");
            if (mOnSocketIOConnectListener != null) mOnSocketIOConnectListener.onDisconnect();
        }
    };

    private Emitter.Listener onConnectTimeout = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e(TAG, "connecting timeout");
            if (mOnSocketIOConnectListener != null) mOnSocketIOConnectListener.onConnectTimeout();
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e(TAG, "connecting error");
            if (mOnSocketIOConnectListener != null) mOnSocketIOConnectListener.onConnectError();
        }
    };

    public void disconnect() {
        mSocket.disconnect();
    }
}
