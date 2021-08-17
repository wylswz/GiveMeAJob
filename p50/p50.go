package p50

func myPow(x float64, n int) float64 {
	if n == 0 {
		return 1
	}
	if n < 0 {
		return 1 / myPow(x, -n)
	}
	if n&1 == 0 {
		val := myPow(x, n/2)
		return val * val
	}
	if n > 2 {
		return x * myPow(x, n-1)
	}
	return x
}
