#include <bits/stdc++.h>
using namespace std;

int main() {

	long long fib[] = {8,2};
	int i=0;
	long long summed=2;

	while(fib[i] < 4000000) {
		summed += fib[i];
		i=(i+1)%2;
		fib[i]= 4*fib[(i+1)%2]+fib[i];
	}

	cout << summed << endl;
}
