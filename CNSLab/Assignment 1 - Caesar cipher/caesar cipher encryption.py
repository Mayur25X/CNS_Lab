def encrypt(plain_text, shift):
    caesar_cipher = ""

    for char in plain_text:
        if char == ' ':
            caesar_cipher += ' '
        elif char.isupper():
            caesar_cipher += chr((ord(char) + shift - 65) % 26 + 65)
        else:
            caesar_cipher += chr((ord(char) + shift - 97) % 26 + 97)

    return caesar_cipher

def main():
    plain_text = input("enter plain text: ")
    print()

    shift = int(input("enter value of shift: "))
    print()

    caesar_cipher = encrypt(plain_text, shift)
    print("The Caesar cipher encrypted text is:")
    print(caesar_cipher)

if __name__ == "__main__":
    main()
