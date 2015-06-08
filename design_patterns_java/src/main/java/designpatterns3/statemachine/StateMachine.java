package designpatterns3.statemachine;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates a state machine
 * @author jsanca
 */
public class StateMachine implements Serializable {

    private State currentState = null;

    private Map<String, State> stateMap =
            new HashMap<String, State>();

    public void changeState (final String stateName, Context context) {

        if (null != this.currentState) {

            this.currentState.handleOut(context);
        }

        this.currentState =
                this.stateMap.get(stateName);

        if (null != this.currentState) {

            this.currentState.handleIn(context);
        }
    }

    public void addState (final String stateName,
                          final State state) {

        this.stateMap.put(stateName, state);
    }

    public static void main(String [ ] args) {

        final StateMachine stateMachine =
                new StateMachine();
        final Context context =
                new ContextImpl();

        stateMachine.addState("play", new PlayStateAction());
        stateMachine.addState("pause", new PauseStateAction());
        stateMachine.addState("stop", new StopStateAction());

        context.set("video", "party.mpeg");
        stateMachine.changeState("play", context);

        System.out.println(".....");
        stateMachine.changeState("pause", context);

        System.out.println(".........");
        stateMachine.changeState("play", context);

        System.out.println(".........");
        stateMachine.changeState("stop", context);
    }
} // E:O:F:StateMachine.
