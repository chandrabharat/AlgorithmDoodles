class WordDictionary {

    private class TrieNode {
        TrieNode[] list;
        boolean isEnd;
        private TrieNode() {
            list = new TrieNode[26];
            isEnd = false;
        }

        public void set(char ch) {
            list[ch - 'a'] = new TrieNode();
        }
        public TrieNode get(char ch) {
            return list[ch - 'a'];
        }
        public boolean contains(char ch) {
            return list[ch - 'a'] != null;
        }
    }
    /** Initialize your data structure here. */
    TrieNode trie;
    public WordDictionary() {
        trie = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode temp = trie;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!temp.contains(ch)) {
                temp.set(ch);
            }
            temp = temp.get(ch);
        }
        temp.isEnd = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        TrieNode temp = trie;
        return search(word, temp);
    }

    public boolean search(String word, TrieNode temp) {
        if (word.length() == 0) {
            return temp.isEnd;
        }
        char ch = word.charAt(0);
        if (ch != '.') {
            return temp.contains(ch) ? search(word.substring(1), temp.get(ch)) : false;
        }
        for (int i = 0; i < temp.list.length; i++) {
            if (temp.list[i] != null) {
                if(search(word.substring(1), temp.list[i])) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */