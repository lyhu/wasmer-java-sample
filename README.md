

# wasmer-java-sample

主要使用 [wasmer-java](https://github.com/wasmerio/wasmer-java/) 加载`wasm`二进制文件的示例。

## wasmer-jni 编译处理

对 [beaclnd92](https://github.com/beaclnd92/wasmer-java/tree/imports) 的 imports PR 与 [wasmer-java](https://github.com/wasmerio/wasmer-java/)
进行了合并提交到 我的 [fork](https://github.com/lyhu/wasmer-java) 主分支上了，并对其重新编译出 支持MacM1芯片的 `[wasmer-jni-arm64-darwin-0.3.0.jar](https://github.com/lyhu/wasmer-java-sample/tree/main/jar)`（jar目录下的其他的jar是从[官网下载](https://github.com/wasmerio/wasmer-java/releases/tag/0.3.0)的）,
最后将不同平台的jar合并到了同一个 `[wasmer-jni-0.3.0.jar](https://github.com/lyhu/wasmer-java-sample/tree/main/jar)`,(内部自动根据平台和操作系统进行加载对应的so文件)


## golang wasm 编译
golang 研发的 wasm 需要 通过 tinygo 编译 成 wasi目标的 wasm 二进制文件。
> GOOS=js GOARCH=wasm go build -o ./html/main.wasm . 编译出来的 wasm格式 主要用在浏览器中 WebAssembly

在本示例中的 src/main/resources/wasm/go
```shell
tinygo build -no-debug -o myDemo.wasm -wasm-abi=generic -target=wasi main.go
```

不过由于 TinyGo 目前的 fs 模块的兼容性问题，我们的编译会失败：
```
# github.com/common-nighthawk/go-figure
../../go/pkg/mod/github.com/common-nighthawk/go-figure@v0.0.0-20210622060536-734e95fb86be/bindata.go:3614:11: Chtimes not declared by package os
```

把对应的 bindata.go 文件的 line 3614 - 3617 行注释掉，重新执行 `tinygo build ...` 命令就可以了。
```
3614	// err = os.Chtimes(_filePath(dir, name), info.ModTime(), info.ModTime())
3615	// if err != nil {
3616	// 	return err
3617	// }
```

## 参考

- [使用 Docker 和 Golang 快速上手 WebAssembly](https://soulteary.com/2021/11/21/use-docker-and-golang-to-quickly-get-started-with-webassembly.html)目前对 wasm 总结的比较好的文章
- [借助 WASM 进行密集计算：入门篇](https://soulteary.com/2021/11/26/intensive-computing-with-wasm-part-1.html)
- [Build a Chat service using GoLang and WebAssembly](https://dev.to/taherfattahi/build-a-chat-service-using-golang-and-webassembly-part-1-1pee)
- [Web Assembly 官网](https://webassembly.org)  [中文 Web Assembly](https://www.wasm.com.cn) 
- [tinygo](https://tinygo.org/) 
- [tinygo git](https://github.com/tinygo-org/tinygo)
- [用 Easegress + WebAssembly 做秒杀](https://www.bilibili.com/video/BV1HL4y1a7nk)
