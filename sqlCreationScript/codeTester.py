import hashlib


salt = "EXAMPLESALT"
stdPassword = hashlib.sha256(salt.encode() + b'password123').hexdigest()

print(stdPassword)