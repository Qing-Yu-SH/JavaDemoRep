## 1.性能结果

| Benchmark                        | Mode | Cnt  | Score     | Error  | Units |
| -------------------------------- | ---- | ---- | --------- | ------ | ----- |
| HashMapCycleTest.entrySet        | avgt | 5    | 217.835 ± | 31.503 | ns/op |
| HashMapCycleTest.forEachEntrySet | avgt | 5    | 212.382 ± | 8.148  | ns/op |
| HashMapCycleTest.forEachKeySet   | avgt | 5    | 321.312 ± | 6.856  | ns/op |
| HashMapCycleTest.keySet          | avgt | 5    | 323.379 ± | 9.975  | ns/op |
| HashMapCycleTest.lambda          | avgt | 5    | 155.472 ± | 6.539  | ns/op |
| HashMapCycleTest.streamApi       | avgt | 5    | 243.237 ± | 42.449 | ns/op |

* ns/op 意思是执行完成时间（单位为纳秒）
*  Score 列为平均执行时间
* `±` 符号表示误差



## 2.性能分析

从结果可知：

* Iterator 和 ForEach 遍历的 `keySet` 或 `entrySet` 两者的耗时差不多
  * 原因是因为在执行 ForEach 时，实际上是创建了一个迭代器来遍历
* 对 `keySet` 遍历，要比 `entrySet` 耗时，所以**我们应该尽量使用 `enetrySet` 来实现 Map 集合的遍历**
  *  `keySet` 遍历循环了两遍 `Map`，`map.get(key)` 相当于又遍历了一遍 Map 集合去查询 `key` 所对应的值
  * `entrySet` 只遍历一遍 `Map`

* 相对而言，`lambda` 的方式效率更高



## 3.安全性分析

* 迭代器中循环删除数据安全
* For 循环中删除数据非安全
* Lambda 循环中删除数据非安全
  * 可以通过 `removeIf()` 先删除
* Stream 循环中删除数据非安全
  * 可以通过 `filter` 过滤