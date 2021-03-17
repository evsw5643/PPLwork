{
 "cells": [
  {
   "cell_type": "code",
   "execution_count": 50,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "2240\n"
     ]
    }
   ],
   "source": [
    "import random\n",
    "import math\n",
    "def merge(C,A,B):\n",
    "    i = 0\n",
    "    j = 0\n",
    "    count = 0\n",
    "    while (i < len(A) or j < len(B)):\n",
    "        if (i == len(A)):\t#if array A is empty\n",
    "            C[i+j] = B[j]\t#add the next element of B to C\n",
    "            j+=1\n",
    "        elif (j == len(B)):\t#if array B is empty \n",
    "            C[i+j] = A[i] \t#add the next val of A to C\n",
    "            i+=1\n",
    "        elif (A[i] <= B[j]):\t#if value in A <= value in B\n",
    "            C[i+j] = A[i]\t\t#add the less value A[i] to C\n",
    "            i+=1               \n",
    "        else:\t                #if the value in B > A\n",
    "            C[i+j] = B[j]\t\t#append the value of B[j] to C\n",
    "            count += len(A)-i\t#this tells us how many reverses the B subarray had to make\n",
    "            j+=1\t\t\t\t\t\n",
    "    return count\n",
    "    \n",
    "\n",
    "def part(C):\n",
    "    if (len(C) < 2):\t#termination of recursive loop\n",
    "        return 0\n",
    "\n",
    "    m = math.floor((len(C))/ 2)\t\t#to find where the parent array should be split\n",
    "    A = C[:m]\t\t\t#A = the everything to the left of m\n",
    "    B = C[m:]\t\t\t#B = everything to the right of m\n",
    "    \n",
    "    return part(A) + part(B) + merge(C, A, B)\n",
    "    #recursively split A and B, and then merge when base case is reached\n",
    "\n",
    "def main():\n",
    "\tC = random.sample(range(1,250),100)\n",
    "\tprint (part(C))\n",
    "    \n",
    "\n",
    "main()\n"
   ]
  },
  {
   "cell_type": "markdown",
   "metadata": {},
   "source": [
    "##### "
   ]
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  }
 ],
 "metadata": {
  "kernelspec": {
   "display_name": "Python 3",
   "language": "python",
   "name": "python3"
  },
  "language_info": {
   "codemirror_mode": {
    "name": "ipython",
    "version": 3
   },
   "file_extension": ".py",
   "mimetype": "text/x-python",
   "name": "python",
   "nbconvert_exporter": "python",
   "pygments_lexer": "ipython3",
   "version": "3.8.5"
  }
 },
 "nbformat": 4,
 "nbformat_minor": 4
}
