package top.zloop.mobile.biz.article.websocket;

import top.zloop.mobile.biz.article.websocket.data.Message;

public interface OnSocketIOMessageEventListener {

    void onNewMessage(Message message);

}
