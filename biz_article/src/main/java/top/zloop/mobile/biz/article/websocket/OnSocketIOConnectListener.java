package top.zloop.mobile.biz.article.websocket;

public interface OnSocketIOConnectListener {


    void onConnect();

    void onDisconnect();

    void onConnectTimeout();

    void onConnectError();

}
