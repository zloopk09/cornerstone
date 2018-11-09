package top.zloop.mobile.biz.article.websocket;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import top.zloop.mobile.biz.article.websocket.data.Message;

public class MessageSocket {

    private static final String TAG = "websocket-MessageSocket";

    public static final String EVENT_NEW_MESSAGE = "new message";

    public static final String EVENT_ADD_USER = "add user";
    private String username="test-robot";

    private OnSocketIOConnectListener mOnSocketIOConnectListener;
    private OnSocketIOMessageEventListener mOnSocketIOMessageEventListener;

    private static Socket mSocket;

    public MessageSocket() {
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
        mSocket.off(EVENT_NEW_MESSAGE,onNewMessage);
    }

    public MessageSocket registerConnectLisenner(OnSocketIOConnectListener listener){
        mOnSocketIOConnectListener = listener;
        mSocket.on(Socket.EVENT_CONNECT,onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectTimeout);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        return this;
    }

    public MessageSocket registerMessageLisenner(OnSocketIOMessageEventListener listener){
        mOnSocketIOMessageEventListener = listener;
        mSocket.on(EVENT_NEW_MESSAGE,onNewMessage);
        return this;
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


    public void sendMessage(final Object... args){
        if(mSocket==null||!mSocket.connected()){
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


}
