#Write a function sum() such that it can accept a list of elements and print the sum of all elements
def calculate_sum(num):
    sum = 0
    for number in num:
        sum += int(number)
        print(sum)
    return number

num_list = list(input("Enter the numbers: ").split(","))
print(num_list)

result = calculate_sum(num_list)

print("The sum of all the elements is: " + str(result))
