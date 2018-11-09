package top.zloop.mobile.biz.article.websocket;

public interface BizSocketInterface {


    void connect();

    boolean isConnected();

    void disconnect() ;

    void off();

    void registerConnectLisenner(OnSocketIOConnectListener listener);


}
