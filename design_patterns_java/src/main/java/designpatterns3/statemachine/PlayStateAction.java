package designpatterns3.statemachine;

/**
 * Perform an play action
 * @author jsanca
 */
public class PlayStateAction implements State {

    @Override
    public void handleIn(Context context) {

        System.out.println("Playing the video: " + context.get("video"));
    }

    @Override
    public void handleOut(Context context) {

        System.out.println("I was playing the video" + context.get("video"));
    }
}
