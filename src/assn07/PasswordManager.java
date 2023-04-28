package assn07;

import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password123";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
        //test();
    }


    // TODO: put
    @Override
    public void put(K key, V value) {

        int i = Math.abs(key.hashCode()) % 50;
        if(_passwords[i] == null) {
            _passwords[i] = new Account(key, value);
        } else {
            Account p = _passwords[i];
            if (p.getWebsite().equals(key)) {
                p.setPassword(value);
                return;
            }
            while(p.getNext() != null) {
                if (p.getWebsite().equals(key)) {
                    p.setPassword(value);
                    return;
                }
                p = p.getNext();
            }
            p.setNext(new Account(key, value));
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int i = Math.abs(key.hashCode()) % 50;
        if(_passwords[i] == null) {
            return null;
        }
        Account p = _passwords[i];
        if(p.getWebsite().equals(key)) {
            return (V) p.getPassword();
        }
        while (p.getNext() != null) {
            p = p.getNext();
            if(p.getWebsite().equals(key)) {
                return (V) p.getPassword();
            }
        }
        return null;
    }

    public void test() {
        Account<K,V> act = new Account("google", "bark");
        _passwords[3] = act;
    }

    // TODO: size
    @Override
    public int size() {
        int size = 0;
        for(int i = 0; i < 50; i++) {
            if(_passwords[i] != null) {
                Account p = _passwords[i];
                size++;
                while(p.getNext() != null) {
                    p = p.getNext();
                    size++;
                }
            }
        }
        return size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> accnts = Collections.emptySet();
        for(int i = 0; i < 50; i++) {
            if(_passwords[i] != null) {
                Account p = _passwords[i];
                accnts.add((K) p.getWebsite());
                while(p.getNext() != null) {
                    p = p.getNext();
                    accnts.add((K) p.getWebsite());
                }
            }
        }
        return accnts;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int i = Math.abs(key.hashCode()) % 50;
        if(_passwords[i] == null) {
            return null;
        }
        Account p = _passwords[i];
        if(p.getWebsite().equals(key)) {
            if(p.getNext() == null) {
                _passwords[i] = null;
            }
            return (V) p.getPassword();
        }
        while (p.getNext() != null) {
            Account prev = p;
            p = p.getNext();
            if(p.getWebsite().equals(key)) {
                prev.setNext(p.getNext());
                return (V) p.getPassword();
            }

        }
        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> accnts = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            if(_passwords[i] != null) {
                Account p = _passwords[i];
                if(value.equals(p.getPassword())) {
                    accnts.add((K) p.getWebsite());
                }
                while(p.getNext() != null) {
                    p = p.getNext();
                    if(value.equals(p.getPassword())) {
                        accnts.add((K) p.getWebsite());
                    }
                }
            }
        }

        return accnts;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return(enteredPassword.equals(MASTER_PASSWORD));
    }

    /*
    Generates random password of input length
     */
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
