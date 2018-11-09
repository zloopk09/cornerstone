package top.zloop.mobile.biz.article.websocket;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import top.zloop.mobile.biz.article.websocket.data.Message;

public class RoomSocket {

    private static final String TAG = "websocket";

    public static final String EVENT_USER_JOINED = "user joined";
    public static final String EVENT_USER_LEFT = "user left";

    private OnSocketIOConnectListener mOnSocketIOConnectListener;
    private OnSocketIORoomEventListener mOnSocketIORoomEventListener;

    private static Socket mSocket;

    public RoomSocket() {
        mSocket=SocketIOClient.getSocket();
    }

    public void connect() {
        mSocket.connect();
    }

    public boolean isConnected(){
        if(mSocket==null){
            return false;
        }
        return mSocket.connected();
    }

    public void disconnect() {
        mSocket.disconnect();
    }

    public void off() {
        mSocket.off(Socket.EVENT_CONNECT,onConnect);
        mSocket.off(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectTimeout);
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.off(EVENT_USER_JOINED,onUserJoined);
        mSocket.off(EVENT_USER_LEFT,onUserLeft);
    }

    public RoomSocket registerConnectLisenner(OnSocketIOConnectListener listener){
        mOnSocketIOConnectListener = listener;
        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectTimeout);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        return this;
    }

    public RoomSocket registerRoomLisenner(OnSocketIORoomEventListener listener){
        mOnSocketIORoomEventListener = listener;
        mSocket.on(EVENT_USER_JOINED,onUserJoined);
        mSocket.on(EVENT_USER_LEFT,onUserLeft);
        return this;
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
