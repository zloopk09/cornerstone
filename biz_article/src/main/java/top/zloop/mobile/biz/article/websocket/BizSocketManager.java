package top.zloop.mobile.biz.article.websocket;

public class BizSocketManager {

    private static final String TAG = BizSocketManager.class.getSimpleName();

    private static BizSocketManager instance;

    private BizSocketManager() {}

    public static BizSocketManager getInstance() {
        if (instance == null) {
            instance = new BizSocketManager();
        }
        return instance;
    }

    public MessageSocket getMessageSocket(){
        return new MessageSocket();
    }

    public RoomSocket getRoomSocket(){
        return new RoomSocket();
    }
}
