package top.zloop.mobile.biz.article.websocket;

import android.util.Log;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class SocketIOClient {

    private static final String TAG = "SocketIOClient";

    private static SocketIOClient instance;

    public static final String SOCKET_URL = "wss://api.huobi.pro/ws";
    private Socket mSocket;

    private SocketIOClient() {
    }

    public static SocketIOClient getInstance() {
        if (instance == null) {
            instance = new SocketIOClient();
        }
        return instance;
    }

    public Socket getmSocket(){
        return mSocket;
    }

    public void onConnect() {
        try {
            IO.Options opts = new IO.Options();
            opts.path = "/notify";
            opts.transports = new String[]{"websocket"};

            opts.reconnection = true;// 是否自动重新连接
            opts.reconnectionAttempts = 10; //重新连接尝试次数
//            opts.reconnectionDelayMax = 1000; //重新连接之间最长等待时间
//            opts.timeout = 20000; //connect_error和connect_timeout事件发出之前的等待时间

            mSocket = IO.socket(SOCKET_URL, opts);

            mSocket.on(Socket.EVENT_CONNECT,onConnect);
            mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
            mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
            mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
            mSocket.on("join_room", onJoinRoom);
            mSocket.on("notify", onNotify);
            mSocket.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void onDestory() {
        mSocket.disconnect();

        mSocket.off(Socket.EVENT_CONNECT, onConnect);
        mSocket.off(Socket.EVENT_DISCONNECT, onDisconnect);
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.off("join_room", onJoinRoom);
        mSocket.off("notify", onNotify);
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.i(TAG, "connected");
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.i(TAG, "diconnected");
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e(TAG, "connecting error");
        }
    };

    private Emitter.Listener onJoinRoom = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONArray data = (JSONArray) args[0];
            Log.e(TAG, data.toString());


        }
    };

    private Emitter.Listener onNotify = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            Log.e(TAG, data.toString());


        }
    };


}
