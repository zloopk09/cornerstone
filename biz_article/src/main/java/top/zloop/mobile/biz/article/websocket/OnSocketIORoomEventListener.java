package top.zloop.mobile.biz.article.websocket;

import top.zloop.mobile.biz.article.websocket.data.Message;

public interface OnSocketIORoomEventListener {

    void onUserJoined(Message message);

    void onUserLeft(Message message);

}
