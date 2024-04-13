package trie;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Nov 06,2023 - 9:31 AM
 */

/**
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/
 *
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 25
 * word in addWord consists of lowercase English letters.
 * word in search consist of '.' or lowercase English letters.
 * There will be at most 2 dots in word for search queries.
 * At most 104 calls will be made to addWord and search.
 */
public class DesignAddandSearchWordsDataStructure {

    public static void main(String[] args) {
        DesignAddandSearchWordsDataStructure o = new DesignAddandSearchWordsDataStructure();
        o.addWord("bad");
        o.addWord("dad");
        o.addWord("mad");
        o.addWord("a");
        System.out.println(o.search("aa")); // return False
        System.out.println(o.search("pad")); // return False
        System.out.println(o.search("bad")); // return True
        System.out.println(o.search(".ad")); // return True
        System.out.println(o.search("b..")); // return True
    }

    Trie root;
    public DesignAddandSearchWordsDataStructure() {
        root = new Trie();
    }

    public void addWord(String word) {
        root.addWord(word);
    }

    public boolean search(String word) {
        return root.search(word, root, 0);
    }

    public class Trie {
        Trie[] childs;
        int[] childIndexes;
        int childLength;
        String word;

        public Trie() {
            childs = new Trie[26];
            childIndexes = new int[26];
            childLength = 0;
        }

        private void addWord(String word) {
            Trie curr = this;

            for (char c : word.toCharArray()) {
                if (curr.childs[c-'a'] == null) {
                    curr.childs[c-'a'] = new Trie();
                    curr.childIndexes[curr.childLength++] = c-'a';
                }
                curr = curr.childs[c-'a'];
            }
            curr.word = word;
        }

        private boolean search(String word, Trie curr, int index) {

            if (curr == null) {
                return false;
            }
            if (curr.word != null) {
                return true;
            }

            for (int j = index; j < word.length(); j++) {
                char c = word.charAt(j);
                if (c == '.') {
                    for (int i = 0; i < curr.childLength; i++) {
                        if (search(word, curr.childs[curr.childIndexes[i]], index+1)) {
                            return true;
                        }
                    }
                } else {
                    return curr.childs[c-'a'] == null ? false : search(word, curr.childs[c-'a'], index+1);
                }
            }
            return false;
        }

    }

}
