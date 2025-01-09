import pytest

# Write test method
def test_sum(num_list):

    
    sum = 0

    for n in num_list:
        sum += n

    # Assertion
    assert sum == 55