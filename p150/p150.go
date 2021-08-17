package p150

import (
	"fmt"
	"strconv"
)

var evalMap = map[string]func(int, int) int{
	"+": func(i1, i2 int) int { return i1 + i2 },
	"-": func(i1, i2 int) int { return i1 - i2 },
	"*": func(i1, i2 int) int { return i1 * i2 },
	"/": func(i1, i2 int) int { return i1 / i2 },
}

type EvalItem struct {
	i1          *int
	i2          *int
	op          func(int, int) int
	evaluatable bool
}

func toNum(token string) int {
	num, err := strconv.Atoi(token)
	if err != nil {
		panic(err)
	}
	return num
}

func (i *EvalItem) AddToken(t string) {
	if i.evaluatable {
		return
	}

	num := toNum(t)
	if i.i2 == nil {
		i.i2 = &num
	} else if i.i1 == nil {
		i.i1 = &num
		i.evaluatable = true
	}
}

type EvalStack struct {
	items []EvalItem
}

func (es *EvalStack) AddToken(t string) {
	op, ok := evalMap[t]
	if ok {
		es.items = append(es.items, EvalItem{op: op})
		return
	}
	if len(es.items) == 0 {
		return
	}
	top := &es.items[len(es.items)-1]
	top.AddToken(t)
}

func (es *EvalStack) Eval() (int, bool) {
	if len(es.items) == 0 {
		return 0, false
	}
	top := es.items[len(es.items)-1]
	if top.evaluatable {
		es.items = es.items[:len(es.items)-1]
		return top.op(*top.i1, *top.i2), true
	}
	return 0, false
}

func evalRPN(tokens []string) int {
	es := EvalStack{
		items: make([]EvalItem, 0),
	}
	var top string
	for {
		res, ok := es.Eval()
		if ok {
			tokens = append(tokens, fmt.Sprint(res))
		}
		if len(tokens) == 0 {
			break
		}
		top = tokens[len(tokens)-1]
		tokens = tokens[:len(tokens)-1]
		es.AddToken(top)
	}
	num, _ := strconv.Atoi(top)
	return num
}
