numbers = list(input("Enter a sequence of comma separated values: ").split(", "))
sum = 0

print(numbers)
FirstEle = numbers[0]
LastEle = numbers[-1]
if (FirstEle == LastEle):
    print(True)
else:
    print(False)