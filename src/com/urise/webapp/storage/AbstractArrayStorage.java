package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LENGTH = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LENGTH];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        var index = findIndex(uuid);
        if (index == -1) {
            System.err.println("No resume " + uuid + " in the database!");
            return null;
        }
        return storage[index];
    }

    protected abstract int findIndex(String uuid);
}
