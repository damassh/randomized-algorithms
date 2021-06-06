import random

import numpy as np
import scipy.stats as stats

class Benchmark():
    directory = 'benchmarks/'
    def __init__(self):
        pass

    def uniform_distribution(self,n):
        r = np.random.uniform(0, 1000, size=n)
        print(r)
        file = self.directory +  'uniform_distribution' + str(n) +'.csv'
        np.savetxt(file, r, delimiter=',', fmt='%f')
        pass

    def gamma_distribution(self, n):
        r = stats.gamma.rvs(a= 1000, size=n)
        print(r)
        file = self.directory + 'gamma_distribution' + str(n) + '.csv'
        np.savetxt(file, r, delimiter=',', fmt='%f')

    def exponential_distribution(self, n):
        r = stats.expon.rvs(scale=1000,loc=0,size=n)
        print(r)
        file = self.directory + 'exponential_distribution' + str(n) + '.csv'
        np.savetxt(file, r, delimiter=',', fmt='%f')
        pass

    def main(self):
        data_size = [1000, 10000, 100000,1000000, 10000000]
        for i in range(len(data_size)):
            self.uniform_distribution(data_size[i])
            self.gamma_distribution(data_size[i])
            self.exponential_distribution(data_size[i])


if __name__ == '__main__':
    benchmark = Benchmark()
    benchmark.main()
