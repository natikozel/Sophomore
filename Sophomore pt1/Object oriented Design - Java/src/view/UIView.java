package view;


import listeners.ActionEventsListener;

import java.util.ArrayList;

public interface UIView {

    void registerListener(ActionEventsListener listener);
    void removeListener(ActionEventsListener listener);
    void initValues();
    void initActions() throws Exception;
    void hideCurrentStage();
    void shutStageDown();


    }
