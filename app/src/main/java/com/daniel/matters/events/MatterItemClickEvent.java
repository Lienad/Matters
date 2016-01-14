package com.daniel.matters.events;

import com.daniel.matters.Matter;

/**
 * Created by dabraham on 1/13/16.
 */
public class MatterItemClickEvent {
    public Matter matter;

    public MatterItemClickEvent(Matter matter) {
        this.matter = matter;
    }
}
