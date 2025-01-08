FirstList = [10,20,40,50,33]
SecondList = [99,77,44,22,11]
ThirdList = []

print(FirstList)
print(SecondList)

for num in FirstList:
    if (num % 2 != 0):
        ThirdList.append(num)

for num in SecondList:
    if (num % 2 == 0):
        ThirdList.append(num)


print("The Result of Third List are :", ThirdList)