package top.zloop.mobile.biz.article.websocket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SocketIO2Activity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private ListView mMessagesView;
    private EditText mInputMessageView;
    private ImageButton sendButton;
    private Button connectButton;
    private Button disconnectButton;
    private ArrayAdapter<String> mAdapter;

    private List<String> mMessages;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_io2);

        Bundle bundle = this.getIntent().getExtras();
        username = bundle.getString("username");

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
                SocketIOClient.getInstance().registerMessageLisenner(new OnSocketIOMessageEventListener() {
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
        });

        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketIOClient.getInstance().disconnect();
            }
        });


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!SocketIOClient.getInstance().isConnected()) return;
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
        SocketIOClient.getInstance().registerMessageLisenner(new OnSocketIOMessageEventListener() {
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
        SocketIOClient.getInstance().unregisterMessageLisenner();
        SocketIOClient.getInstance().unregisterRoomLisenner();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}