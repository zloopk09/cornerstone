package top.zloop.mobile.biz.article.websocket;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import top.zloop.mobile.biz.article.websocket.data.Message;

public class RoomSocket {

    private final String TAG = this.getClass().getSimpleName();

    public static final String EVENT_USER_JOINED = "user joined";
    public static final String EVENT_USER_LEFT = "user left";

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
        mSocket.off(EVENT_USER_JOINED,onUserJoined);
        mSocket.off(EVENT_USER_LEFT,onUserLeft);
    }


    public RoomSocket registerRoomLisenner(OnSocketIORoomEventListener listener){
        mOnSocketIORoomEventListener = listener;
        mSocket.on(EVENT_USER_JOINED,onUserJoined);
        mSocket.on(EVENT_USER_LEFT,onUserLeft);
        return this;
    }


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
