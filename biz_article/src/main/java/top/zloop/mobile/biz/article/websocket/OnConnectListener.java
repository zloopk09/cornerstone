package top.zloop.mobile.biz.article.websocket;

public interface OnConnectListener {


    void onConnect();

    void onDisconnect();

    void onConnectTimeout();

    void onConnectError();
}
