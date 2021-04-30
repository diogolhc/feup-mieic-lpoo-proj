package controller.farm;

import model.Farmer;
import model.Position;

import java.util.HashSet;
import java.util.Set;

public class InteractionController {
    Set<InteractionListener> listeners;

    public InteractionController() {
        this.listeners = new HashSet<>();
    }

    public void notifyInteraction(Position position) {
        for (InteractionListener listener: listeners) {
            listener.notifyInteraction(position);
        }
    }

    public void addInteractionListener(InteractionListener listener) {
        this.listeners.add(listener);
    }

    public void removeInteractionListener(InteractionListener listener) {
        this.listeners.remove(listener);
    }
}
