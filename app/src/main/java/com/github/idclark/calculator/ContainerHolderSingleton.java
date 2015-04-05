package com.github.idclark.calculator;

import com.google.android.gms.tagmanager.ContainerHolder;

/**
 * Created by idclark on 4/5/15.
 */
public class ContainerHolderSingleton {

    private static ContainerHolder containerHolder;

    /**
     * Utility class; don't instantiate.
     */
    private ContainerHolderSingleton() {
    }

    public static ContainerHolder getContainerHolder() {
        return containerHolder;
    }

    public static void setContainerHolder(ContainerHolder c) {
        containerHolder = c;
    }
}

