import nltk
from nltk.corpus import words
nltk.download("words")

english_words = set(words.words())

def decrypt(ciphertext, s):
    result = ""

    for char in ciphertext:
        if char == " ":
            result += " "
        elif char.isupper():
            result += chr((ord(char) - s - 65) % 26 + 65)
        else:
            result += chr((ord(char) - s - 97) % 26 + 97)

    return result

def main():
    ciphertext = input("Enter encrypted text: ")
    print()
    
    shift_key=0
    decrypted_text_store=""

    for s in range(1, 27):
        decrypted_text = decrypt(ciphertext, s)

        decrypted_words = decrypted_text.split()
        print(decrypted_words)
        is_valid = all(word.lower() in english_words for word in decrypted_words)

        if is_valid:
            shift_key=s
            decrypted_text_store=decrypted_text
            break

    if s and decrypted_text_store:
        print("Valid decryption with shift key", shift_key)
        print("Decrypted Text:", decrypted_text)
    else:
            print("Decryption Unsuccessful")

if __name__ == "__main__":
    main()
