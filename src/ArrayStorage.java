import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int STORAGE_LENGTH = 10000;
    Resume[] storage = new Resume[STORAGE_LENGTH];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void update(Resume r) {
        var index = findIndex(r.uuid);
        if (index == -1) {
            System.err.println("Resume is missing or does not exist!");
            return;
        }
        storage[index] = r;
    }

    int findIndex(String uuid) {
        int i = 0;
        for (; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    void save(Resume r) {
        var index = findIndex(r.uuid);
        if (index != -1 || size >= STORAGE_LENGTH) {
            System.err.println("It is not possible to save these data or files. They are already in the system!");
            return;
        }
        storage[size++] = r;
    }

    Resume get(String uuid) {
        var index = findIndex(uuid);
        if (index == -1) {
            System.err.println("No resume in the database!");
            return null;
        }
        return storage[index];
    }

    void delete(String uuid) {
        var index = findIndex(uuid);
        if (!storage[index].uuid.equals(uuid)) {
            System.err.println("It is not possible to delete a void or a non-existent object!");
            return;
        }
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
