Fruit_Shop = {
    "apple" : 10,
    "banana" : 20,
    "cherry" : 30,
    "peaches" : 40,
    "avacado" : 3,
    "grapes" : 4
}

Key_Check = input("what are you looking for : ").lower()
if (Key_Check in Fruit_Shop):
    print("Entered ",Key_Check," are available")
else:
    print("Entered ", Key_Check, " are not available" )