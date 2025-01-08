#Write a recursive function to calculate the sum of numbers from 0 to 10

def calculate_sum(num):
    if num:
        print(num)
        return num + calculate_sum(num-1)
    else:
        return 0

# Call calculateSum() function
input_num = int(input("Enter the Number: "))
res = calculate_sum(input_num)

print(res)