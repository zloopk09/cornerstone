package top.zloop.mobile.biz.article;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import top.zloop.mobile.biz.article.websocket.WebsocketActivity;


public class ArticlesFragment extends Fragment {

    private Button websocketbtn;

    public static ArticlesFragment newInstance() {
        ArticlesFragment fragment = new ArticlesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_articles, container, false);

        websocketbtn = (Button) view.findViewById(R.id.websocketbtn);

        websocketbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticlesFragment.this.getContext(), WebsocketActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
