package top.zloop.mobile.biz.article.websocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

import top.zloop.mobile.biz.article.R;

public class WebsocketActivity extends AppCompatActivity {



    private Socket mSocket;
    private static final String URL = "http://yoururl.com";
    {
        try {
            mSocket = IO.socket(URL);
        } catch (URISyntaxException e) {}
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_websocket);

        mSocket.connect();

    }



}
