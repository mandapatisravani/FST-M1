#Write a Python program that throws a NameError.
try:
    print(x)
except NameError:
    print("x hasn't been defined yet.")