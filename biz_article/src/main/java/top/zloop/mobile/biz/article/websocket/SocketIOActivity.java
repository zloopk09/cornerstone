package top.zloop.mobile.biz.article.websocket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.github.nkzawa.socketio.client.Socket;

import java.util.ArrayList;
import java.util.List;

import top.zloop.mobile.biz.article.R;
import top.zloop.mobile.biz.article.websocket.data.Message;

public class SocketIOActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private ListView mMessagesView;
    private EditText mInputMessageView;
    private ImageButton sendButton;
    private Button nextpageButton;
    private ArrayAdapter<String> mAdapter;

    private List<String> mMessages;

    public static final String EVENT_ADD_USER = "add user";
    private String username="test-robot";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_io);

        mMessagesView = findViewById(R.id.messages);

        mMessages = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                mMessages);
        mMessagesView.setAdapter(mAdapter);

        mInputMessageView =  findViewById(R.id.message_input);
        nextpageButton =  findViewById(R.id.nextpage_button);
        sendButton =  findViewById(R.id.send_button);

        nextpageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SocketIOActivity.this, SocketIO2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! SocketIOClient.getInstance().isConnected()) return;
                String message = mInputMessageView.getText().toString().trim();
                if (TextUtils.isEmpty(message)) {
                    mInputMessageView.requestFocus();
                    return;
                }
                mInputMessageView.setText("");
                addMessage(username, message);
                SocketIOClient.getInstance().sendMessage(message);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SocketIOClient.getInstance().registerConnectLisenner(new OnSocketIOConnectListener() {
            @Override
            public void onConnect() {
                SocketIOClient.getInstance().getSocket().emit(EVENT_ADD_USER, username);
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
        }).registerMessageLisenner(new OnSocketIOMessageEventListener() {
            @Override
            public void onNewMessage(final Message message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addMessage(message.getTitle(),message.getContent());
                    }
                });
            }
        }).registerRoomLisenner(new OnSocketIORoomEventListener() {
            @Override
            public void onUserJoined(final Message message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addMessage(message.getTitle(),message.getContent());
                    }
                });
            }

            @Override
            public void onUserLeft(final Message message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addMessage(message.getTitle(),message.getContent());
                    }
                });
            }
        }).connect();

    }

    private void addMessage(String username, String message) {
        mMessages.add(username+" "+message);
        mAdapter.notifyDataSetChanged();
        mMessagesView.setSelection(mMessagesView.getBottom());
    }

    @Override
    protected void onPause() {
        super.onPause();
        SocketIOClient.getInstance().unregisterConnectLisenner();
        SocketIOClient.getInstance().unregisterMessageLisenner();
        SocketIOClient.getInstance().unregisterRoomLisenner();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SocketIOClient.getInstance().disconnect();
    }

}
