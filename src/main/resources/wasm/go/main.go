package main

import (
	"github.com/common-nighthawk/go-figure"
)

func main() {}

//export HelloWorld
func HelloWorld() {
	myFigure := figure.NewFigure("Hello World", "", true)
	myFigure.Print()
}

//如果 没有导出，会抛异常
//panic: Export `Sum` does not exist

//export Sum
func Sum(x, y int) int {
	return x + y
}
