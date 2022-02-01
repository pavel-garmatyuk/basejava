package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        var index = findIndex(r.getUuid());
        if (index == -1) {
            System.err.println("Resume " + r + " is missing or does not exist!");
            return;
        }
        storage[index] = r;
    }

    public void save(Resume r) {
        var index = findIndex(r.getUuid());
        if (index != -1) {
            System.err.println("It is not possible to save these data or files." + r + " They are already in the system!");
            return;
        }
        if (size >= STORAGE_LENGTH) {
            System.err.println("Array index outside! Or the length of the array is set incorrectly.");
            return;
        }
        storage[size++] = r;
    }


    public void delete(String uuid) {
        var index = findIndex(uuid);
        if (index == -1) {
            System.err.println("It is not possible to delete " + uuid + " a void or a non-existent object!");
            return;
        }
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
