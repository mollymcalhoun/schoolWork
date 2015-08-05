class Amicable(object):

    def __init__(self, limit):
        self.limit = limit
    
    def sum_divisors(self, x):
        div_sum = 0
        x_half = x/2 + 1
        for i in range(1, x_half):
            if x % i == 0:
                div_sum += i
        #print div_sum
        return div_sum

    def check_amicable(self, x, y):
        return self.sum_divisors(y) == x

    def go(self):
        amicable_sum = 0
        is_sum_suitable = False
        is_amicable = False
        used_nums = []

        for i in range(2, self.limit):
            if i not in used_nums:
                i_sum = self.sum_divisors(i)
                is_sum_suitable = (i_sum != i) and (i_sum < self.limit) and (i_sum > 1)
                if is_sum_suitable:
                    is_amicable = self.check_amicable(i, i_sum)
                if is_amicable:
                    used_nums.append(i)
                    used_nums.append(i_sum)
                    amicable_sum += i + i_sum
            is_sum_suitable = False
            is_amicable = False
        print amicable_sum

if __name__ == "__main__":
    amicable = Amicable(10000)
    amicable.go()