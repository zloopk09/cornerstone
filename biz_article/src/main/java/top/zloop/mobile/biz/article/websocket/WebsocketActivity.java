package top.zloop.mobile.biz.article.websocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import top.zloop.mobile.biz.article.R;

public class WebsocketActivity extends AppCompatActivity {

    private static final String TAG = "WebsocketActivity";

    private ListView mMessagesView;
    private EditText mInputMessageView;
    private ImageButton sendButton;
    private Button connectButton;
    private Button disconnectButton;
    private ArrayAdapter<String> mAdapter;

    private List<String> mMessages;

    public static final String EVENT_ADD_USER = "add user";
    public static final String EVENT_NEW_MESSAGE = "new message";
    public static final String EVENT_USER_JOINED = "user joined";
    public static final String EVENT_USER_LEFT = "user left";


//    public static final String EVENT_JOIN_ROOM = "join_room";
//    public static final String EVENT_NOTIFY = "notify";

    private Socket mSocket=SocketIOClient.getInstance().getSocket();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_websocket);

        mMessagesView = findViewById(R.id.messages);

        mMessages = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                mMessages);
        mMessagesView.setAdapter(mAdapter);

        mInputMessageView =  findViewById(R.id.message_input);
        connectButton =  findViewById(R.id.connect_button);
        disconnectButton =  findViewById(R.id.disconnect_button);
        sendButton =  findViewById(R.id.send_button);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketIOClient.getInstance().setOnConnectListener(new OnConnectListener() {
                    @Override
                    public void onConnect() {
                        mSocket.emit(EVENT_ADD_USER, "test-robot");
                    }

                    @Override
                    public void onDisconnect() {

                    }

                    @Override
                    public void onConnectTimeout() {

                    }

                    @Override
                    public void onConnectError() {

                    }
                }).connect();

                mSocket.on(EVENT_NEW_MESSAGE, onNewMessage);
                mSocket.on(EVENT_USER_JOINED, onUserJoined);
                mSocket.on(EVENT_USER_LEFT, onUserLeft);
            }
        });

        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketIOClient.getInstance().destory();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mSocket.connected()) return;
                String message = mInputMessageView.getText().toString().trim();
                if (TextUtils.isEmpty(message)) {
                    mInputMessageView.requestFocus();
                    return;
                }
                mInputMessageView.setText("");
//                addMessage(mUsername, message);
                mSocket.emit(EVENT_NEW_MESSAGE, message);
            }
        });

    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                        return;
                    }

                    addMessage(username, " says: "+message);
                }
            });
        }
    };

    private Emitter.Listener onUserJoined = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    int numUsers;
                    try {
                        username = data.getString("username");
                        numUsers = data.getInt("numUsers");
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                        return;
                    }

                    addMessage(username, " joined, current users:"+numUsers);
                }
            });
        }
    };

    private Emitter.Listener onUserLeft = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    int numUsers;
                    try {
                        username = data.getString("username");
                        numUsers = data.getInt("numUsers");
                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                        return;
                    }

                    addMessage(username, " left, current users:"+numUsers);
                }
            });
        }
    };

    private void addMessage(String username, String message) {
        mMessages.add(username+" "+message);
        mAdapter.notifyDataSetChanged();
        mMessagesView.setSelection(mMessagesView.getBottom());
    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mSocket.disconnect();
//        mSocket.off();
    }

}
