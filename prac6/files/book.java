public class book {
    public void insert(String word) {
            TrieNonLeaf p = root;
            TrieLeaf lf;
            int offset, pos, i = 0;
            while (true) {
            if (i == word.length()) { // if the end of word reached, then
            if (p.endOfWord) // set endOfWord to true;
            System.out.println("duplicate entry1: " + word);
            p.endOfWord = true; // set endOfWord to true;
            return;
            } // if position in p indicated
            
            pos = position(p,word.charAt(i));
            if (pos == notFound) { // by the first letter of word
            createLeaf(word.charAt(i),word.substring(i+1),p);
            // does not exist, create
            return; // a leaf and store in it the
            } // unprocessed suffix of word;
            else if (pos != notFound && // empty leaf in position pos;
            p.ptrs[pos] == null) {
            if (i+1 == word.length()) {
            System.out.println("duplicate entry1: " + word);
            return;
            }
            p.ptrs[pos] = new TrieNonLeaf(word.charAt(i+1));
            ((TrieNonLeaf)(p.ptrs[pos])).endOfWord = true;
            // check whether there is any suffix left:
            String s = (word.length() > i+2) ? word.substring(i+2) : null;
            createLeaf(word.charAt(i+1),s,(TrieNonLeaf)(p.ptrs[pos]));
            return;
            }
            else if (pos != notFound && // if position pos is
            p.ptrs[pos].isLeaf) { // occupied by a leaf,
            lf = (TrieLeaf) p.ptrs[pos]; // hold this leaf;
            if (lf.suffix.equals(word.substring(i+1))) {
            System.out.println("duplicate entry2: " + word);
            return;
            }
            offset = 0;
            // create as many nonleaves as the length of identical
            // prefix of word and the string in the leaf (for cell 'R',
            // leaf "EP", and word "REAR", two such nodes are created);
            do {
            pos = position(p,word.charAt(i+offset));
            // word = "ABC", leaf = "ABCDEF" => leaf = "DEF";
            if (word.length() == i+offset+1) {
            p.ptrs[pos] = new TrieNonLeaf(lf.suffix.charAt(offset));
            p = (TrieNonLeaf) p.ptrs[pos];
            p.endOfWord = true;
            createLeaf(lf.suffix.charAt(offset),
            lf.suffix.substring(offset+1),p);
            return;
            }
            
            // word = "ABCDEF", leaf = "ABC" => leaf = "DEF";
            else if (lf.suffix.length() == offset ) {
            p.ptrs[pos] = new TrieNonLeaf(word.charAt(i+offset+1));
            p = (TrieNonLeaf) p.ptrs[pos];
            p.endOfWord = true;
            createLeaf(word.charAt(i+offset+1),
            word.substring(i+offset+2),p);
            return;
            }
            p.ptrs[pos] = new TrieNonLeaf(word.charAt(i+offset+1));
            p = (TrieNonLeaf) p.ptrs[pos];
            offset++;
            } while (word.charAt(i+offset) == lf.suffix.charAt(offset-1));
            offset--;
            // word = "ABCDEF", leaf = "ABCPQR" =>
            // leaf('D') = "EF", leaf('P') = "QR";
            // check whether there is any suffix left:
            // word = "ABCD", leaf = "ABCPQR" =>
            // leaf('D') = null, leaf('P') = "QR";
            String s = null;
            if (word.length() > i+offset+2)
            s = word.substring(i+offset+2);
            createLeaf(word.charAt(i+offset+1),s,p);
            // check whether there is any suffix left:
            // word = "ABCDEF", leaf = "ABCP" =>
            // leaf('D') = "EF", leaf('P') = null;
            if (lf.suffix.length() > offset+1)
            s = lf.suffix.substring(offset+1);
            else s = null;
            createLeaf(lf.suffix.charAt(offset),s,p);
            return;
            }
            else {
            p = (TrieNonLeaf) p.ptrs[pos];
            i++;
            }
            }
        }
    }
}
