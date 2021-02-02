package solsgo

import "sort"

/*
You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.



Example 1:

Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)

Example 2:

Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)

Example 3:

Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)
*/

type peopleWeight []int

func (p peopleWeight) Len() int {
	return len(p)
}

func (p peopleWeight) Less(i int, j int) bool {
	return p[i] < p[j]
}

func (p peopleWeight) Swap(i int, j int) {
	p[i], p[j] = p[j], p[i]
}

func numRescueBoats(people []int, limit int) int {
	sort.Sort(peopleWeight(people))

	l, r := 0, len(people)-1
	res := 0
	for l < r {
		if people[l]+people[r] > limit {
			r--
			res++
		} else {
			r--
			l++
			res++
		}
	}

	if l == r {
		res++
	}
	return res
}
