package top.zloop.mobile.biz.article.websocket;

import android.util.Log;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URISyntaxException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class SocketIOClient {

    private static final String TAG = "SocketIOClient";

    private static SocketIOClient instance;

    public static final String SOCKET_URL = "wss://api.huobi.pro/ws";
    private Socket mSocket;

    public static final String EVENT_JOIN_ROOM = "join_room";
    public static final String EVENT_NOTIFY = "notify";

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
            opts.query = "scanId=a3f453c91f4bb12ddfd963a277670c33";
            opts.transports = new String[]{"websocket"};
            opts.timeout = 10 * 1000;
//            opts.reconnection = true;
//            opts.reconnectionAttempts = 5;
//            opts.reconnectionDelayMax = 1000; //重连等待时间
            opts.forceNew = true;
            opts.hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            mSocket = IO.socket(SOCKET_URL, opts);

            mSocket.on(Socket.EVENT_CONNECT,onConnect);
            mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
            mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
            mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
            mSocket.on(EVENT_JOIN_ROOM, onJoinRoom);
            mSocket.on(EVENT_NOTIFY, onNotify);
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
        mSocket.off(EVENT_JOIN_ROOM, onJoinRoom);
        mSocket.off(EVENT_NOTIFY, onNotify);
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(TAG, "connected");
            Log.d(TAG, "mSocket.id()?:"+mSocket.id());
            Log.d(TAG, "mSocket.connected()?:"+mSocket.connected());
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(TAG, "diconnected");
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
            Log.d(TAG, data.toString());


        }
    };

    private Emitter.Listener onNotify = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            Log.d(TAG, data.toString());


        }
    };


}
