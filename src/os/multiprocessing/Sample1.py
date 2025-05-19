import os
from multiprocessing import Process
import requests

def f(name):
    x = requests.get('https://www.w3schools.com/python/demopage.htm')
    print('pid of parent:', os.getppid())
    print('pid of %s : %d' %(name, os.getpid()))
    print(x.text)

if __name__ == '__main__':
    print('pid of main:', os.getpid())

    p1 = Process(target=f, args=("프로세스 1", ))
    p2 = Process(target=f, args=("프로세스 2", ))
    p1.start(); p1.join()
    p2.start(); p2.join()
