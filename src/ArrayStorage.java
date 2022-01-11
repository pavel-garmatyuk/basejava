import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void checkResume(Resume r) {
        if (r == null || r.uuid == null) {
            System.err.println("Resume is missing or does not exist!");
        }
    }

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void update(Resume r) {
        if (r == null || r.uuid == null) {
            System.err.println("Resume is missing or does not exist!");
        }
        storage[findIndex(r)] = r;
    }

    int findIndex(Resume r) {
        int i = 0;
        for (; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                return i;
            }
        }
        return -1;
    }

    void save(Resume r) {
        if (r != null || r.uuid != null) {
            storage[size++] = r;
        } else {
            System.err.println("Resume is missing or does not exist!");
        }
    }

    Resume get(String uuid) {
        if (uuid == null)
            System.err.println("Resume is missing or does not exist!");

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                size--;
                break;
            }
        }
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
