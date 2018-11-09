package top.zloop.mobile.biz.article.websocket;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import top.zloop.mobile.biz.article.websocket.data.Message;

public class SocketIOClient {

    private final String TAG = this.getClass().getSimpleName();

    private static SocketIOClient instance;

    public static final String SOCKET_URL = "https://socket-io-chat.now.sh/";

    public static final String EVENT_NEW_MESSAGE = "new message";
    public static final String EVENT_USER_JOINED = "user joined";
    public static final String EVENT_USER_LEFT = "user left";

    private Socket mSocket;

    private OnSocketIOConnectListener mOnSocketIOConnectListener;
    private OnSocketIOMessageEventListener mOnSocketIOMessageEventListener;
    private OnSocketIORoomEventListener mOnSocketIORoomEventListener;

    private SocketIOClient() {
        if(mSocket==null){
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
    }

    public static SocketIOClient getInstance() {
        if (instance == null) {
            instance = new SocketIOClient();
        }
        return instance;
    }

    public Socket getSocket(){
        return mSocket;
    }

    public SocketIOClient connect() {
        mSocket.connect();
        return this;
    }

    public boolean isConnected(){
        if(mSocket==null){
            return false;
        }
        return mSocket.connected();
    }

    public void disconnect() {
        mSocket.disconnect();
        mSocket.off();
    }

    public SocketIOClient registerConnectLisenner(OnSocketIOConnectListener listener){
        mOnSocketIOConnectListener = listener;
        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectTimeout);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        return this;
    }

    public void unregisterConnectLisenner(){
        if (mOnSocketIOConnectListener != null) {
            mSocket.off(Socket.EVENT_CONNECT,onConnect);
            mSocket.off(Socket.EVENT_DISCONNECT,onDisconnect);
            mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectTimeout);
            mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        }
    }

    public SocketIOClient registerMessageLisenner(OnSocketIOMessageEventListener listener){
        mOnSocketIOMessageEventListener = listener;
        mSocket.on(EVENT_NEW_MESSAGE,onNewMessage);
        return this;
    }

    public void unregisterMessageLisenner(){
        if (mOnSocketIOMessageEventListener != null) {
            mSocket.off(EVENT_NEW_MESSAGE,onNewMessage);
        }
    }

    public SocketIOClient registerRoomLisenner(OnSocketIORoomEventListener listener){
        mOnSocketIORoomEventListener = listener;
        mSocket.on(EVENT_USER_JOINED,onUserJoined);
        mSocket.on(EVENT_USER_LEFT,onUserLeft);
        return this;
    }

    public void unregisterRoomLisenner(){
        if (mOnSocketIORoomEventListener != null) {
            mSocket.off(EVENT_USER_JOINED,onUserJoined);
            mSocket.off(EVENT_USER_LEFT,onUserLeft);
        }
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(TAG, "connected");
            Log.d(TAG, "mSocket.id()?:"+mSocket.id());
            Log.d(TAG, "mSocket.connected()?:"+mSocket.connected());
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


    public void sendMessage(final Object... args){
        if(mSocket==null){
            return;
        }
        mSocket.emit(EVENT_NEW_MESSAGE,args);
    }


    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];

            String title;
            String content;
            try {
                title = data.getString("username");
                content = data.getString("message");
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
                return;
            }
            Message message=new Message();
            message.setTitle(title);
            message.setContent(" says: "+content);
            if (mOnSocketIOMessageEventListener != null) {
                mOnSocketIOMessageEventListener.onNewMessage(message);
            }
        }
    };

    private Emitter.Listener onUserJoined = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];

            String title;
            String content;
            try {
                title = data.getString("username");
                content = data.getString("numUsers");
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
                return;
            }
            Message message=new Message();
            message.setTitle(title);
            message.setContent(" joined, current users:"+content);
            if (mOnSocketIORoomEventListener != null) {
                mOnSocketIORoomEventListener.onUserJoined(message);
            }

        }
    };

    private Emitter.Listener onUserLeft = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            JSONObject data = (JSONObject) args[0];

            String title;
            String content;
            try {
                title = data.getString("username");
                content = data.getString("numUsers");
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
                return;
            }
            Message message=new Message();
            message.setTitle(title);
            message.setContent(" left, current users:"+content);
            if (mOnSocketIORoomEventListener != null) {
                mOnSocketIORoomEventListener.onUserLeft(message);
            }

        }
    };

}
