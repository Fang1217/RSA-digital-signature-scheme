# RSA Digital Signature Scheme

## Overview
The **RSA Digital Signature Scheme** repository provides a Java-based implementation of the RSA algorithm for creating and verifying digital signatures. This implementation includes a graphical user interface (GUI) for ease of use, ensuring the authenticity and integrity of messages through cryptographic means, leveraging the widely-used RSA public-key cryptosystem.

## Features
- **Key Generation:** Generate RSA public and private keys.
- **Signing:** Create a digital signature for a message using a private key.
- **Verification:** Verify the authenticity of a message and its signature using a public key.
- **GUI:** User-friendly graphical interface for key management, message signing, and verification.

## Requirements
- Java Development Kit (JDK) 8 or higher

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/Fang1217/RSA-digital-signature-scheme.git
   ```
2. Navigate to the project directory:
   ```sh
   cd RSA-digital-signature-scheme
   ```

## Usage
1. In `RSA-digital-signature-scheme` directory, compile the Java source files:
   ```sh
   javac *.java
   ```
2. Run the main `DSASwingUI` GUI application:
   ```sh
   java DSASwingUI
   ```

## File Structure
- `DSASwingUI.java`: Main application class for the Graphical User Interface (GUI).
- `DSA.java`: Main application class for the Command Line Interface (CLI).
- `RSAKeyPair.java`: Handles RSA key generation.
- `Signer.java`: Manages message signing operations.
- `Verifier.java`: Manages signature verification operations.
- `README.md`: Project documentation.
