// Jaysheel Pandya
// CSE 143 DD with Xunmei Liu
// Homework 1
// A LetterInventory stores the number of instances each
// letter in the alphabet occurs within the given String.

public class LetterInventory {

   // stores the number of instances for each letter
   private int[] counters;
   
   // stores the total number of letters
   private int size;
   
   private static final int LETTERS_IN_ALPHABET = 26;
      
   // represents the shift required to access lowercase letter 'a'
   private static final int CHAR_SHIFT = 97;
   
   // constructs a LetterInventory
   // data is any String passed through, represents
   // the text being evaluated for instances of letters
   public LetterInventory(String data) {
      counters = new int[LETTERS_IN_ALPHABET];
      size = 0;
      data = data.toLowerCase();
      for (int i = 0; i < data.length(); i++) {
         char ch = data.charAt(i);
         int chNum = ch - CHAR_SHIFT;
         if (chNum >= 0 && chNum < LETTERS_IN_ALPHABET) {
            counters[chNum]++;
            size++;
         }
      }
   }
   
   // PRE: Given letter must be a valid letter in the alphabet (throws an
   // IllegalArgumentException otherwise).
   // POST: Returns the number of instances the given letter occurs
   // in the LetterInventory
   public int get(char letter) {
      int letterNum = charToInt(letter);
      if (isInvalidInput(letterNum)) {
         throw new IllegalArgumentException("Character invalid");
      } 
      return counters[letterNum];
   }
   
   // PRE: Given letter must be a valid letter in the alphabet (throws an
   // IllegalArgumentException otherwise). Given value must be 
   // non-negative (throws an IllegalArgumentException otherwise).
   // POST: Sets the number of instances the given letter occurs
   // in the LetterInventory to the given value
   public void set(char letter, int value) {
      int letterNum = charToInt(letter);
      if (isInvalidInput(letterNum) || (value < 0)) {
         throw new IllegalArgumentException("Invalid inputs");
      }
      else {
         size += value - counters[letterNum];
         counters[letterNum] = value;
      }
   }
   
   // Returns the total number of letters in the LetterInventory
   public int size() {
      return size;
   }
   
   // Returns true if the LetterInventory has zero instances
   // of every letter
   public boolean isEmpty() {
      return size == 0;
   }
   
   // Returns the contents of the LetterInventory in lowercase, 
   // alphabetical order of letters (surrounded by brackets)
   public String toString() {
      String str = "[";
      for (int i = 0; i < LETTERS_IN_ALPHABET; i++) {
         for (int j = 0; j < (counters[i]); j++) {
            str += (char) (i + CHAR_SHIFT);
         }
      }
      str += "]";
      return str;
   }
   
   // Creates and returns a LetterInventory that combines the
   // contents of two different LetterInventory objects
   public LetterInventory add(LetterInventory other) {
      LetterInventory sum = new LetterInventory("");
      sum.size = this.size + other.size;
      for (int i = 0; i < LETTERS_IN_ALPHABET; i++) {
         sum.counters[i] = this.counters[i] + other.counters[i];
      }
      return sum;
   }
   
   // Creates and returns a LetterInventory that subtracts the
   // contents of one LetterInventory from another
   // Returns null if the count of any letter results in a negative
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory res = new LetterInventory("");
      res.size = this.size - other.size;
      for (int i = 0; i < LETTERS_IN_ALPHABET; i++) {
         res.counters[i] = this.counters[i] - other.counters[i];
         if (res.counters[i] < 0) {
            return null;
         }
      }
      return res;
   }
   
   // Converts the given letter into an integer 
   private int charToInt(char letter) {
      return Character.toLowerCase(letter) - CHAR_SHIFT;
   }
   
   // Returns true when given value is outside the range of 
   // possible letters, making it invalid
   private boolean isInvalidInput(int letterNum) {
      return letterNum < 0 || letterNum >= LETTERS_IN_ALPHABET;
   }
}