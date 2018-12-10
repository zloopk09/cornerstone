package top.zloop.mobile.biz.article.websocket;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import top.zloop.mobile.biz.article.websocket.data.Message;

public class MessageSocket {

    private final String TAG = MessageSocket.class.getSimpleName();

    public static final String EVENT_NEW_MESSAGE = "new message";

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
        mSocket.off(EVENT_NEW_MESSAGE,onNewMessage);
    }

    public MessageSocket registerMessageLisenner(OnSocketIOMessageEventListener listener){
        mOnSocketIOMessageEventListener = listener;
        mSocket.on(EVENT_NEW_MESSAGE,onNewMessage);
        return this;
    }


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
